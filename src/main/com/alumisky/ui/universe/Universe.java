/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe;

import java.util.List;

/**
 * Defines Universe contract. Universe can contain some objects and use
 * specific physical rules to define object behaviour and interactions.
 * 
 * @author Artem.Elchin
 */
public interface Universe<T extends UniverseObject> {

    /**
     * Returns list of universe objects.
     * @deprecated 
     *
     * @return list of existing objects
     */
    List<T> getObjects();

    /**
     * 
     * @return 
     */
    int getObjectCount();
    
    /**
     * 
     * @param index
     * @return 
     */
    T getObject(int index);
    
    /**
     * Add object to the Universe
     *
     * @param object object to be added
     */
    void addObject(T object);
}
