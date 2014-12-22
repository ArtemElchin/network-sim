/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.demo;

import net.alumisky.simulator.network.strategy.AbstractPeerStrategy;
import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.PeerNetwork;
import net.alumisky.simulator.network.Pipe;
import net.alumisky.simulator.network.exception.NetworkException;
import net.alumisky.simulator.network.util.NetworkHelper;

/**
 * 
 * @author Artem.Elchin
 */
public class ServerPeerStrategy extends AbstractPeerStrategy {

    /**
     * Default port to be used.
     */
    private static final int PORT = 3344;
    private static int COUNTER = 1;
    private int pingCount = 0;
    private boolean pipeMessage = false;

    /**
     * {@inheritDoc}
     */
    public void onInitialize(PeerNetwork network) {

        try {
            System.out.println("server: attaching port " + PORT);
            network.attach(PORT);
            System.out.println("server: announcing server ");
            network.announce(NetworkHelper.createPortAnnounce(PORT));
        } catch (NetworkException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void process(PeerNetwork network) {
        //network.
        try {
            if (pingCount++ < 2) {
                System.out.println("server: sending ping");
                network.announce(NetworkHelper.createMessage("Ping: " + COUNTER++));
            }
        } catch (NetworkException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onDeinitialize(PeerNetwork network) {
        try {
            System.out.println("server: detaching port " + PORT);
            network.detach(PORT);
        } catch (NetworkException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onConnect(int port, PeerNetwork network) {
        System.out.println("server: onConnect " + port);
    }

    /**
     * {@inheritDoc}
     */
    public void onDisconnect(int port, PeerNetwork network) {
        System.out.println("server: onDisconnect " + port);
    }

    public void onPipeMessage(Message message, Pipe pipe, PeerNetwork network) {        
        System.out.println("   server: pipe message: " + message.getSource()
                + " msg:" + message.getPayload());
        
        if (!pipeMessage) {
            System.out.println("     sending response from server");
            Message newMessage = NetworkHelper.createMessage("from-server");

            try {
                pipe.send(newMessage);
            } catch (NetworkException ex) {
                ex.printStackTrace();
            }
            pipeMessage = true;
        }
    }
}
