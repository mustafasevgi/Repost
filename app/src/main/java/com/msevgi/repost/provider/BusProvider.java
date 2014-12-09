package com.msevgi.repost.provider;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by msevgi on 12/9/2014.
 */
public class BusProvider {

    private static Bus bus = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance() {
        return bus;
    }
}
