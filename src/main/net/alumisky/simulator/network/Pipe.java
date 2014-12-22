/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

import net.alumisky.simulator.network.exception.NetworkException;

/**
 * Pipes are virtual communication channels used to send and receive messages between
 * services or applications. Pipes provide the illusion of a virtual in and out mailbox
 * that is independent of any single peer location, and independent of network
 * topology (multi-hops route).
 *
 * @author Renat.Hilmanov
 */
public interface Pipe {

    /**
     * Returns destination peer id.
     *
     * @return destination peer id
     */
    PeerID getDestination();

    /**
     * Return pipe port.
     *
     * @return pipe port.
     */
    int getPort();

    /**
     * Sends a message using pipe.
     *
     * @param message message to send
     * @throws NetworkException if error occurs
     */
    void send(Message message) throws NetworkException;
}
