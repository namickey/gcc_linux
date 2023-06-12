package com.example.a4;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    private PriorityQueue<WaitThread> queue = new PriorityQueue<>();
    private PriorityQueue<WaitThread> readyQueue = new PriorityQueue<>();
    private List<WaitThread> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.execute(100, 900);
    }

    public void execute(int tick, int loop) {
        
        try {
            WaitThread wt1 = createThread(new Counter(), 1, 2000);
            createThread(new Checker(), 1, 1000);
            createThread(new Checker(), 1, 1000);

            WaitThread w = wt1;
            for (int i = 0; i < tick*10; i++) {
                
                if(!w.isGoing()){
                    if (!queue.isEmpty()) {
                        w = queue.poll();
                        w.notifyThread();
                    }

                    for (WaitThread waitThread : tasks) {
                        if (!waitThread.isGoing()) {
                            if (waitThread.isTimePast()){
                                if (readyQueue.contains(waitThread) && !queue.contains(waitThread)) {
                                    readyQueue.remove(waitThread);
                                    queue.add(waitThread);
                                    //str = "+" + queue.size() + " " + waitThread.getTaskName();
                                }
                            }

                            if (!readyQueue.contains(waitThread) && !queue.contains(waitThread)) {
                                readyQueue.add(waitThread);
                            }
                        }
                    }
                }
                for (WaitThread waitThread : tasks) {
                    waitThread.tick(tick);
                }
                
                Thread.sleep(tick);
            }

            close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private WaitThread createThread(Task task, int priority, int waitTime) {
        WaitThread wt = new WaitThread(task, priority, waitTime);
        tasks.add(wt);
        readyQueue.add(wt);
        wt.start();
        return wt;
    }

    private void close() throws InterruptedException {
        Thread.sleep(4000);
        for (WaitThread waitThread : tasks) {
            waitThread.setEnd();
            waitThread.notifyThread();
            Thread.sleep(100);
            waitThread.print();
        }
        queue.clear();
        readyQueue.clear();
        tasks.clear();
    }
}
