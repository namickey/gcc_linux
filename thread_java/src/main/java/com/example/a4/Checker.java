package com.example.a4;

public class Checker implements Task {

    @Override
    public void execute() {
        System.out.println("checker:check");
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
