/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.app;

import com.alumisky.ui.universe.AbstractUniverseRenderer;
import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.ObjectAttr;
import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;
import com.alumisky.ui.universe.impl.AbstractUniverse;
import com.alumisky.ui.universe.impl.UniverseFactory;
import com.alumisky.ui.universe.viewer.UniverseViewer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Artem.Elchin
 */
public class TopologyPanel extends JPanel {

    public TopologyPanel() {
        super(new BorderLayout());

        world = UniverseFactory.createDefaultUniverse();

        universeView1 = UniverseFactory.createUniverseView(world);
        UniverseViewer view = new UniverseViewer(universeView1, new Dimension(800, 800));

        view.addRenderer("typeA", new RendererA());
        view.addRenderer("typeB", new RendererA());

        add(BorderLayout.CENTER, view);
        generate();

    }

    final int WORLD_WIDTH = 800;
    final int WORLD_HEIGHT = 800;

    protected void generate() {
        final int OBJECTS = 100;

        for (int i = 0; i < OBJECTS; i++) {
            ObjectA a = new ObjectA();
            world.addObject(a);
            int x = (int) (Math.random() * WORLD_WIDTH);
            int y = (int) (Math.random() * WORLD_HEIGHT);

            universeView1.addAttributes(a, new ObjectAttr(new Point(x, y)));
        }
    }

    static class TreeUniverse extends AbstractUniverse {

        public TreeUniverse() {
            super();
        }

    }

    static class ObjectA implements UniverseObject {

        public String getType() {
            return "typeA";
        }
    }

    UniverseView universeView1;
    Universe world;

    class RendererA extends AbstractUniverseRenderer {

        public void render(Graphics g, UniverseObject object, Attributes view) {
            g.setColor(Color.LIGHT_GRAY);
            Rectangle r = view.getObjectRectangle();
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawRect(r.x, r.y, r.width, r.height);
        }
    }
}
