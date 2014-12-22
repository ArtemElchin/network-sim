/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe;

import java.awt.Graphics;

/**
 * 
 * 
 * @param <T>
 */
public interface Renderer<T extends UniverseObject> {

    /**
     * 
     * @param g
     * @param object
     * @param view
     */
    void render(Graphics g, T object, Attributes view);
}


