/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.designpattern.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/27 上午10:47
 * @version: V1.0.0
 */
public class SemaphoreProducerConsumerModel {
    private static final int CAPACITY = 5;

    private final Queue<String> queue = new LinkedList<>();

    private final Semaphore notFull = new Semaphore(CAPACITY);

    private final Semaphore notEmpty = new Semaphore(0);

    private final Semaphore mutex = new Semaphore(1);

    class Producer implements Runnable {
        private String product;

        public Producer(String product) {
            this.product = product;
        }

        @Override
        public void run() {
            try {
                notFull.acquire();
                mutex.acquire();
                queue.add(product);
                System.out.println(product + " is product");
                notEmpty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                notEmpty.acquire();
                mutex.acquire();
                String product = queue.poll();
                System.out.println(product + " is consume");
                notFull.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
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