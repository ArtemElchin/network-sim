/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.exception;

/**
 *
 * @author Renat Gilmanov
 */
public class EngineException extends RuntimeException {
    
    /**
     * 
     * @param message 
     */
    public EngineException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     */
    public EngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
