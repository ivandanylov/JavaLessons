package com.study.lesson23_10.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String host;
    private int port;

    public void Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;

        this.socket = new Socket(this.host, this.port);
    }

    public void processData() {

    }
}
