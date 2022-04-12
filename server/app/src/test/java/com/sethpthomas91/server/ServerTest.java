package com.sethpthomas91.server;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.*;

public class ServerTest {
    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IOException.class)
    public void whenPortIsInUseServerThrowsAnIOException() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5050);
        server.startServer();
    }
}