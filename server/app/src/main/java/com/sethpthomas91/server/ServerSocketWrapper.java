package com.sethpthomas91.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements AutoCloseable{

    private ServerSocket serverSocket;
    private Socket clientSocket;
    public PrintWriter writerToClient;
    public BufferedReader inputFromClient;

    public ServerSocketWrapper(int PORT) throws IOException {
        this.startServerSocketAt(PORT);
        this.connectClientToServerSocket(serverSocket);
        this.createPrintWriterToClient(clientSocket);
        this.createInputFromClientReader(clientSocket);
    }

    public void sendMessageToClient(String message) {
        System.out.println(String.format("[OUTGOING MESSAGE]: %s", message));
        writerToClient.println(message);
    }

    public String receiveMessageFromClient() throws IOException {
        String message = inputFromClient.readLine();
        System.out.println(String.format("[INCOMING MESSAGE]: %s", message));
        return message;
    }

    private ServerSocket startServerSocketAt(int PORT) throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println(String.format("[LISTING TO PORT %s]", PORT));
        return serverSocket;
    }

    private Socket connectClientToServerSocket(ServerSocket serverSocket) throws IOException {
        clientSocket = serverSocket.accept();
        System.out.println("[Connection Established]");
        return clientSocket;
    }

    private PrintWriter createPrintWriterToClient(Socket clientSocket) throws IOException {
        writerToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        return writerToClient;
    }

    private BufferedReader createInputFromClientReader(Socket clientSocket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        inputFromClient = new BufferedReader(inputStreamReader);
        return inputFromClient;
    }

    @Override
    public void close() throws Exception {

    }
}
