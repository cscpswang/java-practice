package com.hz.constantine.retry;

import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-07-04 15:10
 * @version: V1.0.0
 */
public class RetryTest {

    @Test
    public void testBasic() throws InterruptedException {
        Retry<Boolean> retry = RetryBuilder.<Boolean> newBuilder().retryIfException()
                .retryIfResult(t -> Objects.equals(null, t) || !t)
                .build(10, 1, TimeUnit.MINUTES, new Callable() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(
                                Calendar.getInstance().getTime() + ": " + System.currentTimeMillis() + " retry");
                        throw new RuntimeException();
                    }
                });
        RetryExecutor.executor().addRetryTask(retry);
        Thread.sleep(100000);
    }

    @Test
    public void testParallelAndAlternate() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int count = i;
            RetryExecutor.executor()
                    .addRetryTask(RetryBuilder.<Boolean> newBuilder().retryIfException()
                            .retryIfResult(t -> Objects.equals(null, t) || !t)
                            .build(10, 100, TimeUnit.MILLISECONDS, new Callable() {
                                @Override
                                public Object call() throws Exception {
                                    System.out.println(Calendar.getInstance().getTime() + ": "
                                            + System.currentTimeMillis() + " Retry[" + count + "]");
                                    throw new RuntimeException();
                                }
                            }));
            Thread.sleep(200);
        }
        Thread.sleep(100000);
    }

}