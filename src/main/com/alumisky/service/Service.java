/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.service;

import com.alumisky.common.Stopable;


/**
 *
 * @author Artem.Elchin
 */
public interface Service extends Stopable {
    String getName();
}
