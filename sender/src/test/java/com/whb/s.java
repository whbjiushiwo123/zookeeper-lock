package com.whb;

import org.junit.Test;

public class s {
    @Test
    public void m1(){
        if(1==1){
            System.out.println("1");
        }else if(2==2){
            System.out.println("2");
        }else {
            System.out.println("3");
        }
         final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
        System.out.println(DEFAULT_INITIAL_CAPACITY);
        System.out.println(this.hashCode() & 4);
        System.out.println(4 & this.hashCode());
    }
}
