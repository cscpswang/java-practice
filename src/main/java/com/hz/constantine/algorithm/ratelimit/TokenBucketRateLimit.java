/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.ratelimit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.testng.annotations.Test;

import java.util.concurrent.*;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/11/13 9:40 AM
 * @version: V1.0.0
 */
public class TokenBucketRateLimit {

    private volatile BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

    public boolean tryAcquire() {
        Integer a = queue.poll();
        if (null == a) {
            return false;
        }
        return true;
    }

    public boolean acquire() {
        try {
            Integer a = queue.take();
            if (null == a) {
                return false;
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean acquireNanos(int nanos) {
        try {
            Integer a = queue.poll(nanos, TimeUnit.NANOSECONDS);
            if (null == a) {
                return false;
            }
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void start() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().daemon(true).namingPattern("timer").build());
        executor.scheduleAtFixedRate(() -> {
            this.queue.offer(1);
            System.out.println(" put token to queue ");
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Test
    @SuppressWarnings("all")
    public void test() {
        TokenBucketRateLimit rateLimit = new TokenBucketRateLimit();
        rateLimit.start();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), (Runnable r) -> new Thread(r));
        Runnable task = () -> {
            int acquireNum = 10;
            for (int i = 0; i < acquireNum; i++) {
                boolean acquireResult = rateLimit.tryAcquire();
                System.out.println("thread[" + Thread.currentThread().getName() + "], i" + i + ",acquire result: "
                        + acquireResult);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = acquireNum; i < acquireNum + acquireNum; i++) {
                boolean acquireResult = rateLimit.tryAcquire();
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