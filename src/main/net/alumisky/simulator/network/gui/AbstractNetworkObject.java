/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui;

import net.alumisky.simulator.network.NetworkEntity;

/**
 *
 * @param <T>
 * @author Renat Gilmanov
 */
public abstract class AbstractNetworkObject<T> {
    
    private NetworkEntity<T> entity;

    public AbstractNetworkObject(NetworkEntity<T> entity) {
        this.entity = entity;
    }

    /**
     * Get peer name.
     *
     * @return name of the peer
     */
    public String getName() {
        return entity.getName();
    }

    /**
     * Returns node identifier.
     *
     * @return node id
     */
    public T getID() {
        return entity.getID();
    }
}