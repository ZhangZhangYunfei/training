module com.greetings {
    requires com.astro;
    provides com.astro.spi.NetworkSocketProvider
            with com.greetings.FastNetworkSocketProvider;
}