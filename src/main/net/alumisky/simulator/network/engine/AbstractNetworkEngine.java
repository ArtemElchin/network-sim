/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.engine;

import com.alumisky.service.AbstractRunnableService;
import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.util.concurrent.ConcurrentHelpers;
import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.manager.NetworkManager;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.manager.PipeManager;
import net.alumisky.simulator.network.Peer;
import net.alumisky.simulator.network.exception.NetworkException;
import net.alumisky.simulator.network.impl.NetworkSimulator;
import net.alumisky.simulator.network.exception.UnknownPeerException;

import java.util.Map;
import java.util.HashMap;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import net.alumisky.simulator.network.manager.PermissiveNetworkManager;
import net.alumisky.simulator.network.manager.PermissivePipeManager;

/**
 * Network engine implementation.
 *
 * @author Artem.Elchin
 */
public abstract class AbstractNetworkEngine extends AbstractRunnableService implements NetworkEngine {

    private static final NetworkID NETWORK_ID = NetworkSimulator.createNetworkID(0);
    private NetworkManager networkManager;
    private PipeManager pipeManager;
    private Map<PeerID, PeerContext> peerMap;
    private Map<NetworkID, NetworkContext> networkMap;
    private int iterationDelay = 50;

    private Universe universe = UniverseFactory.createDefaultUniverse();

    /**
     * Constructs default Network Engine implementation.
     */
    public AbstractNetworkEngine() {       
        peerMap = new HashMap<PeerID, PeerContext>();
        networkMap = new HashMap<NetworkID, NetworkContext>();
        pipeManager = new PermissivePipeManager();
        networkManager = new PermissiveNetworkManager();
    }

    public Universe getUniverse() {
        return universe;
    }

    /**
     * {@inheritDoc}
     */
    public void registerNetwork(Network network) {
        if (null == network) {
            throw new IllegalArgumentException("Network could not be null");
        }
        networkMap.put(network.getID(), new NetworkContext(network));
    }

    /**
     * {@inheritDoc}
     */
    public void setNetworkManager(NetworkManager manager) {
        if (null == manager) {
            throw new IllegalArgumentException("Network manager could not be null");
        }
        this.networkManager = manager;
    }

    /**
     * {@inheritDoc}
     */
    public void setPipeManager(PipeManager manager) {
        if (null == manager) {
            throw new IllegalArgumentException("Pipeline manager could not be null");
        }
        this.pipeManager = manager;
    }

    /**
     * {@inheritDoc}
     */
    public void registerPeer(NetworkID netID, Peer peer) {
        if (null == netID) {
            throw new IllegalArgumentException("Network ID can not be null");
        } else if (null == peer) {
            throw new IllegalArgumentException("Peer can not be null");
        }

        universe.addObject(new PeerUniverseObject(peer));

        NetworkContext netContext = networkMap.get(netID);

        if (null != netContext) {
            if (!peerMap.containsKey(peer.getID())) {
                PeerContext peerContext = new PeerContext(netContext.getNetwork(), peer);
                peerMap.put(peer.getID(), peerContext);
                System.out.println("registering: " + peer.getID().hashCode());
                netContext.addPeer(peer);
            } else {
                throw new IllegalArgumentException("Peer with peerID: " + peer.getID() + " is not known");
            }
        } else {
            throw new IllegalArgumentException("Network with networkID: " + netID + " is not known");
        }
    }

    private void walkThroughPeers(PeerAction action) {
        for (Map.Entry<PeerID, PeerContext> peerEntry : peerMap.entrySet()) {
            PeerContext peerContext = peerEntry.getValue();
            NetworkContext netContext = networkMap.get(peerContext.getNetworkID());
            PeerNetworkWrapper peerNetwork = null;

            Peer peer = peerContext.getPeer();
            peerNetwork = new PeerNetworkWrapper(netContext.getNetwork());
            peerNetwork.setSourcePeer(peer);

            switch (action) {
                case INITIALIZE:
                    peerContext.initialize(peerNetwork);
                    break;
                case DEINITIALIZE:
                    peerContext.deinitialize(peerNetwork);
                    break;
            }
        }
    }

    public void onStart() {
        walkThroughPeers(PeerAction.INITIALIZE);
    }

    public void onStop() {
        walkThroughPeers(PeerAction.DEINITIALIZE);
    }

    @Override
    public boolean process() {
        iterate();
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void iterate() {

        // swap network message lists
        for (Map.Entry<NetworkID, NetworkContext> netEntry : networkMap.entrySet()) {
            NetworkContext netContext = netEntry.getValue();
            netContext.swapMessageContainers();
        }

        for (Map.Entry<PeerID, PeerContext> peerEntry : peerMap.entrySet()) {

            PeerContext peerContext = peerEntry.getValue();
            NetworkContext netContext = networkMap.get(peerContext.getNetworkID());

            processPeer(peerContext, netContext, networkManager, pipeManager);
        }

        // clear network message lists
        for (Map.Entry<NetworkID, NetworkContext> netEntry : networkMap.entrySet()) {
            NetworkContext netContext = netEntry.getValue();
            netContext.clearDeliveredMessages();
        }

        ConcurrentHelpers.sleep(iterationDelay);
    }

    public abstract void processPeer(PeerContext peerContext, NetworkContext netContext,
        NetworkManager networkManager, PipeManager pipelineManager);
    
    /**
     *
     * @param delay
     */
    public void setIterationDelay(int delay) {
        this.iterationDelay = delay;
    }

    /**
     * {@inheritDoc}
     */
    public NetworkID getID() {
        return NETWORK_ID;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "networkEngine" + NETWORK_ID;
    }

    /**
     * {@inheritDoc}
     */
    public void announce(PeerID source, Message message) throws NetworkException {
        PeerID peerID = message.getSource();
        PeerContext peerContext = getPeerContext(peerID);

        NetworkID networkID = peerContext.getNetwork().getID();
        NetworkContext networkContext = networkMap.get(networkID);
        networkContext.announce(message);
    }

    /**
     * {@inheritDoc}
     */
    public void send(PeerID source, PeerID destination, Message message) throws NetworkException {
        PeerContext peerContext = getPeerContext(destination);
        peerContext.send(message);
    }

    /**
     * {@inheritDoc}
     */
    public Pipe connect(PeerID source, PeerID destination, int port) throws NetworkException {
        PipeContext pipeTo = null;
        PipeContext pipeFrom = null;

        PeerContext sourceContext = getPeerContext(source);
        PeerContext destinationContext = getPeerContext(destination);

        pipeTo = destinationContext.connect(port);
        pipeFrom = sourceContext.registerConnection(port);

        pipeTo.registerOpposite(pipeFrom);
        pipeFrom.registerOpposite(pipeTo);

        return pipeTo;
    }

    /**
     * {@inheritDoc}
     */
    public void disconnect(PeerID source, PeerID destination, int port) throws NetworkException {
        PeerContext sourceContext = getPeerContext(source);
        PeerContext destinationContext = getPeerContext(destination);
        sourceContext.disconnect(port);
        destinationContext.disconnect(port);
    }

    /**
     * {@inheritDoc}
     */
    public void attach(PeerID id, int port) throws NetworkException {
        PeerContext peerContext = getPeerContext(id);
        peerContext.listen(port);
    }

    /**
     * {@inheritDoc}
     */
    public void detach(PeerID id, int port) throws NetworkException {
        PeerContext peerContext = getPeerContext(id);
        peerContext.detach(port);
    }

    /**
     *
     * @param id
     * @return
     * @throws UnknownPeerException
     */
    private PeerContext getPeerContext(PeerID id) throws UnknownPeerException {
        PeerContext peerContext = peerMap.get(id);

        if (null == peerContext) {
            System.out.println("map: " + peerMap.toString());
            System.out.println("Trying to find: " + id.hashCode());
            throw new UnknownPeerException("PeerContext with peerID: " + id + " is not known");
        }

        return peerContext;
    }

    enum PeerAction {
        INITIALIZE,
        DEINITIALIZE
    }
}
