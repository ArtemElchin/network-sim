/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.ui.universe;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author Artem.Elchin
 */
public interface Attributes {
     boolean multilineSupport=true;

    /**
     *
     * @return
     */
    Point getPosition();

    /**
     *
     * @param position
     */
    void setPosition(Point position);

    /**
     *
     * @param point
     * @return
     */
    boolean contains(Point point);

    /**
     * 
     * @return
     */
    Rectangle getObjectRectangle();
    
    
    RoundRectangle2D getObjectRoundRectangle2D();
}
