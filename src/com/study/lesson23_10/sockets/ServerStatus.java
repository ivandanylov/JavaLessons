package com.study.lesson23_10.sockets;

public class ServerStatus {
    volatile boolean running = false;
    volatile int clientCounter = 0;

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized void increaseClientCounter() {
        clientCounter++;
        System.out.println("Clients count: " + clientCounter);
    }

    public synchronized void decreaseClientCounter() {
        clientCounter--;
        System.out.println("Clients count: " + clientCounter);
    }

    public synchronized int getClientCounter() {
        return clientCounter;
    }
}
