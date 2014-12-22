/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.impl;

import net.alumisky.simulator.network.Peer;

import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;

import java.util.concurrent.atomic.AtomicLong;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.PeerStrategy;
import net.alumisky.simulator.network.engine.SingleThreadNetworkEngine;

/**
 * Helper class.
 *
 * @author Artem.Elchin
 */
public final class NetworkSimulator {

    /**
     * Peer id counter.
     */
    private static AtomicLong nextPeerID = new AtomicLong();

    /**
     * Network id counter.
     */
    private static AtomicLong nextNetworkID = new AtomicLong();

    /**
     * Creates network instance.
     *
     * @param name of the network
     * @param engine network engine to be used
     * @return network instance
     */
    public static Network createNetwork(String name, NetworkEngine engine) {
        return new NetworkImpl(createNetworkID(nextNetworkID.incrementAndGet()),
                engine, name);
    }

    /**
     * Creates Network identifier.
     *
     * @param value network id
     * @return network identifier instance
     */
    public static NetworkID createNetworkID(long value) {
        return new NetworkIdentifier(nextNetworkID.incrementAndGet());
    }

    /**
     * Creates network engine instance.
     *
     * @return network engine instance
     */
    public static NetworkEngine createNetworkEngine() {
        return new SingleThreadNetworkEngine();
    }

    /**
     * Creates a peer using next available peer id.
     *
     * @param strategy peer strategy
     * @return Peer instance
     */
    public static Peer createPeer(PeerStrategy strategy) {
        return new PeerImpl(new PeerIdentifier(nextPeerID.incrementAndGet()), strategy);
    }

    /**
     * Returns peer textual representation.
     *
     * @param peer to convert
     * @return peer string
     */
    public static String peerToString(Peer peer) {
        return "Peer." + peer.getName();
    }

    /**
     * Singleton.
     */
    private NetworkSimulator() {
    }
}
