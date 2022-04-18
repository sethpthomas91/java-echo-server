package com.sethpthomas91.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockServerSocketWrapper implements ServerSocketInterface{
    public BufferedReader in;
    public PrintWriter out;
    private boolean serverSocketWasCreated = false;

    public MockServerSocketWrapper() {
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    private String turnOutToString(BufferedReader input) throws IOException {
        return input.readLine();
    }

    @Override
    public String getLastOutput() throws IOException {
        return turnOutToString(this.in);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void startSocketAt(int port) {
        serverSocketWasCreated = true;
    }

    @Override
    public void closeSocket() throws IOException {
    }

    public boolean serverSocketWasCreated() {
        return serverSocketWasCreated;
    }
}
