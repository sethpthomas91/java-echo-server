/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.sethpthomas91.server;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(new App().getGreeting());
    }


}
