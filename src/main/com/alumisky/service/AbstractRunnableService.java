/*
 * Copyright 2009 AlumiSky (http://alumisky.net). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.alumisky.service;

import com.alumisky.util.concurrent.ConcurrentHelpers;

/**
 *
 * @author Artem.Elchin
 */
public abstract class AbstractRunnableService implements Service, Runnable {
    private Thread thread = null;
    protected static final int EMPTY_CYCLES_DELAY = 10;
    protected static final String UNDEFINED_NAME = "undefined";

    protected String name;

    public AbstractRunnableService() {
        this(UNDEFINED_NAME);
    }
    
    public AbstractRunnableService(String name) {
        this.name = name;
    }

    @Override
    public void start() throws ServiceException {
        if (null != thread) {
            throw new IllegalStateException("Service already started");
        }

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() throws ServiceException {
        thread = null;
    }

    public boolean isActive() {
        return null != thread;
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        onStart();
        while (thread == thisThread) {
            if(!process()) {
                break;
            }else{
                ConcurrentHelpers.sleep(EMPTY_CYCLES_DELAY);
            }
        }
        thread = null;
        onStop();
    }

    /**
     * 
     * @return
     */
    public abstract boolean process();

    /**
     * 
     */
    public void onStart() { }

    /**
     * 
     */
    public void onStop() { }
}
