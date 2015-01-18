/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer.demo;

import com.alumisky.ui.universe.AbstractUniverseRenderer;
import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.ObjectAttr;
import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;
import com.alumisky.ui.universe.impl.AbstractUniverse;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.ui.universe.viewer.UniverseViewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

class UniverseFrame2 extends JFrame {

    class TreeUniverse extends AbstractUniverse {        

        public TreeUniverse() {
            super();
        }        
        
    }
    
    class ObjectA implements UniverseObject {

        public String getType() {
            return "typeA";
        }
    }

    UniverseView universeView1;
    Universe world;
    
    class RendererA extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor((view.isSelected()) ? Color.ORANGE : Color.LIGHT_GRAY);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }

    public UniverseFrame2() {
        super("Universe");

        world = UniverseFactory.createDefaultUniverse();

//        world.addObject(new ObjectA());
//        world.addObject(new ObjectA());

        universeView1 = UniverseFactory.createUniverseView(world);    
        UniverseViewer view1 = new UniverseViewer(universeView1, new Dimension(800, 800));
//        UniverseViewer view2 = new UniverseViewer(UniverseView1);
//        UniverseViewer view3 = new UniverseViewer(UniverseView2);
//        UniverseViewer view4 = new UniverseViewer(UniverseView2);

        view1.addRenderer("typeA", new RendererA());
//        view2.addRenderer("typeA", new RendererB());
//        view3.addRenderer("typeA", new RendererD());
//        view4.addRenderer("typeA", new RendererD());

        view1.addRenderer("typeB", new RendererA());
//        view2.addRenderer("typeB", new RendererB());
//        view3.addRenderer("typeB", new RendererC());
//        view4.addRenderer("typeB", new RendererD());

//        JSplitPane splitFirst = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(view1), view2);
//        JSplitPane splitSecond = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, view3, view4);

        generate();
        JSplitPane splitMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, view1, new JPanel());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, splitMain);

        setSize(new Dimension(600, 600));
//        splitFirst.setDividerLocation(200);
//        splitSecond.setDividerLocation(200);
        splitMain.setDividerLocation(400);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    final int WORLD_WIDTH = 200;
    final int WORLD_HEIGHT = 200;
    
     protected void generate() {
          final int OBJECTS = 22;
          
          for(int i=0; i<OBJECTS; i++) {
              ObjectA a = new ObjectA();
              world.addObject(a);
              int x = (int)(Math.random() * WORLD_WIDTH);
              int y = (int)(Math.random() * WORLD_HEIGHT);
                      
              universeView1.addAttributes(a, new ObjectAttr(new Point(x, y)));
            }
        }    
}

public class HugeTreeDemo {

    public HugeTreeDemo() {
    }

    public static void main(String[] args) {
        UniverseFrame2 frame = new UniverseFrame2();
        frame.setVisible(true);
    }
}