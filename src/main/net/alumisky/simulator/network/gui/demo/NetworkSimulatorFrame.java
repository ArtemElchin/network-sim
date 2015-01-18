/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui.demo;
import net.alumisky.simulator.network.gui.renderer.DefaultRenderer;
import com.alumisky.ui.universe.ObjectAttr;
import com.alumisky.ui.universe.UniverseView;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.ui.universe.viewer.UniverseViewer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.demo.ClientPeerStrategy;
import net.alumisky.simulator.network.demo.ServerPeerStrategy;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import net.alumisky.simulator.network.impl.NetworkSimulator;

/**
 *
 * @author Artem.Elchin
 */
public class NetworkSimulatorFrame extends JFrame {

    public static final int SIZE = 500;
    private UniverseViewer panel;
    private UniverseView view;

    public NetworkSimulatorFrame() {
        super("Cloud");

        // ---------------------------------------------
        NetworkEngine engine = NetworkSimulator.createNetworkEngine();
        Network network = NetworkSimulator.createNetwork("basicString", engine);

        NetworkID netID = network.getID();
        engine.registerNetwork(network);

        engine.registerPeer(netID, NetworkSimulator.createPeer(new ServerPeerStrategy()));
        engine.registerPeer(netID, NetworkSimulator.createPeer(new ClientPeerStrategy()));
       
        // ---------------------------------------------
        // Universe world = UniverseFactory.createDefaultUniverse();
        view = UniverseFactory.createUniverseView(engine.getUniverse());
        panel = new UniverseViewer(view);

        panel.addRenderer(PeerUniverseObject.TYPE, new DefaultRenderer());
        for (int i = 0; i < engine.getUniverse().getObjects().size(); i++) {
            view.addAttributes(engine.getUniverse().getObject(i), new ObjectAttr(80,60));
        }
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, panel);

        setSize(new Dimension(SIZE, SIZE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        NetworkSimulatorFrame frame = new NetworkSimulatorFrame();
        frame.setVisible(true);
    }
}
