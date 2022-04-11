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

    private String getUserInput() throws IOException {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("[USER INPUT]: ");
        return stdIn.readLine();
    }

    public void startClient() throws IOException {
        try (
                Socket socket = new Socket(LOCAL_HOST_NAME, PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
                BufferedReader input = new BufferedReader(inputStream);
        ) {
            String fromServer;
            String fromUser;

            while ((fromServer = input.readLine()) != null) {

                System.out.println(String.format("[SERVER RETURN MESSAGE]: %s", fromServer));

                if (fromServer.equals("Disconnect"))
                    break;

                fromUser = getUserInput();
                if (fromUser != null) {
                    System.out.println(String.format("[CLIENT OUTGOING MESSAGE]: %s", fromUser));
                    output.println(fromUser);
                }
            }

        } catch (IOException error) {
            System.out.println(String.format("Exception caught when listening to port %s or listening for a connection.", PORT));
            System.out.println(error.getMessage());
        }
    }
}