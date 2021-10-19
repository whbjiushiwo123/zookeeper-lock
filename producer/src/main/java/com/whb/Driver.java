package com.whb;

import java.util.concurrent.CountDownLatch;

public class Driver {
    public static void main(String [] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);
        for(int i=0;i<5;i++){
            new Thread(new Worker(startSignal,doneSignal,i)).start();
        }

        //军座先跑
        doSomethingElse("军座先撤退...");
        //然后计算器减1
        startSignal.countDown();//军座撤退完毕，打开门闩，让小兵撤退
        doSomethingElse("军座撤退成功，让小兵撤退...");
        //清点人数，确认小兵是否全部撤退，若没有需要列队等待
        doneSignal.await();           // wait for all to finish
        System.out.println("全军撤退完毕！");

    }

    private static void doSomethingElse(String commond) {
        System.out.println(commond);
    }
}
