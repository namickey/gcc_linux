package com.example.a2;

public class Counter {

    public void count() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public synchronized void wait(String message) throws InterruptedException {
        System.out.println(message);
        wait();
    }
    public synchronized void notifyThread() {
        notify();
    }
}
