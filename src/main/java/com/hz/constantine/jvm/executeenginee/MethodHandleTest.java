/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.executeenginee;

import org.testng.annotations.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/3/12 上午9:10
 * @version: V1.0.0
 */
public class MethodHandleTest {

    static class A {
        public void print(String s) {
            System.out.println("i 'm "+s);
        }
    }

    @Test
    public void methodHandle() throws Throwable {

        getMethodHandle(new A()).invokeExact("test");
    }

    private MethodHandle getMethodHandle(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "print", methodType).bindTo(receiver);
    }

}