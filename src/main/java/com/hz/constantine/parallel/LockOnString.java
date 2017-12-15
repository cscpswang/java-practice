/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.parallel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/25 14:50
 * @version: V1.0.0
 */
public class LockOnString {
    public static String str = "correct";

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10);
        LockOnString instance = new LockOnString();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            instance.printSomething();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void printSomething() throws InterruptedException {
        synchronized ("mock") {
            str = "wrong";
            Thread.sleep(100);
            str = "correct";
            Thread.sleep(100);
            System.out.println(str);
        }
    }

}