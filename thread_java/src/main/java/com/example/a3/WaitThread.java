package com.example.a3;

public class WaitThread extends Thread implements Comparable<WaitThread> {

    private Task task;
    private Integer priority;
    private Integer waitTime;
    private Integer waitTimeForTick;
    private String name;
    private enum Status {
        UNINITIALIZED, READY, GOING, END
    }
    private Status status = Status.UNINITIALIZED;

    public WaitThread(Task task, int priority, Integer waitTime) {
        this.task = task;
        this.name = task.getClass().toString();
        this.priority = priority;
        this.waitTime = waitTime;
        this.waitTimeForTick = waitTime;
    }

    public void setEnd() {
        System.out.println("setEnd");
        this.status = Status.END;
    }

    public void tick(Integer tickTime) {
        this.waitTimeForTick -= tickTime;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean isTimePast() {
        return waitTimeForTick < 0;
    }

    public boolean isReady() {
        return Status.READY.equals(this.status);
    }

    public boolean isGoing() {
        return Status.GOING.equals(this.status);
    }

    @Override
    public void run() {
        
        while(!Status.END.equals(this.status)) {
            try {
                if (Status.UNINITIALIZED.equals(this.status)) {
                    this.status = Status.READY;
                    task.waitThread();
                    this.status = Status.GOING;
                } else {
                    this.status = Status.GOING;
                    waitTimeForTick = waitTime;
                    task.execute();
                    this.status = Status.READY;
                    task.waitThread();
                    if (!Status.END.equals(this.status)) {
                        this.status = Status.GOING;
                    }
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void notifyThread() {
        task.notifyThread();
    }

    @Override
    public int compareTo(WaitThread waitThread) {
        return priority.compareTo(waitThread.priority);
    }
}
