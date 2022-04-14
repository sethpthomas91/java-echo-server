package com.sethpthomas91.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketWrapper implements ServerSocketInterface{
    private boolean connected = false;
    private ServerSocket serverSocket;


    @Override
    public String getLastOutput() throws IOException {
        return null;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void startSocketAt(int port) throws IOException {
        connected = true;
        serverSocket = new ServerSocket(port);
    }

}
