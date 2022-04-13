package com.sethpthomas91.server;

import java.io.IOException;

public class DummyServerWrapper implements ServerSocketWrapperInterface{

    public DummyServerWrapper(int PORT) throws IOException {

    }

    @Override
    public void sendMessageToClient(String message) {

    }

    @Override
    public String receiveMessageFromClient() throws IOException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
