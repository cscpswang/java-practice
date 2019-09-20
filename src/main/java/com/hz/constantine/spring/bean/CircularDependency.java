package com.hz.constantine.spring.bean;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description:
 * 
 *               <pre>
 *     循环依赖. see: https://tinyurl.com/y34fmj33
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-09-18 13:40
 * @version: V1.0.0
 */
public class CircularDependency {

    @Test(expectedExceptions = UnsatisfiedDependencyException.class)
    public void testNotAllowCircular() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanConfig.class);
        applicationContext.setAllowCircularReferences(false);
        applicationContext.refresh();

        final BeanA beanA = applicationContext.getBean(BeanA.class);
        System.out.println(beanA.toPrintString());
    }

    @Test
    public void testAllowCircular() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        final BeanA beanA = applicationContext.getBean(BeanA.class);
        System.out.println(beanA.toPrintString());
    }

    @Component
    public static class BeanA {

        @Autowired
        private BeanB beanB;

        public String toPrintString() {
            return "i'm beanA, beanB is" + beanB;
        }
    }

    @Component
    public static class BeanB {

        @Autowired
        private BeanA beanA;

        public String toPrintString() {
            return "i'm beanB, beanA is" + beanA;
        }
    }

}