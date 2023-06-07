package com.example.a1;

public class WaitThread implements Runnable {

    private Counter counter;
    private int flg = 0;

    public WaitThread(Counter counter) {
        this.counter = counter;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    @Override
    public void run() {
        while(flg == 0) {
            try {
                counter.count();
                counter.wait("waitting....");
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
