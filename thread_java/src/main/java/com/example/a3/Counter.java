package com.example.a3;

public class Counter implements Task {

    public void execute() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Counter:" + i);
        }
    }
    
    @Override
    public synchronized void waitThread() throws InterruptedException {
        wait();
    }

    @Override
    public synchronized void notifyThread() {
        notify();
    }
}
