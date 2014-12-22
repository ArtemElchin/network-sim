/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.engine;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.Peer;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.exception.NetworkException;

/**
 *
 * @author Artem.Elchin
 */
public class PeerNetworkWrapper implements PeerNetwork {

    private Peer source;
    private PeerID sourceID;
    private Network network;

    /**
     *
     * @param network
     */
    public PeerNetworkWrapper(Network network) {
        this.network = network;
    }

    /**
     * 
     * @param peer
     */
    public void setSourcePeer(Peer peer) {
        this.source = peer;
        this.sourceID = peer.getID();
    }

    /**
     * {@inheritDoc}
     */
    public void announce(Message message) throws NetworkException {
        network.announce(sourceID, message);
    }

    /**
     * {@inheritDoc}
     */
    public void send(PeerID destination, Message message) throws NetworkException {
        network.send(sourceID, destination, message);
    }

    /**
     * {@inheritDoc}
     */
    public Pipe connect(PeerID destination, int port) throws NetworkException {
        return network.connect(sourceID, destination, port);
    }

    /**
     * {@inheritDoc}
     */
    public void disconnect(PeerID destination, int port) throws NetworkException {
        network.disconnect(sourceID, destination, port);
    }

    /**
     * {@inheritDoc}
     */
    public void attach(int port) throws NetworkException {
        network.attach(sourceID, port);
    }

    /**
     * {@inheritDoc}
     */
    public void detach(int port) throws NetworkException {
        network.detach(sourceID, port);
    }
}
