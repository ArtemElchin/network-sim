/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.strategy;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.PeerStrategy;
import net.alumisky.simulator.network.Pipe;

/**
 * Abstract strategy with empty PeerStrategy implementation to let
 * derived classes to implement only needed handlers.
 *
 * @author Artem.Elchin
 */
public abstract class AbstractPeerStrategy implements PeerStrategy {

    /**
     * {@inheritDoc}
     */
    public void onInitialize(PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onDeinitialize(PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onMessage(Message message, PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onMulticastMessage(Message message, PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onPipeMessage(Message message, Pipe pipe, PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onConnect(int port, PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void onDisconnect(int port, PeerNetwork network) {
    }

    /**
     * {@inheritDoc}
     */
    public void process(PeerNetwork network) {
    }
}
