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
import net.alumisky.simulator.network.PeerStrategy;
import net.alumisky.simulator.network.manager.NetworkManager;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Network engine internal network context.
 *
 * @author Artem.Elchin
 */
class NetworkContext {

    /**
     * Peer network.
     */
    private Network network;
    /**
     * Network multicast messages.
     */
    private Queue<Message> writeContainer;
    private Queue<Message> readContainer;
    /**
     * Network peers.
     */
    private Map<PeerID, Peer> peerMap;

    /**
     * Constructs default context.
     *
     * @param network peer network
     */
    public NetworkContext(Network network) {
        this.network = network;
        peerMap = new ConcurrentHashMap<PeerID, Peer>();
        readContainer = new ConcurrentLinkedQueue<Message>();
        writeContainer = new ConcurrentLinkedQueue<Message>();
    }

    public Network getNetwork() {
        return network;
    }

    public void addPeer(Peer peer) {
        peerMap.put(peer.getID(), peer);
    }

    public void swapMessageContainers() {
        Queue<Message> tempContainer = writeContainer;
        writeContainer = readContainer;
        readContainer = tempContainer;
    }

    public void clearDeliveredMessages() {
        readContainer.clear();
    }

    public void announce(Message message) {
        writeContainer.add(message);
    }

    public void deliverMessages(Peer peer, PeerNetwork network, NetworkManager manager) {
        PeerStrategy strategy = peer.getStrategy();
        for (Message message : readContainer) {
            if(manager.canDeliverMulticast(message, peer)) {
                strategy.onMulticastMessage(message, network);
            }
        }
    }
}
