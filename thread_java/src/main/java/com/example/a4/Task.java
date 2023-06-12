package com.example.a4;

public interface Task {
    void execute();
    void waitThread() throws InterruptedException;
    void notifyThread();
}
