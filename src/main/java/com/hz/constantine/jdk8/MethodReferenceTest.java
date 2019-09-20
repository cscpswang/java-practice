package com.hz.constantine.jdk8;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-06-22 22:05
 * @version: V1.0.0
 */
public class MethodReferenceTest {

    @Test
    public void test() {
        Supplier<Boolean> isGirl = Boy::isGirl;
        Assert.assertFalse(isGirl.get());

        Boy boy = new Boy("anan");
        Supplier<String> getName = boy::getName;
        Assert.assertEquals(getName.get(), "anan");

        List<Boy> collect = Stream.of("a", "b", "c").map(Boy::new).collect(Collectors.toList());

        Function<String, Boolean> isIntrest = Boy::isIntrest;
        Assert.assertTrue(isIntrest.apply("football"));

        Function<String, Boolean> isEmpty = String::isEmpty;

        Function<Boy, String> getName1 = Boy::getName;

    }

    static class Boy {
        String name;

        public Boy(String name) {
            this.name = name;
            System.out.println("Boy instance");
        }

        public static Boolean isGirl() {
            return false;
        }

        public static Boolean isIntrest(String type) {
            switch (type) {
                case "football":
                case "basketball":
                    return true;
                default:
                    return false;
            }
        }

        public String getName() {
            return name;
        }
    }

}