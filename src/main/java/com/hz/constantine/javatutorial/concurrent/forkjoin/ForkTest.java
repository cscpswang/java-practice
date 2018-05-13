/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.concurrent.forkjoin;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

/**
 * @Description:
 *
 *               ForkTest implements a simple horizontal image blur. It averages pixels in the source array and writes
 *               them to a destination array. The sThreshold value determines whether the blurring will be performed
 *               directly or split into two tasks.
 *
 *               This is not the recommended way to blur images; it is only intended to illustrate the use of the
 *               Fork/Join framework.
 * @author: xiangji
 * @date: 2018/1/16 下午7:32
 * @version: V1.0.0
 */
public class ForkTest {

    class ActionTest extends RecursiveAction {

        private List<String> msgs = Lists.newArrayList();

        private static final int sThreshold = 10;

        public ActionTest(List<String> msgs) {
            this.msgs = msgs;
        }

        protected void sendDirectly() {
            System.out.println("send :" + msgs);
        }

        @Override
        protected void compute() {
            if (msgs.size() <= sThreshold) {
                sendDirectly();
                return;
            }

            int split = msgs.size() / 2;

            invokeAll(new ActionTest(msgs.subList(0, split)), new ActionTest(msgs.subList(split, msgs.size())));
        }
    }

    @Test
    public void action() {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(
                Integer.toString(processors) + " processor" + (processors != 1 ? "s are " : " is ") + "available");

        List<String> msgs = Lists.newArrayList();
        for (int i = 0; i < 40; i++) {
            msgs.add(String.valueOf(i));
        }
        ActionTest fb = new ActionTest(msgs);

        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) + " milliseconds.");

    }

    class TaskTest extends RecursiveTask<Integer> {

        private List<String> msgs = Lists.newArrayList();

        private static final int sThreshold = 10;

        public TaskTest(List<String> msgs) {
            this.msgs = msgs;
        }

        protected Integer sendDirectly() {
            int factor = RandomUtils.nextInt(0,2);
                System.out.println(Calendar.getInstance().getTime() + ":send :" + msgs);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return msgs.size();
        }

        @Override
        protected Integer compute() {
            if (msgs.size() <= sThreshold) {
                return sendDirectly();
            }

            int split = msgs.size() / 2;
            TaskTest taskTest1 = new TaskTest(msgs.subList(0, split));
            TaskTest taskTest2 = new TaskTest(msgs.subList(split, msgs.size()));
            taskTest1.fork();
            taskTest2.fork();

            try {
                return taskTest1.get() + taskTest2.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void task() {
        List<String> msgs = Lists.newArrayList();
        int size = 65;
        for (int i = 0; i < size; i++) {
            msgs.add(String.valueOf(i));
        }
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(new TaskTest(msgs));

        Assert.assertEquals(result.intValue(), size);

    }
}