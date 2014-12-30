/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import com.alumisky.ui.universe.UniverseObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Renat Gilmanov
 */
public class BirdViewRenderer implements Renderer {

    private double scaleWigth = 1.0;
    private double scaleHeight = 1.0;
    public void setScale(double scaleWight, double scaleHeight) {
        this.scaleWigth = scaleWight;
        this.scaleHeight= scaleHeight;
    }

    @Override
    public void render(Graphics g, UniverseObject object, Attributes view) {
        g.setColor(Color.BLUE);
        Rectangle r = view.getObjectRectangle();
        g.fillRect((int) (r.x * scaleWigth),
                (int) (r.y * scaleHeight),
                (int) (r.width * scaleWigth),
                (int) (r.height * scaleHeight));
    }
}
