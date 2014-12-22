/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui.renderer;

import com.alumisky.ui.universe.Attributes;
import com.alumisky.ui.universe.Renderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import net.alumisky.simulator.network.gui.PeerUniverseObject;

/**
 *
 * @author Renat Gilmanov
 */
public class DefaultPeerRenderer implements Renderer<PeerUniverseObject> {

    @Override
    public void render(Graphics g, PeerUniverseObject object, Attributes view) {
        g.setColor(Color.BLUE);
	Rectangle r = view.getObjectRectangle();
	g.fillRect(r.x, r.y, r.width, r.height);
        g.drawString(object.getName(), r.x, r.y);
    }

}
