/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.memallocate;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/2 9:14
 * @version: V1.0.0
 */
public class EdenAllocate {

    public static void main(String[] args) {
        byte[] a, b, c, d;
        a = new byte[2 * 1024 * 1024];
        b = new byte[2 * 1024 * 1024];
        c = new byte[2 * 1024 * 1024];
        d = new byte[4 * 1024 * 1024];
    }

}