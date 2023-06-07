package com.example.a1;

public class Counter {

    public void count() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(100);
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
