/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.executeenginee;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/3/7 上午9:33
 * @version: V1.0.0
 */
public class StaticResolution {

    public static void sayHello(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }

}