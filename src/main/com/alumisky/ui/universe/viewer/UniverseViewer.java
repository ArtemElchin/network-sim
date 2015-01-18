/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.Graphics2D;

/**
 *
 * @author Artem.Elchin
 */
public class UniverseViewer extends JPanel {

    private int shiftX = 0;
    private int shiftY = 0;
    
    protected Point draggedPoint = null;
    protected Point selectionPoint = null;
    protected UniverseView universeView = null;
    protected Rectangle selectionRect = null;
    protected UniverseObject draggedObject = null;
    protected List<UniverseObject> draggedObjects = new ArrayList<>();
    protected Map<String, Renderer> rendererMap = new HashMap<>();


    public UniverseViewer(UniverseView universeView) {
        this(universeView, null);
    }
    
    /**
     * 
     * @param universeView 
     * @param size 
     */
    public UniverseViewer(UniverseView universeView, Dimension size) {
        super();
        this.universeView = universeView;
        universeView.registerViewer(this);

        addMouseListener(new ViewerMouseListener());
        addMouseMotionListener(new ViewerMouseMotionListener());

        //
        setLayout(new BorderLayout());
        JPanel container = new TransparentPanel();

        container.add(BorderLayout.SOUTH, new BirdView(this));
        add(BorderLayout.EAST, container);
        
        if(null != size) {
            setPreferredSize(size);
        }
    }

    public void addRenderer(String name, Renderer renderer) {
        rendererMap.put(name, renderer);
    }

    public UniverseView getView() {
        return universeView;
    }    
    
    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
        Dimension d = getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, d.width, d.height);

        for (UniverseObject object : draggedObjects) {        
            universeView.getAttributes(object).setSelected(true);
        }
        
        // draw all objects
        List<UniverseObject> objects = universeView.getObjects();
        for (UniverseObject object : objects) {
            Attributes attributes = universeView.getAttributes(object);
            if (rendererMap.containsKey(object.getType())) {
                Renderer renderer = (Renderer) rendererMap.get(object.getType());
                renderer.render(g, object, attributes);
            }
            
            // all temporary attributes like selection should be removed
            attributes.clearTemporaryAttributes();
        }       
        
        // draw selection region
        if (null != selectionPoint && null != draggedPoint) {
            g.setColor(Color.BLACK);
            Rectangle selection = getRect(draggedPoint, selectionPoint);
            g.drawRect(selection.x,
                    selection.y,
                    selection.width,
                    selection.height);
        }

        super.paintComponents(g);
    }

    /**
     * 
     */
    private class ViewerMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point point = e.getPoint();

            if (!(null != selectionRect && selectionRect.contains(point))) {
                draggedObjects.clear();
            }

            for (UniverseObject object : universeView.getObjects()) {
                if (universeView.getAttributes(object).contains(point)) {
                    draggedPoint = point;
                    Attributes attributes = universeView.getAttributes(object);
                    shiftX = point.x - attributes.getPosition().x;
                    shiftY = point.y - attributes.getPosition().y;

                    draggedObject = object;
                    break;
                }
            }

            // No picked component, let's draw a selection
            if (null == draggedObject) {
                selectionPoint = point;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // mark components as selected
            Point point = e.getPoint();
            if (null != selectionPoint) {
                selectionRect = getRect(point, selectionPoint);

                for (UniverseObject object : universeView.getObjects()) {
                    if (selectionRect.contains(universeView.getAttributes(object).getObjectRectangle())) {
                        draggedObjects.add(object);
                    }
                }
                // scan for other objects
            }

            draggedPoint = null;
            draggedObject = null;
            selectionPoint = null;

            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private static Rectangle getRect(Point p1, Point p2) {
        int x = (p1.x < p2.x) ? p1.x : p2.x;
        int y = (p1.y < p2.y) ? p1.y : p2.y;
        int width = Math.abs(p2.x - p1.x);
        int height = Math.abs(p2.y - p1.y);

        Rectangle result = new Rectangle(x,
                y,
                width,
                height);

        return result;
    }

    /**
     * 
     */
    private class ViewerMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            draggedPoint = e.getPoint();
            Point current = new Point(draggedPoint.x - shiftX, draggedPoint.y - shiftY);

            if (null != draggedObject) {
                Point previous = universeView.getAttributes(draggedObject).getPosition();

                universeView.getAttributes(draggedObject).setPosition(current);

                int dX = current.x - previous.x;
                int dY = current.y - previous.y;

                for (UniverseObject object : draggedObjects) {
                    if (object != draggedObject) {
                        universeView.getAttributes(object).getPosition().x += dX;
                        universeView.getAttributes(object).getPosition().y += dY;
                    }
                }
            }

            universeView.updateAllViewers();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
