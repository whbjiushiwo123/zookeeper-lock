package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }
    private final AtomicInteger ctl1 = new AtomicInteger(ctlOf(RUNNING, 0));
    private final AtomicInteger ctl2 = new AtomicInteger(ctlOf(SHUTDOWN, 0));
    private final AtomicInteger ctl3 = new AtomicInteger(ctlOf(STOP, 0));
    private final AtomicInteger ctl4 = new AtomicInteger(ctlOf(TIDYING, 0));
    private final AtomicInteger ctl5 = new AtomicInteger(ctlOf(TERMINATED, 0));

    public static void main(String[] args){
        Test1 test1 = new Test1();
        int c1 = test1.ctl1.get();
        int c2 = test1.ctl2.get();
        int c3 = test1.ctl3.get();
        int c4 = test1.ctl4.get();
        int c5 = test1.ctl5.get();
        System.out.println(Integer.toBinaryString(c1));
        System.out.println(Integer.toBinaryString(c2));
        System.out.println(Integer.toBinaryString(c3));
        System.out.println(Integer.toBinaryString(c4));
        System.out.println(Integer.toBinaryString(c5));

    }
}
