/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 * Peer strategy contract. Defines call-back functions to be used to deliver
 * messages, etc.
 *
 * @author Artem.Elchin
 */
public interface PeerStrategy {

    /**
     * Called during initialization procedure.
     *
     * @param network peer network
     */
    void onInitialize(PeerNetwork network);

    /**
     * Called during finalization.
     * 
     * @param network peer network
     */
    void onDeinitialize(PeerNetwork network);

    /**
     * Called when ordinary message arrives.
     *
     * @param message received message
     * @param network peer network
     */
    void onMessage(Message message, PeerNetwork network);

    /**
     * Called when multicast message arrives.
     *
     * @param message multicast message
     * @param network peer network
     */
    void onMulticastMessage(Message message, PeerNetwork network);

    /**
     * Called when new pipe message arrives.
     *
     * @param message received message
     * @param pipe established connection
     * @param network peer network
     */
    void onPipeMessage(Message message, Pipe pipe, PeerNetwork network);

    /**
     * Called when new connection is being setup.
     *
     * @param port connection port
     * @param network peer network
     */
    void onConnect(int port, PeerNetwork network);

    /**
     * Called when existing connection is being terminated.
     *
     * @param port connection port
     * @param network peer network
     */
    void onDisconnect(int port, PeerNetwork network);

    /**
     * Execute peer logic.
     *
     * @param network peer network
     */
    void process(PeerNetwork network);
}
