/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 * Represents unique node identifier.
 * 
 * @author Artem.Elchin
 */
public interface PeerID {
    /**
     * Returns unique node identifier.
     *
     * @return unique identifier
     */
    long getUID();
}
