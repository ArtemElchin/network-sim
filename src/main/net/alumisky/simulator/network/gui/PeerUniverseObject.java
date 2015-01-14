/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui;

import com.alumisky.ui.universe.UniverseObject;
import net.alumisky.simulator.network.Peer;
import net.alumisky.simulator.network.PeerID;

/**
 *
 * @author Renat Gilmanov
 */
public class PeerUniverseObject extends AbstractNetworkObject<PeerID>
    implements UniverseObject {
    public static final String TYPE = "network.peer";
    public final boolean disableMode=false;
    public final boolean errorMode=false;
    private Peer peer;

    public PeerUniverseObject(Peer peer) {
        super(peer);
        this.peer = peer;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
