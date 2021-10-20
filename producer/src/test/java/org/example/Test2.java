package org.example;

import org.openjdk.jol.info.ClassLayout;

public class Test2 {
    public static void main(String[] args) {
//        Object obj = new Object();
        //-XX:+UseCompressedOops 开启指针压缩
        //-XX:-UseCompressedOops 关闭指针压缩
        MyItem myItem = new MyItem();
        System.out.println(ClassLayout.parseInstance(myItem).toPrintable());
    }
}
