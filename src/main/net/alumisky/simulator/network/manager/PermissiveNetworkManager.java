/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.manager;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.Peer;

/**
 *
 * @author Artem.Elchin
 */
public class PermissiveNetworkManager implements NetworkManager {

    public boolean canDeliverMulticast(Message message, Peer peer) {
        return true;
    }

    public boolean canDeliverMessage(Message message, Peer peer) {
        return true;
    }

}
