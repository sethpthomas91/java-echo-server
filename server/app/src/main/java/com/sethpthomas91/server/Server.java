package com.sethpthomas91.server;

import java.io.IOException;
import java.net.UnknownHostException;

public class Server {
    public int PORT = 5050;

    public Server() throws UnknownHostException {
    }

    public void startServer() throws Exception {
        try (
                ServerSocketWrapper serverSocket = new ServerSocketWrapper(PORT);
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

}
