/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network;

import net.alumisky.simulator.network.exception.NetworkException;

/**
 * Network gateway abstraction.
 *
 * @author Renat.Hilmanov
 */
public interface Gateway extends Peer {

    /**
     * Link to specified network.
     *
     * @param network network this gateway to be linked to
     * @throws NetworkException if case of error
     */
    void link(NetworkID network) throws NetworkException;
}
