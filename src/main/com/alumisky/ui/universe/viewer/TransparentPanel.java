/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.viewer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Artem.Elchin
 */
public class TransparentPanel extends JPanel {

    /**
     * 
     */
    public TransparentPanel() {
        super(new BorderLayout());
    }

    /**
     * 
     * @param g 
     */
    public void paint(Graphics g) {
        super.paintComponents(g);
    }
}
