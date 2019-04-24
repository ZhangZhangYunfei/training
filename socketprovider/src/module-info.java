module com.socketprovider {
    requires com.socket;

    provides com.socket.spi.NetworkSocketProvider
            with com.socketprovider.FastNetworkSocketProvider;
}