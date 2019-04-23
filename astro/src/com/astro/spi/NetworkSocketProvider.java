package com.astro.spi;

import com.astro.NetworkSocket;

public abstract class NetworkSocketProvider {
    protected NetworkSocketProvider() {
    }

    public abstract NetworkSocket openNetworkSocket();
}
