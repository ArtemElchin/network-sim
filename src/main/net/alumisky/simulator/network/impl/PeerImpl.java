/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.impl;

import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.Peer;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.PeerStrategy;

/**
 * Default Peer implementation.
 * 
 * @author Artem.Elchin
 */
public class PeerImpl implements Peer {

    private PeerID id;
    private String name;
    private PeerStrategy strategy;

    /**
     * Constructs peer entity, using specified peer ID.
     *
     * @param id peer id
     * @param strategy peer strategy
     */
    public PeerImpl(PeerID id, PeerStrategy strategy) {
        this(id, strategy, "peer-" + id.getUID());
    }

    /**
     * Constructs peer entity, using specified peer ID and peer name.
     *
     * @param id peer id
     * @param strategy peer strategy
     * @param name peer name
     */
    public PeerImpl(PeerID id, PeerStrategy strategy, String name) {
        this.id = id;
        this.strategy = strategy;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public PeerID getID() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public void process(Network network) {
        if (null != strategy) {
            //strategy.process(network);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public PeerStrategy getStrategy() {
        return strategy;
    }

    /**
     * Returns meaningful peer textual presentation.
     *
     * @return peer textual presentation
     */
    @Override
    public String toString() {
        return NetworkSimulator.peerToString(this);
    }
}
