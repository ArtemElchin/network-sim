/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.demo;

import net.alumisky.simulator.network.strategy.AbstractPeerStrategy;
import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.MessagePayload;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.exception.NetworkException;
import net.alumisky.simulator.network.message.PortAnnouncePayload;
import net.alumisky.simulator.network.util.NetworkHelper;

/**
 *
 * @author Artem.Elchin
 */
public class ClientPeerStrategy extends AbstractPeerStrategy {
    /**
     * Default port to be used.
     */
    private boolean connected = false;
    
    private int port = 0;
    private PeerID serverID;

    /**
     * {@inheritDoc}
     */
    public void onMessage(Message message, PeerNetwork network) {
        System.out.println("   client, onMessage: " + message.getPayload());
    }

    /**
     * {@inheritDoc}
     */
    public void onMulticastMessage(Message message, PeerNetwork network) {
        MessagePayload payload = message.getPayload();
        System.out.println("   client, onMulticastMessage: " + payload);

        if(payload instanceof PortAnnouncePayload) {
            PortAnnouncePayload announce = (PortAnnouncePayload) payload;
            serverID = message.getSource();
            port = announce.getPort();

            System.out.println("   client, server found: " + serverID + " port: " + port);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onPipeMessage(Message message, Pipe pipe, PeerNetwork network) {
        System.out.println("   client: pipe message: " + message.getSource()
                + " message: " + message.getPayload());
        Message newMessage = NetworkHelper.createMessage("from-client");
        try {
            pipe.send(newMessage);
        } catch (NetworkException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void process(PeerNetwork network) {
        if(!connected && serverID != null) {
            try {
                Pipe connection = network.connect(serverID, port);
                connected = true;
                System.out.println("   connected: " + serverID + " port: " + port);
                
                Message newMessage = NetworkHelper.createMessage("from-client-initial");
                connection.send(newMessage);
            } catch (NetworkException ex) {
                ex.printStackTrace();
            }
        }
    }    
}
