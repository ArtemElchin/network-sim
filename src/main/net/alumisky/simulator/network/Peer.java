/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 * A peer is any networked device. Each peer is identified by a unique ID.
 * Peers are autonomous and operate independently and asynchronously of all other peers.
 *
 * Peers are typically configured to spontaneously discover each other on the network
 * to form transient or persistent relationships with other peers.
 *
 * Peers may join or leave the network at any time. A peer should always anticipate
 * that connectivity may be lost to any peer that it is currently communicating with.
 * 
 * @author Renat.Hilmanov
 */
public interface Peer extends NetworkEntity<PeerID>{
    
    /**
     * Returns peer strategy.
     *
     * @return peer strategy
     */
    PeerStrategy getStrategy();
}


























