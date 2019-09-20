/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.spring.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/21 下午3:14
 * @version: V1.0.0
 */
public class BeanFactory implements FactoryBean<BeanFactory.Cat> {

    @Override
    public Cat getObject() {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Bean("beanFactory")
    public BeanFactory beanFactory() {
        return new BeanFactory();
    }

    public static class Cat {

    }

    @Test
    public void test() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println(applicationContext.getBean("beanFactory"));

    }
}
