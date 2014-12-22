/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.ui.universe.impl;

import com.alumisky.ui.universe.ObjectAttr;
import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseObject;
import com.alumisky.ui.universe.UniverseView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author renat
 */
public abstract class AbstractUniverseView implements UniverseView {
    protected Universe world = null;
    protected Map viewMap = new HashMap();
    protected List viewers = new LinkedList();
    protected Dimension worldDimension = new Dimension();
    
    public AbstractUniverseView(Universe world) {
	this.world = world;
    }

    @Override
    public Attributes getAttributes(UniverseObject object) {
	if(viewMap.containsKey(object)) {
	    return (Attributes)viewMap.get(object);
	}else{
	    Attributes attr = new ObjectAttr(40,20);
	    viewMap.put(object, attr);
	    return attr;
	}
    }

    @Override
    public void addAttributes(UniverseObject object, Attributes attr) {
        viewMap.put(object, attr);
        Rectangle r = attr.getObjectRectangle();
        
        int x = r.x + r.width;
        int y = r.y + r.height;        
        
        if(x > worldDimension.width) {
            worldDimension.width = x;
        }        

        if(y > worldDimension.width) {
            worldDimension.width = y;
        }                
    }    
    
    @Override
    public List getObjects() {
	return world.getObjects();
    }

    @Override
    public void registerViewer(JPanel viewer) {
	viewers.add(viewer);
    }

    public Dimension getWorldDimension() {
        return worldDimension;
    }
    
    @Override
    public void updateAllViewers() {
	Iterator iterator = viewers.iterator();
	while(iterator.hasNext()) {
	    JPanel panel = (JPanel)iterator.next();
	    panel.repaint();
	}
    }
}
