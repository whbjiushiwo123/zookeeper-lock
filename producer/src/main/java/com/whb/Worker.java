package com.whb;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;
    private int count;
    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, int count) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            System.out.println("第"+count+"个小兵试图逃跑，被要求滚回战场...");
            startSignal.await();
            doWork(count);
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork(int count) {
        System.out.println("军座都跑完了，我们小兵也跑吧,第"+count+"个小兵逃跑："+Thread.currentThread().getName());
    }
}
