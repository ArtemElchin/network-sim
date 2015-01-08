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

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.PeerStrategy;
import net.alumisky.simulator.network.exception.NetworkException;
import net.alumisky.simulator.network.manager.NetworkManager;
import net.alumisky.simulator.network.manager.PipeManager;

/**
 * Network engine internal peer context.
 *
 * @author Artem.Elchin
 */
class PeerContext {

    /**
     * Peer.
     */
    private final Peer peer;
    /**
     * Peer network.
     */
    private final Network network;
    /**
     * Peer incoming messages.
     */
    private final Queue<Message> messages;
    /**
     * Map of port listeners.
     */
    private final Map<Integer, Boolean> listeners;
    /**
     * Peer connections.
     */
    private final Map<Integer, PipeContext> connections;

    /**
     * Constructs default context.
     * 
     * @param network peer network
     */
    public PeerContext(Network network, Peer peer) {
        this.network = network;
        this.peer = peer;
        messages = new ConcurrentLinkedQueue<>();
        listeners = new ConcurrentHashMap<>();
        connections = new ConcurrentHashMap<>();
    }

    public Network getNetwork() {
        return network;
    }

    public NetworkID getNetworkID() {
        return network.getID();
    }

    public PeerID getID() {
        return peer.getID();
    }

    public void send(Message message) {
        messages.add(message);
    }

    public void listen(int port) throws NetworkException {
        if(!listeners.containsKey(port)) {
            listeners.put(port, Boolean.TRUE);
        }else{
            throw new NetworkException("Listener is already binded to port: " + port);
        }
    }
    
    public void detach(int port) throws NetworkException {
        if(connections.containsKey(port)) {
            PipeContext context = connections.remove(port);
            context.markAsClosed();
        }else{
            throw new NetworkException("No connection for port: " +port);
        }        
    }

    public PipeContext registerConnection(int port) throws NetworkException {
        return connect(port, false);
    }

    public PipeContext connect(int port) throws NetworkException {
        return connect(port, true);
    }

    private PipeContext connect(int port, boolean checkListener) throws NetworkException {
        PipeContext connection = null;
        if(!checkListener || listeners.containsKey(port)) {
            connection = new PipeContext(peer.getID(), port);
            connections.put(port, connection);
        }else{
            throw new NetworkException("No listener for port: " +port);
        }
        return connection;
    }

    public void disconnect(int port) throws NetworkException {
        detach(port);
    }


    public void initialize(PeerNetwork network) {
        PeerStrategy strategy = peer.getStrategy();
        strategy.onInitialize(network);
    }

    public void deinitialize(PeerNetwork network) {
        PeerStrategy strategy = peer.getStrategy();
        strategy.onDeinitialize(network);
    }

    public void deliverMessages(PeerNetwork network, NetworkManager manager) {
        PeerStrategy strategy = peer.getStrategy();
        for(Message message: messages) {
            if(manager.canDeliverMessage(message, peer)) {
                strategy.onMessage(message, network);
            }
        }
    }

    public void deliverPipeMessages(PeerNetwork network, PipeManager manager) {
        PeerStrategy strategy = peer.getStrategy();

        for (Map.Entry<Integer, PipeContext> connectionEntry : connections.entrySet()) {
            PipeContext pipe = connectionEntry.getValue();
            pipe.deliverPipeMessages(strategy, network, manager);
        }
        
        //strategy.onPipeMessage(null, null, network);
    }

    public void executePeerCycle(PeerNetwork network) {
        PeerStrategy strategy = peer.getStrategy();
        strategy.process(network);
    }

    public Peer getPeer() {
        return peer;
    }

    public String toString() {
        return "context[" + network.getID()+"," + peer.getID()+"]";
    }
}
