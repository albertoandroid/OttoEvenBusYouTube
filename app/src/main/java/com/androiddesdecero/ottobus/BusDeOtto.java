package com.androiddesdecero.ottobus;

import com.squareup.otto.Bus;

/**
 * Created by albertopalomarrobledo on 11/4/18.
 */

public class BusDeOtto {

    private static Bus bus;

    public static Bus getBus(){
        if(bus == null){
            bus = new Bus();
        }
        return bus;
    }
}
