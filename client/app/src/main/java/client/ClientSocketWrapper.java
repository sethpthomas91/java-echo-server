package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketWrapper {
    private String LOCAL_HOST_NAME;
    private int PORT = 5050;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public ClientSocketWrapper() throws IOException {
        this.getLOCAL_HOST_NAME();
        this.createSocketAt(LOCAL_HOST_NAME, PORT);
        this.createPrintWriter(socket);
        this.listenToServerSocket(socket);
    }

    public String recieveMessageFromServer() throws IOException {
        String message = input.readLine();
        return message;
    }

    public void sendMessageToServer(String message){
        output.println(message);
    }

    private Socket createSocketAt(String LOCAL_HOST_NAME, int PORT) throws IOException {
        socket = new Socket(LOCAL_HOST_NAME, PORT);
        return socket;
    }

    private String getLOCAL_HOST_NAME() throws UnknownHostException {
        LOCAL_HOST_NAME = Inet4Address.getLocalHost().getHostName();
        return LOCAL_HOST_NAME;
    }

    private PrintWriter createPrintWriter(Socket socket) throws IOException {
        output = new PrintWriter(socket.getOutputStream());
        return output;
    }

    private BufferedReader listenToServerSocket(Socket socket) throws IOException {
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        input = new BufferedReader(inputStream);
        return input;
    }

    @Override
    public void close() throws Exception {

    }
}
