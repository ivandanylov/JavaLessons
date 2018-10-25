package com.study.lesson23_10.sockets;

import java.io.IOException;
import java.util.Scanner;

public class EchoServer {
    public static int readPortFromConsole() {
        int port = 0;

        System.out.println("Input server port number, please, or type q for exit: ");
        Scanner inputScanner = new Scanner(System.in);

        while (port == 0) {
            String inputString = inputScanner.next();

            if (inputString.equals("q")) {
                System.exit(0);
            }

            try {
                port = Integer.parseInt(inputString);
            } catch (NumberFormatException e) {
                port = 0;
                System.out.println("Input server port number, please, or type q for exit: ");
            }
        }

        return port;
    }

    public static void main(String[] args) throws IOException {
        int port = 0;

        if (args.length == 1)
        {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = readPortFromConsole();
            }
        }

        try {
            Server server = new Server(port);
            server.start();
            server.processClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}