/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Artem.Elchin
 */
public class ToolsPanel extends JPanel {

    public ToolsPanel() {
        super(new BorderLayout());
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Elements", new JPanel());
        pane.addTab("Clusters", new JPanel());
        
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(300, 200));
        add(BorderLayout.CENTER, pane);
        add(BorderLayout.SOUTH, panel1);
    }
    
}
