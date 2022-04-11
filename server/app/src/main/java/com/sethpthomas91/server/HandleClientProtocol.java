package com.sethpthomas91.server;

public class HandleClientProtocol {

    public String processInput(String input) {
        String additionalMessage = "PROCESSED ";
        String output = additionalMessage + input;
        return output;
    }


}
