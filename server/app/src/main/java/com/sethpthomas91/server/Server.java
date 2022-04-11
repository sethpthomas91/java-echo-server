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

    public static String getLocalIPAddress() throws UnknownHostException {
        String localIPAddress = Inet4Address.getLocalHost().getHostAddress();
        return localIPAddress;
    }

    public void startServer(){

        System.out.println(String.format("[LISTING TO PORT %s]", PORT));

        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader input = new BufferedReader(inputStreamReader);
                ) {

            String inputLine;
            String outputLine;

            HandleClientProtocol handler = new HandleClientProtocol();
            outputLine = handler.processInput(null);
            output.println(outputLine);

            System.out.println("[Connection Established]");

            while ((inputLine = input.readLine()) != null) {
                System.out.println(String.format("[INCOMING MESSAGE]: %s", inputLine));
                outputLine = handler.processInput(inputLine);
                System.out.println(String.format("[OUTGOING MESSAGE]: %s", outputLine));
                System.out.println("DISCONNECTING");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
