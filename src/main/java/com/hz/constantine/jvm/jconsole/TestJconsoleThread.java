/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.jconsole;

import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/7 上午9:30
 * @version: V1.0.0
 */
public class TestJconsoleThread {

    /**
     * 死循环线程
     */
    private void newDeadLoopThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {

                }
            }
        }, "deadLoopThread");
        thread.start();
    }

    /**
     * 活锁等待线程
     */
    private void newLiveLockThread() {
        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "LiveLockThread");
        thread.start();
    }

    @Test
    public void main() throws Exception {
        TestJconsoleThread testJconsoleThread = new TestJconsoleThread();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入任意字符，new 死循环线程");
        reader.readLine();
        testJconsoleThread.newDeadLoopThread();
        System.out.println("输入任意字符，new 活锁等待线程");
        reader.readLine();
        testJconsoleThread.newLiveLockThread();
    }
}