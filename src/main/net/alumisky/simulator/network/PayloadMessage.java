/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 *
 * @author Artem.Elchin
 */
public class PayloadMessage implements Message {

    private PeerID source;

    private MessagePayload payload;

    public PayloadMessage(MessagePayload payload) {
        this.payload = payload;
    }

    public PayloadMessage(PeerID source, MessagePayload payload) {
        this.source = source;
        this.payload = payload;
    }
    
    public PeerID getSource() {
        return source;
    }

    public MessagePayload getPayload() {
        return payload;
    }

    public void setSource(PeerID id) {
        this.source = id;
    }
}
