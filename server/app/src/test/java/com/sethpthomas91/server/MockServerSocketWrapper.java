package com.sethpthomas91.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocketWrapper implements ServerSocketInterface{
    public BufferedReader in;
    public PrintWriter out;
    private boolean listeningForClient = false;
    private boolean serverSocketWasCreated = false;
    private boolean clientReaderCreated = false;

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

    @Override
    public Socket connectToClient(ServerSocket serverSocket) throws IOException {
        listeningForClient = true;
        createClientReader();
        return null;
    }

    @Override
    public void createClientReader() throws IOException {
        clientReaderCreated = true;
    }

    public boolean serverSocketWasCreated() {
        return serverSocketWasCreated;
    }

    public boolean serverIsListening() {
        return listeningForClient;
    }

    public boolean readerCreated() {
        return clientReaderCreated;
    }

}
