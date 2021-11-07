package com.whb;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class SmartInstantiationAwareBeanPostProcesserTest {
    public static void main(String[] args){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new MySmartInstantiationAwareBeanPostProcesser());

        factory.registerBeanDefinition("name",
                BeanDefinitionBuilder
                        .genericBeanDefinition(String.class)
                        .addConstructorArgValue("李四")
                        .getBeanDefinition());

        factory.registerBeanDefinition("person",
                BeanDefinitionBuilder
                        .genericBeanDefinition(Person.class)
                        .getBeanDefinition());

        Person person = factory.getBean("person",Person.class);
    }
}
