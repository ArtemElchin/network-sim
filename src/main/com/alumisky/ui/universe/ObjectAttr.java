/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.ui.universe;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Artem.Elchin
 */
public class ObjectAttr implements Attributes {
    private Point position = new Point(20, 30);

    protected int width  = 80;
    protected int height = 30;
    
    public ObjectAttr(int width, int height) {
	this.width = width;
	this.height = height;
    }

    public ObjectAttr(Point position) {
        this.position = position;
    }
    
    public Point getPosition() {
	return position;
    }

    public void setPosition(Point position) {
	this.position = position;
    }

    public boolean contains(Point point) {
	return getObjectRectangle().contains(point);
    }

    public Rectangle getObjectRectangle() {
	return new Rectangle(position.x, position.y, width, height);
    }
}
