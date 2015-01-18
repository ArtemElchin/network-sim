/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer.demo;

import com.alumisky.ui.universe.AbstractUniverseRenderer;
import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.ui.universe.viewer.UniverseViewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

class UniverseFrame extends JFrame {

    class ObjectA implements UniverseObject {

        public String getType() {
            return "typeA";
        }
    }

    class ObjectB implements UniverseObject {

        public String getType() {
            return "typeB";
        }
    }

    class RendererA extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor(Color.BLUE);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    class RendererB extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor(Color.RED);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    class RendererC extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor(Color.GREEN);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    class RendererD extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor(Color.MAGENTA);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    public UniverseFrame() {
        super("Universe");

        Universe world = UniverseFactory.createDefaultUniverse();

        world.addObject(new ObjectA());
        world.addObject(new ObjectB());
        world.addObject(new ObjectB());
        world.addObject(new ObjectA());

        UniverseView UniverseView1 = UniverseFactory.createUniverseView(world);
        UniverseView UniverseView2 = UniverseFactory.createUniverseView(world);

        UniverseViewer view1 = new UniverseViewer(UniverseView1, new Dimension(800, 800));
        UniverseViewer view2 = new UniverseViewer(UniverseView1);
        UniverseViewer view3 = new UniverseViewer(UniverseView2);
        UniverseViewer view4 = new UniverseViewer(UniverseView2);

        view1.addRenderer("typeA", new RendererA());
        view2.addRenderer("typeA", new RendererB());
        view3.addRenderer("typeA", new RendererD());
        view4.addRenderer("typeA", new RendererD());

        view1.addRenderer("typeB", new RendererA());
        view2.addRenderer("typeB", new RendererB());
        view3.addRenderer("typeB", new RendererC());
        view4.addRenderer("typeB", new RendererD());

        JSplitPane splitFirst = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(view1), view2);
        JSplitPane splitSecond = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, view3, view4);
        JSplitPane splitMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitFirst, splitSecond);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, splitMain);

        setSize(new Dimension(600, 600));
        splitFirst.setDividerLocation(200);
        splitSecond.setDividerLocation(200);
        splitMain.setDividerLocation(200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

public class UniverseDemo {

    public UniverseDemo() {
    }

    public static void main(String[] args) {

        UniverseFrame frame = new UniverseFrame();
        frame.setVisible(true);
    }
}
