/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui;

import com.alumisky.ui.universe.UniverseObject;
import net.alumisky.simulator.network.Gateway;
import net.alumisky.simulator.network.PeerID;

/**
 *
 * @author Renat Gilmanov
 */
public class GatewayUniverseObject  extends AbstractNetworkObject<PeerID>
implements UniverseObject {
    public static final String TYPE = "network.gateway";

    private Gateway gateway;

    public GatewayUniverseObject(Gateway gateway) {
        super(gateway);
        this.gateway = gateway;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
