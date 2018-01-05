/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.commonlang;

import mockit.Deencapsulation;
import org.apache.commons.beanutils.BeanUtils;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/12/21 19:34
 * @version: V1.0.0
 */
public class PropertyUtils {

    public class Cat {
        private String name;

        public Cat() {
        }

        public Cat(String name) {
            this.name = name;
        }

        private void eat() {
            System.out.println("i'm eating");
        }

    }

    @Test
    public void copyProperty()
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Cat jimmy = new Cat("jimmy");
        Cat jimmyCopier = new Cat();

        for (Field field : jimmy.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Deencapsulation.setField(jimmyCopier, field.getName(), field.get(jimmy));
        }
        Method eat = jimmy.getClass().getDeclaredMethod("eat");
        eat.setAccessible(true);

        System.out.println(jimmyCopier.name);
    }

}