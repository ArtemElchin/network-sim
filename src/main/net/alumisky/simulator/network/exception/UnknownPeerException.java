/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.exception;

/**
 * Signals that an unknown peer was requested.
 *
 * @author Artem.Elchin
 */
public class UnknownPeerException extends NetworkException {

    /**
     * Constructs a UnknownPeerException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method)
     */
    public UnknownPeerException(String message) {
        super(message);
    }

    /**
     * Constructs an NetworkException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method)
     * @param cause The cause (which is saved for later retrieval
     *              by the Throwable.getCause() method).
     */
    public UnknownPeerException(String message, Throwable cause) {
        super(message, cause);
    }
}
