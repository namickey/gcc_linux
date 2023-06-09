package com.example.a3;

public class WaitThread extends Thread implements Comparable<WaitThread> {

    private Task task;
    private Integer priority;
    private Integer waitTime;
    private Integer waitTimeForTick;
    private Integer flg = 2;

    public WaitThread(Task task, int priority, Integer waitTime) {
        this.task = task;
        this.priority = priority;
        this.waitTime = waitTime;
        this.waitTimeForTick = waitTime;
    }

    public void setFlg(Integer flg) {
        System.out.println("setFlg:" + flg);
        this.flg = flg;
    }

    public void tick(Integer tickTime) {
        this.waitTimeForTick -= tickTime;
        //System.out.println(waitTime);
        //System.out.println(waitTimeForTick);
    }

    public Boolean isGo() {
        return waitTimeForTick < 0;
    }

    public void waitTimeForTick() {
        waitTimeForTick = waitTime;
    }

    @Override
    public void run() {
        while(flg != 1) {
            try {
                if (flg == 2) {
                    flg = 0;
                    System.out.println("start");
                    task.waitThread();
                } else {
                    task.execute();
                    task.waitThread();
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
