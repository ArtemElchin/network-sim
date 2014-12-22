/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

import net.alumisky.simulator.network.exception.NetworkException;

/**
 * Peer network abstraction. Provides simplified network abstraction, hiding
 * source peer.
 *
 * @author Renat.Hilmanov
 */
public interface PeerNetwork {

    /**
     * Send multicast message.
     *
     * @param message message to be published.
     * @throws NetworkException if error occurs
     */
    void announce(Message message) throws NetworkException;

    /**
     * Message to be sent towards destination peer.
     *
     * @param message message to be sent
     * @param destination message destination
     * @throws NetworkException if error occurs
     */
    void send(PeerID destination, Message message) throws NetworkException;

    /**
     * Request to establish connection to the specified peer.
     *
     * @param destination peer id
     * @param port port to connect
     * @return established connection instance - pipe
     * @throws NetworkException if error occurs
     */
    Pipe connect(PeerID destination, int port) throws NetworkException;

    /**
     * Request to terminate existing connection.
     *
     * @param destination destination peer id
     * @param port connected port
     * @throws NetworkException if error occurs
     */
    void disconnect(PeerID destination, int port) throws NetworkException;

    /**
     * Listens for a new connection.
     *
     * @param port connected port
     * @throws NetworkException if error occurs
     */
    void attach(int port) throws NetworkException;

    /**
     * Stop listening on new connections.
     *
     * @throws NetworkException if error occurs
     */
    void detach(int port) throws NetworkException;
}
