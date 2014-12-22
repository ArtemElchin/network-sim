/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

import com.alumisky.service.Service;
import com.alumisky.ui.universe.Universe;
import net.alumisky.simulator.network.manager.NetworkManager;
import net.alumisky.simulator.network.manager.PipeManager;

/**
 * Network simulation engine.
 *
 * @author Renat.Hilmanov
 */
public interface NetworkEngine extends Network, Service {
   
    /**
     * Registers network to be simulated.
     *
     * @param net network to simulate
     */
    void registerNetwork(Network net);

    /**
     * Register peer.
     *
     * @param netID network id
     * @param peer peer to be registered
     */
    void registerPeer(NetworkID netID, Peer peer);

    /**
     * Set network manager to be used during simulation.
     *
     * @param manager network simulation manager
     */
    void setNetworkManager(NetworkManager manager);
    
    /**
     * Set pipeline manager to be used during simulation.
     *
     * @param manager pipeline manager
     */
    void setPipeManager(PipeManager manager);
   
    /**
     * Set iteration delay.
     *
     * @param delay iteration delay
     */
    void setIterationDelay(int delay);

    Universe getUniverse();
}
