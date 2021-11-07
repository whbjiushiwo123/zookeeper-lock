package com.whb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("helloService1")
public class HelloService1Impl implements HelloService1 {
    @Autowired
    private HelloService2 helloService2;
    @Override
    public String say1() {
        System.out.println(helloService2.toString());
        return helloService2.say2();
    }
}
