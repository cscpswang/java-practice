/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.gc;

import com.google.common.primitives.Bytes;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/27 9:07
 * @version: V1.0.0
 */
public class MinorGc {

    public static void main(String[] args) throws InterruptedException {
        byte[] a, b, c, d;
        a = new byte[1024 * 1024 * 2];
        b = new byte[1024 * 1024 * 2];
        c = new byte[1024 * 1024 * 2];
        d = new byte[1024 * 1024 * 4];
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

}