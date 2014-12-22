/**
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.alumisky.ui.universe.impl;

import com.alumisky.ui.universe.Universe;
import com.alumisky.ui.universe.UniverseView;

/**
 *
 * @author Renat Gilmanov
 */
public class UniverseFactory {
    public static Universe createDefaultUniverse() {
        return new DefaultUniverseImpl();
    }    

    public static UniverseView createUniverseView(Universe universe) {
        return new DefaultUniverseViewImpl(universe);
    }
}
