package com.xj.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/28
 */
public class DirectMemoryOOM {

    public static int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {

        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
