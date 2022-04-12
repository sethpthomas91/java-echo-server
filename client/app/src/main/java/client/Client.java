package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;

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

    public void startClient() {
        try (
                ClientSocketWrapper clientSocket = new ClientSocketWrapper()
        ) {
            String fromServer;
            String fromUser;
            while ((fromServer = clientSocket.receiveMessageFromServer()) != null) {

                if (fromServer.equals("Disconnect"))
                    break;

                fromUser = getUserInput();
                if (fromUser != null) {
                    clientSocket.sendMessageToServer((fromUser));
                }
            }

        } catch (Exception error) {
            System.out.println(String.format("Exception caught when listening to port %s or listening for a connection.", PORT));
            System.out.println(error.getMessage());
        }
    }
}