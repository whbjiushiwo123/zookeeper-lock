package com.whb;

import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MySmartInstantiationAwareBeanPostProcesser implements SmartInstantiationAwareBeanPostProcessor {
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName){
        System.out.println(beanClass);
        System.out.println("调用 MySmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors 方法");
        Constructor<?>[] declaredConstructs = beanClass.getDeclaredConstructors();
        if(declaredConstructs != null){
            List<Constructor<?>> collect = Arrays.stream(declaredConstructs)
                    .filter(construct->construct.isAnnotationPresent(MyAutowried.class))
                    .collect(Collectors.toList());
            Constructor<?>[] constructors = collect.toArray(new Constructor[collect.size()]);
            return constructors.length!=0?constructors:null;
        }
        return null;
    }
}
