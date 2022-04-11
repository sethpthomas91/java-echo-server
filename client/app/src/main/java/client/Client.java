package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    String LOCAL_HOST_NAME = Inet4Address.getLocalHost().getHostName();
    int PORT = 5050;

    public Client() throws IOException {
    }

    public void startClient() throws IOException {
        try (
                Socket socket = new Socket(LOCAL_HOST_NAME, PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
                BufferedReader input = new BufferedReader(inputStream);
        ) {
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = input.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    output.println(fromUser);
                }
            }

        }
    }
}