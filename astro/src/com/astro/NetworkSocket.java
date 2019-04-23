package com.astro;

import com.astro.spi.NetworkSocketProvider;

import java.io.Closeable;
import java.util.Iterator;
import java.util.ServiceLoader;

public abstract class NetworkSocket implements Closeable {
    protected NetworkSocket() {
    }

    public static NetworkSocket open() {
        ServiceLoader<NetworkSocketProvider> sl
                = ServiceLoader.load(NetworkSocketProvider.class);
        Iterator<NetworkSocketProvider> iter = sl.iterator();
        if (!iter.hasNext())
            throw new RuntimeException("No service providers found!");
        NetworkSocketProvider provider = iter.next();
        return provider.openNetworkSocket();
    }
}
