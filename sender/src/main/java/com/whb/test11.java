package com.whb;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * beanPostProcessors列表，如果类型是InstantiationAwareBeanPostProcessor，
 * 尝试调用InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
 * 获取bean的实例对象，如果能够获取到，那么将返回值作为当前bean的实例，
 * 那么spring自带的实例化bean的过程就被跳过了。
 * 这个地方给开发者提供了一个扩展点，允许开发者在这个方法中直接返回bean的一个实例。
 */
public class test11 {
    public static void main(String[] args){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                if(beanClass == Car.class){
                    Car c = new Car();
                    c.setName("奥迪！");
                    return c;
                }
                return null;
            }
        });

        AbstractBeanDefinition definition = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class)
                .addPropertyValue("name","保时捷").
                getBeanDefinition();

        factory.registerBeanDefinition("car",definition);
        System.out.println(factory.getBean("car"));
    }
}
