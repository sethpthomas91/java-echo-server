package com.sethpthomas91.server;

import java.io.IOException;
import java.net.UnknownHostException;

public class Server implements AutoCloseable {
    public int PORT = 5050;
    private final ServerSocketWrapper serverSocket;

    public Server(ServerSocketWrapper serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() throws Exception {
        try (
                serverSocket
        ) {

            String messageFromClient;
            String messageToClient;

            HandleClientProtocol handler = new HandleClientProtocol();
            messageToClient = handler.processInput(null);

            serverSocket.sendMessageToClient(messageToClient);

            while ((messageFromClient = serverSocket.receiveMessageFromClient()) != null) {
                messageToClient = handler.processInput(messageFromClient);
                serverSocket.sendMessageToClient(messageToClient);
            }
        } catch (IOException error) {
            System.out.println(String.format("Exception caught when listening to port %s or listening for a connection.", PORT));
            throw new IOException(error.getMessage());
        }
    }

    @Override
    public void close() throws Exception {

    }
}
