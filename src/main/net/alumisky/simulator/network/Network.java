/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network;

import net.alumisky.simulator.network.exception.NetworkException;

/**
 * Network abstraction. Supports all basic operations.
 *
 * @author Renat.Hilmanov
 */
public interface Network extends NetworkEntity<NetworkID> {

    /**
     * Send multicast message.
     *
     * @param source message source
     * @param message message to be published.
     * @throws NetworkException if error occurs
     */
    void announce(PeerID source, Message message) throws NetworkException;

    /**
     * Message to be sent towards destination peer.
     *
     * @param source message source
     * @param message message to be sent
     * @param destination message destination
     * @throws NetworkException if error occurs
     */
    void send(PeerID source, PeerID destination, Message message) throws NetworkException;

    /**
     * Request to establish connection to the specified peer.
     *
     * @param source message source
     * @param destination peer id
     * @param port port to connect
     * @return established connection instance - pipe
     * @throws NetworkException if error occurs
     */
    Pipe connect(PeerID source, PeerID destination, int port) throws NetworkException;

    /**
     * Request to terminate existing connection.
     *
     * @param source message source
     * @param destination destination peer id
     * @param port connected port
     * @throws NetworkException if error occurs
     */
    void disconnect(PeerID source, PeerID destination, int port) throws NetworkException;

    /**
     * Listens for a new connection.
     * 
     * @param source message source
     * @param port connected port
     * @throws NetworkException if error occurs
     */
    void attach(PeerID source, int port) throws NetworkException;

    /**
     * Listens for a new connection.
     *
     * @param source message source
     * @param port connected port
     * @throws NetworkException if error occurs
     */
    void detach(PeerID source, int port) throws NetworkException;
}
