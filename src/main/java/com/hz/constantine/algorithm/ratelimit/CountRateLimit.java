/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.ratelimit;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/11/12 7:02 PM
 * @version: V1.0.0
 */
public class CountRateLimit {

    private volatile AtomicInteger counter = new AtomicInteger(0);

    private static final int LIMIT = 10;

    public boolean tryAcquire() {
        boolean isOverLimit = counter.incrementAndGet() > LIMIT;
        if (isOverLimit) {
            return false;
        }
        return true;
    }

    public void acquire() {
        while (true) {
            if (tryAcquire()) {
                break;
            }
        }
    }

    public void acquireNanos(int nanos) {
        long startTime = System.nanoTime();
        while (true) {
            if (tryAcquire()) {
                break;
            }
            if (System.nanoTime() - startTime > nanos) {
                return;
            }
        }
    }

    public void start() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().daemon(true).namingPattern("timer").build());
        executor.scheduleAtFixedRate(() -> {
            counter.set(0);
            System.out.println(" counter reset to 0 ");
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Test
    @SuppressWarnings("all")
    public void test() {
        CountRateLimit countRateLimit = new CountRateLimit();
        countRateLimit.start();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), (Runnable r) -> new Thread(r));
        Runnable task = () -> {
            int acquireNum = 10;
            for (int i = 0; i < acquireNum; i++) {
                boolean acquireResult = countRateLimit.tryAcquire();
                System.out.println("thread[" + Thread.currentThread().getName() + "], i" + i + ",acquire result: "
                        + acquireResult);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = acquireNum; i < acquireNum + acquireNum; i++) {
                boolean acquireResult = countRateLimit.tryAcquire();
                System.out.println("thread[" + Thread.currentThread().getName() + "], i" + i + ",acquire result: "
                        + acquireResult);
            }
        };
        poolExecutor.execute(task);
        poolExecutor.execute(task);
        poolExecutor.execute(task);

        while (3 != poolExecutor.getCompletedTaskCount()) {
        }
        ;
    }

}