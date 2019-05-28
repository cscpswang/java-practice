/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.datastructure.linkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: *
 * 
 *               <pre>
 *                   see 极客时间:数据结构与算法之美:https://time.geekbang.org/column/article/41222
 *                   浏览器的前进后退功能
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-02-01 09:31
 * @version: V1.0.0
 */
public class StackTest {
    Stack<String> stackClick = new Stack<>(new String[10]);

    Stack<String> stackBehavior = new Stack<>(new String[10]);

    /**
     * 模拟浏览器行为
     * 
     * @return
     */
    @Test
    public void browseAction() {
        StackTest stackTest = new StackTest();
        stackTest.click("PageA");
        stackTest.click("PageB");
        stackTest.click("PageC");
        Assert.assertEquals(stackTest.getCurrentPage(), "PageC");

        stackTest.backoff();
        stackTest.backoff();
        stackTest.forward();
        Assert.assertEquals(stackTest.getCurrentPage(), "PageB");

        stackTest.click("PageD");
        stackTest.backoff();
        stackTest.forward();
        Assert.assertEquals(stackTest.getCurrentPage(), "PageD");
    }

    private String getCurrentPage() {
        return stackClick.get();
    }

    private void click(String page) {
        stackClick.push(page);
        stackBehavior.clear();
    }

    private void forward() {
        String forwardPage = stackBehavior.pop();
        if (null == forwardPage) {
            throw new IllegalStateException("can't forward ");
        }
        stackClick.push(forwardPage);
    }

    private void backoff() {
        String backoffPage = stackClick.pop();
        if (null == backoffPage) {
            throw new IllegalStateException("can't backoff ");
        }
        stackBehavior.push(backoffPage);
    }

    static class Stack<T> {

        T[] array;

        int length;

        public Stack(T[] array) {
            this.array = array;
            this.length = 0;
        }

        public T pop() {
            if (length <= 0) {
                return null;
            }
            T end = array[length - 1];
            array[length - 1] = null;
            length--;
            return end;
        }

        public T get() {
            if (length <= 0) {
                return null;
            }
            return array[length - 1];
        }

        public void push(T t) {
            if (length >= array.length) {
                throw new IndexOutOfBoundsException();
            }
            array[length] = t;
            length++;
        }

        public void clear() {
            for (T t : array) {
                t = null;
            }
            this.length = 0;
        }
    }

}