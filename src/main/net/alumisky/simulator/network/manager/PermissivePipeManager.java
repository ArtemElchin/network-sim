/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.manager;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.PeerID;

/**
 *
 * @author Artem.Elchin
 */
public class PermissivePipeManager implements PipeManager{

    public boolean canDeliver(Message message, PeerID peerID, int port) {
        return true;
    }

}
