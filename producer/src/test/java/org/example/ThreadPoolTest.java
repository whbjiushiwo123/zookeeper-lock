package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(JUnit4.class)
public class ThreadPoolTest {
    @Test
    public void test01(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(80,150,10L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(200));
        long start = System.currentTimeMillis() / 1000;
        IntStream.range(0,500).forEach(i->{
            executor.submit(new MyThread());
            try {
                Thread.sleep(3L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
        System.out.println("use time " + (System.currentTimeMillis() / 1000 - start) + "秒");
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500L);
            System.out.println(Thread.currentThread().getName()+"     run  ....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
