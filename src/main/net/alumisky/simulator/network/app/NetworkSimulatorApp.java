/*
 * Copyright 2009-2015 AlumiSky (http://alumisky.net). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.alumisky.simulator.network.app;

import com.taunova.event.Event;
import com.taunova.event.EventUtil;
import com.taunova.event.Listener;
import net.taunova.app.SingleViewApplication;

import org.jdesktop.application.Action;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.alumisky.simulator.network.app.event.DemoEvent;

/**
 *
 * @author Artem.Elchin
 */
public class NetworkSimulatorApp extends SingleViewApplication {

    public NetworkSimulatorApp() {
        super();
        setMenuBarEnabled(true);
        setToolBarEnabled(true);
        setStatusBarEnabled(false);
    }

    @Override
    protected void initialize(String[] args) {
        // check resources
        Runtime.getRuntime().addShutdownHook(new Thread(
                new Runnable() {

                    public void run() {
                        // empty shutdown hook
                    }
                }));
    }

    protected JComponent buildContent() {
        JPanel content = new JPanel(new BorderLayout());

        subscribeEvents();

        return content;
    }

    private Object demoSubscription;

    protected void subscribeEvents() {
        demoSubscription = EventUtil.subscribe(DemoEvent.class, new Listener() {

            @Override
            public void onEvent(Event event) {
            }
        });
    }

    // --- Actions ------------------------------------------------------
    @Action
    public void newItem() {
        System.out.println("New item called");
    }

    // --- Entry point --------------------------------------------------
    public static void main(String[] args) {
        launch(NetworkSimulatorApp.class, args);
    }
}
