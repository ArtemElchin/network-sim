/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 *
 * @param <T>
 * @author Renat Gilmanov
 */
public interface NetworkEntity<T> {
    /**
     * Get peer name.
     *
     * @return name of the peer
     */
    String getName();

    /**
     * Returns node identifier.
     *
     * @return node id
     */
    T getID();
}

