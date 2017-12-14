/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.dependency;

import com.sun.tools.classfile.Dependency;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/7 下午6:46
 * @version: V1.0.0
 */
public final class ClassUnderTest {
    private Dependency dependency;

    private Dependency2 dependency2;

    public void doSomething() {
        String data = this.dependency.findSomeData();
        System.out.println("find some data:"+data);
        this.dependency2.insert(data);
        this.dependency2.insert(data);
    }

    public static class Dependency {
        public String findSomeData() {
            return "some";
        }
    }

    public static class Dependency2 {
        public void insert(String target) {
            System.out.println(target + " has been inserted!!! ");
        }
    }
}

