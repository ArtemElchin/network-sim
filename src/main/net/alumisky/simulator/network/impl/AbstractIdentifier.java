/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package net.alumisky.simulator.network.impl;

/**
 *
 * @author Artem.Elchin
 */
public class AbstractIdentifier {
    private long uid = 0;

    public AbstractIdentifier(long uid) {
        this.uid = uid;
    }

    public long getUID() {
        return uid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractIdentifier other = (AbstractIdentifier) obj;
        if (this.uid != other.uid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.uid ^ (this.uid >>> 32));
        return hash;
    }
}
