/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.message;

import net.alumisky.simulator.network.MessagePayload;

/**
 *
 * @author Artem.Elchin
 */
public class PortAnnouncePayload implements MessagePayload{
    private int value;

    public PortAnnouncePayload(int port) {
        this.value = port;
    }

    public int getPort() {
        return value;
    }

    public String toString() {
        return "port-announce-payload: " + value;
    }
}











































