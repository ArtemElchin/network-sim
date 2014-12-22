/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.impl;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.exception.NetworkException;

/**
 *
 * @author Artem.Elchin
 */
public class NetworkImpl implements Network {

    private String name;
    private NetworkID networkID;
    private NetworkEngine engine;

    /**
     * 
     * @param networkID
     * @param name
     */
    public NetworkImpl(NetworkID networkID, NetworkEngine engine, String name) {
        this.networkID = networkID;
        this.engine = engine;
        this.name = name;
    }

    /**
     * 
     * @return
     */
    public NetworkID getID() {
        return networkID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public void announce(PeerID source, Message message) throws NetworkException {
        message.setSource(source);
        engine.announce(source, message);
    }

    /**
     * {@inheritDoc}
     */
    public void send(PeerID source, PeerID destination, Message message) throws NetworkException {
        message.setSource(source);
        engine.send(source, destination, message);
    }

    /**
     * {@inheritDoc}
     */
    public Pipe connect(PeerID source, PeerID destination, int port) throws NetworkException {
        return engine.connect(source, destination, port);
    }

    /**
     * {@inheritDoc}
     */
    public void disconnect(PeerID source, PeerID destination, int port) throws NetworkException {
        engine.disconnect(source, destination, port);
    }

    /**
     * {@inheritDoc}
     */
    public void attach(PeerID id, int port) throws NetworkException {
        engine.attach(id, port);
    }

    /**
     * {@inheritDoc}
     */
    public void detach(PeerID source, int port) throws NetworkException {
        engine.detach(source, port);
    }
}
