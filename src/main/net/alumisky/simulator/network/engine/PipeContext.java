/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.engine;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.PeerStrategy;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.exception.NetworkException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.alumisky.simulator.network.manager.PipeManager;

/**
 *
 * @author Artem.Elchin
 */
class PipeContext implements Pipe {

    private int port;
    private PeerID peerID;
    private boolean closed = false;
    private PipeContext opposite;

    /**
     * Peer incoming messages.
     */
    private Queue<Message> messages;

    public PipeContext(PeerID peerID, int port) {
        this.peerID = peerID;
        this.port = port;
        messages = new ConcurrentLinkedQueue<Message>();
    }

    public PeerID getDestination() {
        return peerID;
    }

    public void registerOpposite(PipeContext opposite) {
        if(null == opposite) {
            throw new IllegalArgumentException("Opposite pipe could not be null");
        }else if(this == opposite) {
            throw new IllegalArgumentException("Opposite thread could not be the same");
        }

        this.opposite = opposite;
    }

    public int getPort() {
        return port;
    }

    public void send(Message message) throws NetworkException {
        if(!closed) {
            message.setSource(peerID);
            messages.add(message);
        }else{
            throw new NetworkException("Connection closed");
        }
    }

    public void deliverPipeMessages(PeerStrategy strategy, PeerNetwork network, PipeManager manager) {
        
        int queueSize = messages.size();
        for(int i=0; i<queueSize; i++) {
            Message message = messages.poll();
            if(manager.canDeliver(message, peerID, port)) {
                strategy.onPipeMessage(message, opposite, network);
            }else{
                break;
            }
        }
    }

    public void markAsClosed() {
        if(!closed) {
            closed = true;
            opposite.markAsClosed();
        }
    }
}
