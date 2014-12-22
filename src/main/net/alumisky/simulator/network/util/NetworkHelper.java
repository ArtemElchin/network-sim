/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */


package net.alumisky.simulator.network.util;

import net.alumisky.simulator.network.Message;
import net.alumisky.simulator.network.MessagePayload;
import net.alumisky.simulator.network.PayloadMessage;
import net.alumisky.simulator.network.PeerID;
import net.alumisky.simulator.network.message.PortAnnouncePayload;

import net.alumisky.simulator.network.message.StringPayload;

/**
 *
 * @author Artem.Elchin
 */
public final class NetworkHelper {

    public static Message createPortAnnounce(int port) {
        return new PayloadMessage(new PortAnnouncePayload(port));
    }

    public static Message createMessage (String payload) {
        return new PayloadMessage(new StringPayload(payload));
    }

    public static Message createMessage (MessagePayload payload) {
        return new PayloadMessage(payload);
    }


    public static Message createMessage (PeerID destinationID, MessagePayload payload) {
        return new PayloadMessage(destinationID, payload);
    }

    private NetworkHelper() {
        
    }
}
