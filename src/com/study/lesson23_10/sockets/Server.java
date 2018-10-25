package com.study.lesson23_10.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private int port;
    private ServerSocket serverSocket;
    private ServerStatus serverStatus;
    private int clientCounter = 0;

    public Server(int port) {
        this.port = port;

        serverStatus = new ServerStatus();
        serverStatus.setRunning(false);
    }

    public void processClients() throws IOException {
        while(serverStatus.isRunning()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("A new client is connected: " + socket);

                serverStatus.increaseClientCounter();

                System.out.println("Assigning new thread for this client");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Runnable clientRunnable = new ClientHandler(socket, inputStream, outputStream, serverStatus);
                Thread thread = new Thread(clientRunnable);
                thread.join();
                thread.start();
            } catch (SocketException se) {
                //System.out.println("Connection reset");
                se.printStackTrace();
                System.exit(0);
            } catch (IOException | InterruptedException ie) {
                serverStatus.decreaseClientCounter();
            }
        }
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        serverStatus.setRunning(true);
        System.out.println("Server started on port: " + port);
        System.out.println("Input q for stop server: ");

        try {
            Runnable consoleRunnable = new ServerConsoleReader(serverSocket, serverStatus);
            Thread thread = new Thread(consoleRunnable);
            thread.join();
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void stop() throws IOException {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverStatus.setRunning(false);
            serverSocket.close();
        }

        System.out.println("Server stopped");
        //System.exit(0);
    }
}
