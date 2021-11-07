package com.whb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("helloService2")
public class HelloService2Impl implements HelloService2 {

    @Autowired
    private HelloService1 helloService1;
    @Override
    public String say2() {
        System.out.println(helloService1.toString());
        return helloService1.say1();
    }
}
