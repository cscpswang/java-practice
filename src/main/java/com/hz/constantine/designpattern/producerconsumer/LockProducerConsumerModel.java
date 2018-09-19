/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.designpattern.producerconsumer;

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/27 上午9:53
 * @version: V1.0.0
 */
public class LockProducerConsumerModel {

    private static final int CAPACITY = 5;

    private final Queue<String> queue = new LinkedList<>();

    private final Lock lockP = new ReentrantLock();

    private final Lock lockC = new ReentrantLock();

    private final Condition conditionNotFull = lockP.newCondition();

    private final Condition conditionNotEmpty = lockC.newCondition();

    private AtomicInteger queueSize = new AtomicInteger(0);

    class Producer implements Runnable {
        private String product;

        public Producer(String product) {
            this.product = product;
        }

        @Override
        public void run() {
            int queueSizeInner = -1;
            try {
                lockP.lock();
                if (queueSize.get() >= CAPACITY) {
                    conditionNotFull.await();
                }
                queue.add(product);
                queueSizeInner = queueSize.incrementAndGet();
                System.out.println(product + " is product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockP.unlock();
            }
            try {
                lockC.lock();
                if (queueSizeInner > 0) {
                    conditionNotEmpty.signalAll();
                }
            } finally {
                lockC.unlock();
            }
        }
    }

    class Consumer implements Runnable {
        public Consumer() {
        }

        @Override
        public void run() {
            int queueSizeInner = -1;
            try {
                lockC.lock();
                if (queueSize.get() <= 0) {
                    conditionNotEmpty.await();
                }
                String product = queue.poll();
                queueSizeInner = queueSize.decrementAndGet();
                System.out.println(product + " is consume");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockC.unlock();
            }
            try {
                lockP.lock();
                if (queueSizeInner < CAPACITY) {
                    conditionNotFull.signalAll();
                }
            } finally {
                lockP.unlock();
            }
        }
    }

    @Test
    public void model() throws InterruptedException {
        int count = 10;
        for (int i = 0; i < count; i++) {
            String product = "product_" + i;
            new Thread(new Producer(product)).start();
        }
        for (int i = 0; i < count; i++) {
            new Thread(new Consumer()).start();
        }
        Thread.sleep(1000);
    }

}