/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.exception;

/**
 * Signals that a Network exception of some sort has occurred.
 * This class is the general class of exceptions produced by failed or
 * interrupted network operations.
 *
 * @author Artem.Elchin
 */
public class NetworkException extends EngineException {

    /**
     * Constructs a NetworkException with the specified detail message.
     * 
     * @param message The detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method)
     */
    public NetworkException(String message) {
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
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
