/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.jvisualvm;

import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/26 上午8:21
 * @version: V1.0.0
 */
public class BTraceTest {

    public int btrace(int a,int b)throws Exception{

        return RandomUtils.nextInt(a,1000)+RandomUtils.nextInt(b,1000);
    }

    public static void main(String[] args)throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter any symbol to start");
        bf.readLine();
        System.out.println(new BTraceTest().btrace(1,5));
    }
}