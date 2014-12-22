/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.impl;

import net.alumisky.simulator.network.NetworkID;

/**
 *
 * @author Artem.Elchin
 */
public class NetworkIdentifier extends AbstractIdentifier implements NetworkID {

    public NetworkIdentifier(long uid) {
        super(uid);
    }

    public String toString() {
        return "network:"+Long.toHexString(getUID());
    }

}
