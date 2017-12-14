/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.jvisualvm;

import org.apache.commons.lang3.RandomUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/12 上午9:40
 * @version: V1.0.0
 */
public class JvisualvmTest {

    private int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        JvisualvmTest jvisualvmTest = new JvisualvmTest();

        for (int i = 0; i < 10; i++) {
            System.out.println("next, input anything:");
            bufferedReader.readLine();

            System.out.println(jvisualvmTest.add(RandomUtils.nextInt(0, 1000), RandomUtils.nextInt(0, 1000)));

        }

    }

}