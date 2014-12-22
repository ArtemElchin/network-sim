/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.engine;

import net.alumisky.simulator.network.Peer;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.manager.NetworkManager;
import net.alumisky.simulator.network.manager.PipeManager;


/**
 * Network engine implementation.
 *
 * @author Artem.Elchin
 */
public class SingleThreadNetworkEngine extends AbstractNetworkEngine
        implements NetworkEngine {

    /**
     * 
     */
    public SingleThreadNetworkEngine() {
        super();
    }

    /**
     * 
     * @param peerContext
     * @param netContext
     * @param networkManager
     * @param pipelineManager
     */
    public void processPeer(PeerContext peerContext, NetworkContext netContext,
            NetworkManager networkManager, PipeManager pipelineManager) {

        Peer peer = peerContext.getPeer();
        PeerNetworkWrapper peerNetwork = new PeerNetworkWrapper(netContext.getNetwork());
        peerNetwork.setSourcePeer(peer);

        netContext.deliverMessages(peer, peerNetwork, networkManager); // deliver all network-wide messages
        peerContext.deliverMessages(peerNetwork, networkManager);      // deliver all peer-wide messages
        peerContext.deliverPipeMessages(peerNetwork, pipelineManager);  // deliver all pipe messages
        peerContext.executePeerCycle(peerNetwork);     // execute peer cycle
    }

}
