package com.example.a3;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<WaitThread> queue = new PriorityQueue<>();
        List<WaitThread> readyTasks = new ArrayList<>();
        try {
            WaitThread wt1 = new WaitThread(new Counter(), 1, 600);
            WaitThread wt2 = new WaitThread(new Checker(), 2, 200);
            readyTasks.add(wt1);
            readyTasks.add(wt2);
            wt1.start();
            wt2.start();

            for (int i = 0; i < 20; i++) {
                
                if(!queue.isEmpty()){
                    WaitThread w = queue.poll();
                    w.notifyThread();
                    w.waitTimeForTick();
                }

                for (WaitThread waitThread : readyTasks) {
                    waitThread.tick(100);
                    if (waitThread.isGo()){
                        queue.add(waitThread);
                    }
                }

                Thread.sleep(100);
            }

            for (WaitThread waitThread : readyTasks) {
                waitThread.setFlg(1);
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
