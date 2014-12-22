/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.ui.universe.impl;

import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseObject;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Artem.Elchin
 */
public abstract class AbstractUniverse implements Universe {
    protected List<UniverseObject> objects = new ArrayList<UniverseObject>();

    public List<UniverseObject> getObjects() {
	return objects;
    }

    public void addObject(UniverseObject object) {
	objects.add(object);
    }
    
    @Override
    public int getObjectCount() {
        return objects.size();
    }

    @Override
    public UniverseObject getObject(int index) {
        return objects.get(index);
    }    
}
