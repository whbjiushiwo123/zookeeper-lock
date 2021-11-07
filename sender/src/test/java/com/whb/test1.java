package com.whb;

import com.whb.demo.HelloService1;
import com.whb.demo.HelloService1Impl;
import com.whb.demo.HelloService2Impl;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {
    @Test
    public void test1(){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        BeanDefinition definition = builder.getBeanDefinition();
        System.out.println(definition);
    }

    @Test
    public void test2(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new HelloSmartInstantiationAwareBeanPostProcesser());
        factory.addBeanPostProcessor(new HelloSmartInstantiationAwareBeanPostProcesser());
        factory.registerBeanDefinition("helloService1",BeanDefinitionBuilder
                .genericBeanDefinition(HelloService1Impl.class)
                .addPropertyReference("helloService2","helloService2")

                .getBeanDefinition());


        factory.registerBeanDefinition("helloService2",BeanDefinitionBuilder
                .genericBeanDefinition(HelloService2Impl.class)
                .getBeanDefinition());
        HelloService1 s1 = factory.getBean("helloService1",HelloService1Impl.class);
        s1.say1();
    }

    @Test
    public void test03(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        HelloService1 service1 = context.getBean("helloService1",HelloService1.class);
        service1.say1();
    }
}
