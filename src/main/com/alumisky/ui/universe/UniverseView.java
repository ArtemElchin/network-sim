/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe;

import java.util.List;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * 
 * @author Artem.Elchin
 */
public interface UniverseView {

    /**
     * 
     * @return
     */
    List<UniverseObject> getObjects();

    /**
     * 
     * @param viewer
     */
    void registerViewer(JPanel viewer);

    /**
     * 
     */
    void updateAllViewers();

    /**
     * 
     * @param object
     * @return
     */
    Attributes getAttributes(UniverseObject object);
    
    /**
     * 
     * @param object
     * @param attr 
     */
    void addAttributes(UniverseObject object, Attributes attr);    
    
    
    Dimension getWorldDimension();
}


