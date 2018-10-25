package com.study.lesson23_10.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServerConsoleReader implements Runnable {
    private ServerSocket serverSocket;
    private ServerStatus serverStatus;

    public ServerConsoleReader(ServerSocket serverSocket, ServerStatus serverStatus) {
        this.serverSocket = serverSocket;
        this.serverStatus = serverStatus;
    }

    @Override
    public void run() {
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String inputString = inputScanner.next();

            if (inputString.equals("q")) {
                inputScanner.close();
                try {
                    serverStatus.setRunning(false);
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }
}
