/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe;

import java.awt.Graphics;
import java.awt.Shape;

/**
 *
 * @param <T>
 *
 * @author Artem.Elchin
 */
public abstract class AbstractUniverseRenderer<T extends UniverseObject>
        implements Renderer<T> {

    @Override
    public abstract void render(Graphics g, T object, Attributes view);

    @Override
    public Shape getObjectShape(T object, Attributes attr) {
        return attr.getObjectRectangle();
    }

}
