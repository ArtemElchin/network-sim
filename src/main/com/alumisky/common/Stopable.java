/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.common;

import com.alumisky.common.exception.StateChangeException;

/**
 * 
 * @author Artem.Elchin
 */
public interface Stopable {

    /**
     * <p>Does ...</p>
     *
     */
    void start() throws StateChangeException;

    /**
     * <p>Does ...</p>
     *
     */
    void stop() throws StateChangeException;

    /**
     * 
     * @return
     */
    boolean isActive();
}


