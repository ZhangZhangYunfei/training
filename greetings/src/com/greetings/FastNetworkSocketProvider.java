package com.greetings;

import com.astro.NetworkSocket;
import com.astro.spi.NetworkSocketProvider;

public class FastNetworkSocketProvider extends NetworkSocketProvider {
    public FastNetworkSocketProvider() {
    }

    @Override
    public NetworkSocket openNetworkSocket() {
        return new FastNetworkSocket();
    }
}