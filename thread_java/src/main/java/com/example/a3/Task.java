package com.example.a3;

public interface Task {
    void execute();
    void waitThread() throws InterruptedException;
    void notifyThread();
}
