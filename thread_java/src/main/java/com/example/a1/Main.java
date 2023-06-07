package com.example.a1;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        try {
            WaitThread wt = new WaitThread(counter);
            Thread t = new Thread(wt);
            t.start();
            Thread.sleep(3000);
            //NotifyThread nt = new NotifyThread(counter);
            for (int i = 0; i < 4; i++) {
                //nt.start();
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
