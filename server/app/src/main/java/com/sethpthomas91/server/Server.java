package com.sethpthomas91.server;

//1. Set the port
//2. Open/Listen on a socket
//3. Bind a client connection
//4. Echo a message from client
//5. Close the same socket

import java.io.IOException;

public class Server {
    public boolean wasStarted = false;
    public int port;
    public ServerSocketInterface serverSocket;

    public Server(ServerSocketInterface serverSocket) {
        this.serverSocket = serverSocket;
    }

    public boolean started() {
        return wasStarted;
    }

    public void start() throws IOException {
        wasStarted = true;
        serverSocket.startSocketAt(port);
    }

    public void close() throws IOException {
        wasStarted = false;
        serverSocket.closeSocket();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int newPort){
        this.port = newPort;
    }
}