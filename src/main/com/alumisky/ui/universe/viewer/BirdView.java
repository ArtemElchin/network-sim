/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer;

import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Renat Gilmanov
 */
public class BirdView extends JPanel {

    private BirdViewRenderer renderer;
    private UniverseView universeView;
    private UniverseViewer universeViewer;
    private Dimension size;
    private int viewFactor = 2;

    public BirdView(UniverseViewer universeViewer) {
        super();
        this.universeViewer = universeViewer;
        this.universeView = universeViewer.getView();
        this.size = new Dimension(300, 200);
        this.renderer = new BirdViewRenderer();
        setViewSize(size);
    }

    public void setViewSize(Dimension size) {
        this.size = size;
        setPreferredSize(getScaledDimension());
        invalidate();
    }

    public Dimension getScaledDimension() {
        return new Dimension(size.width / viewFactor,
                size.height / viewFactor);
    }

    public int getScaledWidth() {
        return size.width / viewFactor;
    }

    public int getScaledHeight() {
        return size.height / viewFactor;
    }

    @Override
   public void paint(Graphics g) {
        Dimension viewSize = universeViewer.getSize();
        Dimension parentSize = universeView.getWorldDimension();
        
        renderer.setScale(((double) size.width/(viewSize.width*viewFactor)), ((double)size.height/(viewSize.height*viewFactor)));


        double scale = 1.0;
        int swidth = getScaledWidth();
        int sheight = getScaledHeight();

        if (swidth < sheight) {
            scale = ((double) swidth / parentSize.width);
        } else {
            scale = ((double) swidth / parentSize.height);
        }

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getScaledWidth(), getScaledHeight());

        // -- border
        int width = (int) (scale * viewSize.width);
        int height = (int) (scale * viewSize.height);

        int x = (swidth - width) / 2;
        int y = (sheight - height) / 2;

        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);

        List<UniverseObject> objects = universeView.getObjects();

        for (UniverseObject object : objects) {
            renderer.render(g, object, universeView.getAttributes(object));
//                if (rendererMap.containsKey(object.getType())) {
//                    Renderer renderer = (Renderer) rendererMap.get(object.getType());
//                    renderer.render(g, object, universeView.getAttributes(object));
//                }

        }

        //universeView.

    }
}