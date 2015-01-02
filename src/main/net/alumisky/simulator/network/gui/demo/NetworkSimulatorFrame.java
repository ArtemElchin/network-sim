/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui.demo;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import net.alumisky.simulator.network.gui.renderer.DefaultRenderer;
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
import net.alumisky.simulator.network.gui.PeerUniverseObject;
import net.alumisky.simulator.network.impl.NetworkSimulator;

/**
 *
 * @author Artem.Elchin
 */

class RendererName implements Renderer<PeerUniverseObject> {

        public void render(Graphics g, PeerUniverseObject object, Attributes view) {
            
            int fontSize=16;
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize)); 
            g.setColor(Color.WHITE);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
             FontMetrics fm = g.getFontMetrics();
             String s=object.getName()+"dsdasa";
             
            int totalWidth = fm.stringWidth(s);
            
            if(totalWidth>r.width) {
               int lengthOneSymbol=(totalWidth/s.length());
               
            
               int sizeDots =fm.stringWidth("...");
               int numbSymbol=0;
               
                System.out.println(lengthOneSymbol);
               while(lengthOneSymbol+sizeDots+totalWidth/s.length()<r.width)
               {
                   numbSymbol++;
                   lengthOneSymbol+=totalWidth/s.length();
                }
               
                s=(s.substring(0, numbSymbol))+"...";
                totalWidth = fm.stringWidth(s);
            }
           
            
      
            int x = (int) (((r.width - totalWidth) )/4)+2;
            int y=(int) (((r.height - fm.getHeight()) / 2) + fm.getAscent());
            
            g.drawString(s, r.x+x, r.y+y );
            g.drawRoundRect(r.x, r.y, r.width, r.height, 30, 30);
                       
                
            
            
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

        panel.addRenderer(PeerUniverseObject.TYPE, new DefaultRenderer());
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
