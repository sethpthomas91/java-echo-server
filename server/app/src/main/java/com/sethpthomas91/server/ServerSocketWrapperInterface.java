package com.sethpthomas91.server;

import java.io.IOException;

public interface ServerSocketWrapperInterface extends AutoCloseable {

    void sendMessageToClient(String message);

    String receiveMessageFromClient() throws IOException;

}
