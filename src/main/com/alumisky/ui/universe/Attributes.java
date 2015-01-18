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
public interface Attributes {

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
    
    /**
     * 
     * @return 
     */
    boolean isSelected();
    
    /**
     * 
     * @param value 
     */
    void setSelected(boolean value);
    
    /**
     * 
     */
    void clearTemporaryAttributes();
    
    /**
     * 
     * @return 
     */
    boolean isMultilineEnabled();
}
