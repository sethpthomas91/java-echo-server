package com.sethpthomas91.server;

import java.io.IOException;

public interface ServerSocketInterface {
    String getLastOutput() throws IOException;
    boolean isConnected();
    void startSocketAt(int port) throws IOException;
    void closeSocket() throws IOException;
}
