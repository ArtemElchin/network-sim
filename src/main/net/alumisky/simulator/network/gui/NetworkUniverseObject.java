/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.gui;

import com.alumisky.ui.universe.UniverseObject;
import net.alumisky.simulator.network.Network;
import net.alumisky.simulator.network.NetworkID;

/**
 *
 * @author Renat Gilmanov
 */
public class NetworkUniverseObject extends AbstractNetworkObject<NetworkID>
implements UniverseObject {
    public static final String TYPE = "network.network";

    private Network network;

    public NetworkUniverseObject(Network network) {
        super(network);
        this.network = network;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
