package com.study.lesson23_10.sockets;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000);
             BufferedWriter socketBufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String value;

            while ((value = consoleBufferedReader.readLine()) != null) {
                socketBufferedWriter.write(value + "\n");
                socketBufferedWriter.flush();
                String response = socketBufferedReader.readLine();
                System.out.println(response);
            }
        }
    }
}
