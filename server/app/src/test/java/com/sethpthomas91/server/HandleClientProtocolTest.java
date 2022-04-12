package com.sethpthomas91.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandleClientProtocolTest {
    private HandleClientProtocol clientProtocol;

    @Before
    public void setUp() throws Exception {
        clientProtocol = new HandleClientProtocol();
    }

    @Test
    public void processInputShouldReturnSameStringHello() {
        Assert.assertEquals("Hello", clientProtocol.processInput("Hello"));
    }

    @Test
    public void processInputShouldReturnSameEmptyString() {
        Assert.assertEquals("", clientProtocol.processInput(""));
    }
}