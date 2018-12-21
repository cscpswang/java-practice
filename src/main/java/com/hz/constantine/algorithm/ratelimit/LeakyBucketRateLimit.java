/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.ratelimit;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.testng.annotations.Test;

/**
 * @Description: 漏桶限流
 * @author: xiangji
 * @date: 2018/11/12 9:03 PM
 * @version: V1.0.0
 */
public class LeakyBucketRateLimit {

    private volatile Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    public void mockRequest() {
        AtomicInteger task = new AtomicInteger(0);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().daemon(true).namingPattern("provider").build());
        executor.scheduleAtFixedRate(() -> {
            int i = task.incrementAndGet();
            queue.offer(i);
            System.out.println("thread[" + Thread.currentThread().getName() + "], offer:" + i);
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    @Test
    @SuppressWarnings("all")
    public void test() throws InterruptedException {
        LeakyBucketRateLimit rateLimit = new LeakyBucketRateLimit();
        rateLimit.mockRequest();

        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(5,
                new BasicThreadFactory.Builder().daemon(true).namingPattern("consumer").build());
        ScheduledFuture scheduledFuture1 = poolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("thread[" + Thread.currentThread().getName() + "], pop:" + rateLimit.queue.poll());
        }, 0, 100, TimeUnit.MILLISECONDS);
        ScheduledFuture scheduledFuture2 = poolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("thread[" + Thread.currentThread().getName() + "], pop:" + rateLimit.queue.poll());
        }, 0, 100, TimeUnit.MILLISECONDS);

        Thread.sleep(1000);
    }
}