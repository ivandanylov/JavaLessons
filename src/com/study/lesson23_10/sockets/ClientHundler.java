package com.study.lesson23_10.sockets;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    final InputStream inputStream;
    final OutputStream outputStream;
    final Socket socket;
    private ServerStatus serverStatus;

    public ClientHandler(Socket socket, InputStream inputStream, OutputStream outputStream, ServerStatus serverStatus) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.serverStatus = serverStatus;
    }

    @Override
    public void run() {

        while (true) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {

                String value;
                while (!socket.isClosed() && serverStatus.isRunning() && (value = bufferedReader.readLine()) != null && !value.isEmpty()) {
                    System.out.println("Client request: " + value);
                    System.out.println("Input q for stop server: ");
                    bufferedWriter.write(value + "\n");
                    bufferedWriter.flush();
                }
            } catch (Exception e) {
                serverStatus.decreaseClientCounter();
                e.printStackTrace();
                break;
            }
        }
    }
}