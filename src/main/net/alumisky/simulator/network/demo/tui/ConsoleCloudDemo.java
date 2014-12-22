/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.demo.tui;

import com.alumisky.service.ServiceException;
import com.alumisky.util.concurrent.ConcurrentHelpers;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.demo.ClientPeerStrategy;
import net.alumisky.simulator.network.demo.ServerPeerStrategy;
import net.alumisky.simulator.network.impl.NetworkSimulator;

/**
 * Basic virtual cloud demo.
 *
 * @author Artem.Elchin
 */
public class ConsoleCloudDemo {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting simulation");
        NetworkEngine engine = NetworkSimulator.createNetworkEngine();
        Network network = NetworkSimulator.createNetwork("basicString", engine);

        NetworkID netID = network.getID();
        engine.registerNetwork(network);

        engine.registerPeer(netID, NetworkSimulator.createPeer(new ServerPeerStrategy()));
        engine.registerPeer(netID, NetworkSimulator.createPeer(new ClientPeerStrategy()));
        
        try {
            engine.start();
            ConcurrentHelpers.sleep(500);
            engine.stop();
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }
}




























