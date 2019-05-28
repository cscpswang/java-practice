/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.datastructure.linkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @Description:
 * 
 *               <pre>
 *                  极客时间: 队列.https://time.geekbang.org/column/article/41330
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-02-15 09:22
 * @version: V1.0.0
 */
public class QueueTest {
    /**
     * 用数组实现队列
     */
    static class ArrayQueue {
        String[] array;

        int capacity;

        int head;

        int tail;

        public ArrayQueue(int capacity) {
            this.array = new String[capacity + 1];
            this.capacity = capacity;
            this.head = 0;
            this.tail = 0;
        }

        public void enqueue(String item) {
            if (this.tail >= capacity) {
                if (head > 0) {
                    for (int i = 0; i < tail; i++) {
                        array[i] = array[i + tail - head];
                    }
                    tail = tail - head;
                    head = 0;
                } else {
                    return;
                }
            }
            array[tail] = item;
            tail++;
        }

        public String dequeue() {
            if (head < tail) {
                String result = array[head];
                head++;
                return result;
            }
            return null;
        }

    }

    @Test
    public void testArrayQueue() {
        int capacity = 2;

        ArrayQueue arrayQueue = new ArrayQueue(capacity);
        Assert.assertNull(arrayQueue.dequeue());

        arrayQueue.enqueue("a");
        Assert.assertEquals(arrayQueue.dequeue(), "a");

        arrayQueue.enqueue("a");
        arrayQueue.enqueue("b");
        Assert.assertEquals(arrayQueue.dequeue(), "a");
        Assert.assertEquals(arrayQueue.dequeue(), "b");

        arrayQueue.enqueue("a");
        arrayQueue.enqueue("b");
        arrayQueue.enqueue("c");
        Assert.assertEquals(arrayQueue.dequeue(), "a");
        Assert.assertEquals(arrayQueue.dequeue(), "b");
        Assert.assertNull(arrayQueue.dequeue());
    }
}