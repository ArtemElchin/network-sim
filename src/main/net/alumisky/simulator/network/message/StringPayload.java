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
public class StringPayload implements MessagePayload {
    private String value;

    public StringPayload(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "string-payload: " + value;
    }
}
