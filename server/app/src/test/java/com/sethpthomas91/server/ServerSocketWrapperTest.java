package com.sethpthomas91.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;

import static org.junit.Assert.*;

public class ServerSocketWrapperTest {
    private ServerSocketWrapper serverSocket;

    @Before
    public void setUp() throws Exception {
        serverSocket = new ServerSocketWrapper(5050);
    }

    @Test
    public void sendMessageToClient() throws Exception {
//        Trying to get this integration test working.
//        String message = "Hello";
//        Socket socket = new Socket(Inet4Address.getLocalHost().getHostName(), 5050);
//        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
//        BufferedReader input = new BufferedReader(inputStream);
//        String incomingMessage = input.readLine();
//        serverSocket.sendMessageToClient(message);
//        Assert.assertEquals(message, incomingMessage);
    }

    @Test
    public void receiveMessageFromClient() {
    }
}