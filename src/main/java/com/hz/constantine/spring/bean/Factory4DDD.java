/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/21 下午3:34
 * @version: V1.0.0
 */
public class Factory4DDD {

    @Autowired
    private Factory factory;

    @Component
    class Factory {
        @Autowired
        private Repository repository;

        public Cat createObj() {
            return new Cat(repository);
        }
    }

    @Component
    class Repository {

    }

    class Cat {
        private Repository repository;

        public Cat(Repository repository) {
            this.repository = repository;
        }
    }

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Starter.class);
        applicationContext.getBean(Factory.class).createObj();
    }
}

@EnableAutoConfiguration
class Starter {

}