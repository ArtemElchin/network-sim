/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network;

/**
 * Defines Agent contract. Agents are used to monitor peers, networks and gateways,
 *
 * @author Renat.Hilmanov
 */
public interface Agent {

    /**
     * Notifies agent new pipe is created.
     */
    void incrementPipesCount();
    
    /**
     * Notifies agent pipe is closed.
     */
    void decrementPipesCount();
    
    /**
     * Notifies agent new message arrived.
     */
    void incrementArrivedMessageCount();

    /**
     * Notifies agent message was processed.
     */
    void incrementProcessedMessageCount();

    /**
     * Notifies agent error has occurred.
     */
    void incrementErrorCount();

    /**
     * Log message, to be viewed from the Agent console.
     *
     * @param message message to log
     */
    void log(String message);
}




















