/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.common.exception;

/**
 *
 * @author Artem.Elchin
 */
public class StateChangeException extends RuntimeException {

    /**
     * 
     * @param description 
     */
    public StateChangeException(String description) {
        super(description);
    }

    /**
     * 
     * @param description
     * @param cause 
     */
    public StateChangeException(String description, Throwable cause) {
        super(description, cause);
    }
}
