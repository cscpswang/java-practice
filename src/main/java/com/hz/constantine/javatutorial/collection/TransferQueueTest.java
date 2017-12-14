/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/20 9:40
 * @version: V1.0.0
 */
public class TransferQueueTest {

    public static void main(String[] args) throws InterruptedException {
        String message = "test";
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("first msg");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                queue.transfer(message);
                System.out.printf("sub-thread transfer %s ok \n", message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 2; i++) {
            try {
                System.out.printf("get %d msessage : %s \n", i + 1, queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

    }

}