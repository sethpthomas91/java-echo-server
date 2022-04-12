package com.sethpthomas91.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    public int PORT = 5050;
    public String localIPAddress = getLocalIPAddress();

    public Server() throws UnknownHostException {
    }

    public void startServer() throws Exception {

        System.out.println(String.format("[LISTING TO PORT %s]", PORT));

        try (
                ServerSocket serverSocket = createServerSocket(PORT);
                Socket clientSocket = createClientSocket(serverSocket);
                PrintWriter writerToClient = createPrintWriterToClient(clientSocket);
                BufferedReader inputFromClient = createInputFromClientReader(clientSocket);
                ) {

            String inputLine;
            String outputLine;

            HandleClientProtocol handler = new HandleClientProtocol();
            outputLine = handler.processInput(null);
            writerToClient.println(outputLine);

            System.out.println("[Connection Established]");

            while ((inputLine = inputFromClient.readLine()) != null) {
                System.out.println(String.format("[INCOMING MESSAGE]: %s", inputLine));
                outputLine = handler.processInput(inputLine);
                writerToClient.println(outputLine);
                System.out.println(String.format("[OUTGOING MESSAGE]: %s", outputLine));

            }
        } catch (IOException error) {
            System.out.println(String.format("Exception caught when listening to port %s or listening for a connection.", PORT));
            throw new IOException(error.getMessage());
        }
    }

    private ServerSocket createServerSocket(int PORT) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        return serverSocket;
    }

    private Socket createClientSocket(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    private PrintWriter createPrintWriterToClient(Socket clientSocket) throws IOException {
        PrintWriter writerToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        return writerToClient;
    }

    private BufferedReader createInputFromClientReader(Socket clientSocket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader inputFromClient = new BufferedReader(inputStreamReader);
        return inputFromClient;
    }

    private static String getLocalIPAddress() throws UnknownHostException {
        String localIPAddress = Inet4Address.getLocalHost().getHostAddress();
        return localIPAddress;
    }

}
