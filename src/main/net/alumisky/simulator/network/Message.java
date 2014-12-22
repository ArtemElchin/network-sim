/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network;

/**
 * The information transmitted using pipes and between endpoints is packaged
 * as messages. Messages define an envelope to transfer any kind of data. A message
 * may contain an arbitrary number of named sub-sections which can hold any form of data.
 *
 * @author Renat.Hilmanov
 */
public interface Message {

    /**
     * Returns if of the message source.
     *
     * @return source id
     */
    PeerID getSource();

    /**
     * Set messages source.
     *
     * @param id id of the source (peer)
     */
    void setSource(PeerID id);

    /**
     * Get message payload.
     *
     * @return message payload
     */
    MessagePayload getPayload();
}
