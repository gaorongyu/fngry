package com.gry.test.server;

/**
 * Created by gaorongyu on 14-9-29.
 */
public class TestServer {

    public static void main(String[] args) {

        Simulator simulator = new Simulator();
        int i = 1;

        while(true) {
            Integer second = simulator.process(i);
            System.out.println("    simulator return second is: " + second);
            System.out.println("    ");

            i = i + 1;
        }

    }

}
