package com.sethpthomas91.server;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ServerTest {
    @Test
    public void testStartsServerSocket() throws IOException {
        MockServerSocketWrapper wrapper = new MockServerSocketWrapper();
        Server server = new Server(wrapper);
        server.start();
        Assert.assertTrue(server.started());
        server.close();
    }

    @Test
    public void testServerSocketIsClosed() throws IOException {
        MockServerSocketWrapper wrapper = new MockServerSocketWrapper();
        Server server = new Server(wrapper);
        server.start();
        server.close();
        Assert.assertFalse(server.started());
    }

    @Test
    public void testServerAcceptsCustomPort() throws IOException {
        int customPort = 4601;
        MockServerSocketWrapper wrapper = new MockServerSocketWrapper();
        Server server = new Server(wrapper);
        server.setPort(customPort);
        server.start();
        Assert.assertEquals(customPort, server.getPort());
        server.close();
    }

    @Test
    public void testServerEchosInputToOutput() throws IOException {
        String input = "echo";
        BufferedReader in = new BufferedReader(new StringReader(input));
        PrintWriter out = new PrintWriter(new StringWriter(), true);
        MockServerSocketWrapper wrapper = new MockServerSocketWrapper();
        wrapper.setIn(in);
        wrapper.setOut(out);
        Server server = new Server(wrapper);
        server.start();
        Assert.assertEquals(wrapper.getLastOutput(), input);
    }

    @Test
    public void testServerSocketWrapperCreatesSocket() throws IOException {
        ServerSocketWrapper serverSocket = new ServerSocketWrapper();
        int port = 5010;
        serverSocket.startSocketAt(port);
        Assert.assertTrue(serverSocket.isConnected());
    }

    @Test
    public void testServerSocketStartsSocketWhenStartIsCalled() throws IOException {
        int port = 5011;
        MockServerSocketWrapper serverSocket = new MockServerSocketWrapper();
        Server server = new Server(serverSocket);
        server.setPort(port);
        server.start();
        Assert.assertTrue(serverSocket.serverSocketWasCreated());
        server.close();
    }

}