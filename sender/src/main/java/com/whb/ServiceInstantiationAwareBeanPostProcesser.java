package com.whb;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class ServiceInstantiationAwareBeanPostProcesser  implements InstantiationAwareBeanPostProcessor {
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName){
        System.out.println("ServiceInstantiationAwareBeanPostProcesser ===========================");
        return pvs;
    }

}
