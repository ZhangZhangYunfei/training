package com.greetings;

import com.socket.NetworkSocket;
import com.astro.World;

public class Main {
    public static void main(String[] args) {
        System.out.format("Greetings %s!%n", World.name());

        NetworkSocket s = NetworkSocket.open();
        System.out.println(s.getClass());
    }
}
