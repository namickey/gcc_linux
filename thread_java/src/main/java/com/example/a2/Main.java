package com.example.a2;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        try {
            WaitThread wt = new WaitThread(counter);
            wt.start();
            Thread.sleep(3000);
            for (int i = 0; i < 4; i++) {
                counter.notifyThread();
                Thread.sleep(3000);
            }
            wt.setFlg(1);
            counter.notifyThread();
            Thread.sleep(3000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
