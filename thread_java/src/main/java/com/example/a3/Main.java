package com.example.a3;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<WaitThread> queue = new PriorityQueue<>();
        PriorityQueue<WaitThread> readyQueue = new PriorityQueue<>();
        List<WaitThread> tasks = new ArrayList<>();
        try {
            WaitThread wt1 = new WaitThread(new Counter(), 1, 3);
            WaitThread wt2 = new WaitThread(new Checker(), 1, 2);
            WaitThread wt3 = new WaitThread(new Checker(), 1, 6);
            tasks.add(wt1);
            tasks.add(wt2);
            tasks.add(wt3);
            readyQueue.add(wt1);
            readyQueue.add(wt2);
            readyQueue.add(wt3);
            wt1.start();
            wt2.start();
            wt3.start();

            WaitThread w = wt1;
            for (int i = 0; i < 100; i++) {
                
                if(!queue.isEmpty()){
                    if (!w.isGoing()) {
                        w = queue.poll();
                        w.notifyThread();
                    }
                }

                for (WaitThread waitThread : tasks) {
                    
                    String str = "";

                    if (!waitThread.isGoing() && waitThread.isTimePast()){
                        if (readyQueue.contains(waitThread) && !queue.contains(waitThread)) {
                            readyQueue.remove(waitThread);
                            queue.add(waitThread);
                            str = "+" + queue.size() + " " + waitThread.getTaskName();
                        }
                    }

                    if (!waitThread.isGoing() && !readyQueue.contains(waitThread) && !queue.contains(waitThread)) {
                        readyQueue.add(waitThread);
                    }
                    System.out.println(str);
                    waitThread.tick(1);
                }

                Thread.sleep(10);
            }

            Thread.sleep(200);
            for (WaitThread waitThread : tasks) {
                waitThread.setEnd();
                waitThread.notifyThread();
            }
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
