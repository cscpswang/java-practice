/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.designpattern.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/27 上午9:38
 * @version: V1.0.0
 */
public class SimpleProducerConsumerModel {
    private static final int CAPACITY = 5;

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(CAPACITY);

    class Producer implements Runnable {
        private String product;

        public Producer(String product) {
            this.product = product;
        }

        @Override
        public void run() {
            try {
                queue.put(product);
                System.out.println(product + " is product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        public Consumer() {
        }

        @Override
        public void run() {
            try {
                String product = queue.take();
                System.out.println(product + " is consume");
            } catch (InterruptedException e) {
                e.printStackTrace();
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