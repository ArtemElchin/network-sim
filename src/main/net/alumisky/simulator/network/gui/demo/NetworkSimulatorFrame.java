/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui.demo;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import com.alumisky.ui.universe.UniverseObject;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import com.alumisky.ui.universe.ObjectAttr;
import com.alumisky.ui.universe.UniverseView;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.ui.universe.viewer.UniverseViewer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JFrame;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkEngine;
import net.alumisky.simulator.network.NetworkID;
import net.alumisky.simulator.network.demo.ClientPeerStrategy;
import net.alumisky.simulator.network.demo.ServerPeerStrategy;
import net.alumisky.simulator.network.gui.AbstractNetworkObject;
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import net.alumisky.simulator.network.impl.NetworkSimulator;

/**
 *
 * @author Artem.Elchin
 */
class RendererName implements Renderer {
    final Font titleFont = new Font("TimesRoman", Font.BOLD, 16);
    
    @Override
    public void render(Graphics g, UniverseObject object, Attributes view) {
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        Rectangle r = view.getObjectRectangle();
        g.fillRect(r.x, r.y, r.width, r.height);        
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        String title = ((AbstractNetworkObject)object).getName();
        int totalWidth = (fm.stringWidth(title) * 2) + 4;
        int x = (int) ((r.getWidth() - totalWidth)) + fm.stringWidth(title) / 2;
        int y = (int) (((r.getHeight() - fm.getHeight()) / 2) + fm.getAscent());
        g.drawString(title, r.x + x, r.y + y);
        g.drawRect(r.x, r.y, r.width, r.height);
    }
}

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

        panel.addRenderer(PeerUniverseObject.TYPE, new RendererName());
        for (int i = 0; i < engine.getUniverse().getObjects().size(); i++) {
            view.addAttributes(engine.getUniverse().getObject(i), new ObjectAttr(new Point(0, 0)));
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
