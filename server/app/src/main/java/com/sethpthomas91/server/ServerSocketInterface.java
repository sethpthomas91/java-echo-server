package com.sethpthomas91.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketInterface {
    String getLastOutput() throws IOException;
    boolean isConnected();
    void startSocketAt(int port) throws IOException;
    void closeSocket() throws IOException;
    Socket connectToClient(ServerSocket serverSocket) throws IOException;
    void createClientReader() throws IOException;
}
