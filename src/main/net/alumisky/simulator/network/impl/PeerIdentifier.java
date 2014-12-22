/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.impl;

import java.util.HashMap;
import java.util.Map;
import net.alumisky.simulator.network.PeerID;

/**
 *
 * @author Artem.Elchin
 */
public class PeerIdentifier extends AbstractIdentifier implements PeerID {

    /**
     * 
     * @param uid
     */
    public PeerIdentifier(long uid) {
        super(uid);
    }

    public String toString() {
        return "peer:"+Long.toHexString(getUID());
    }

//    public static void main(String[] args) {
//        PeerID id1 = new PeerIdentifier(1);
//        PeerID id2 = new PeerIdentifier(1);
//
//        Map map = new HashMap();
//        map.put(id1, null);
//
//        System.out.println(map.containsKey(id2));
//    }
}
