/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description:
 * 
 *               <pre>
 *      冒泡排序: https://time.geekbang.org/column/article/41802
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-02-19 09:35
 * @version: V1.0.0
 */
public class BubbleSort {

    public int[] sort(int[] originalArray) {
        // 指针指向已排序的尾部
        int sortedTail = originalArray.length;
        while (sortedTail > 0) {
            // 本次遍历是否发生交换
            boolean swapped = false;

            for (int i = 0; i < sortedTail - 1; i++) {

                int front = originalArray[i];
                int back = originalArray[i + 1];

                if (front > back) {
                    int tmp = originalArray[i];
                    originalArray[i] = originalArray[i + 1];
                    originalArray[i + 1] = tmp;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }

            sortedTail--;
        }
        return originalArray;
    }

    @Test
    public void test() {
        int[] originalArray = new int[] { 5, 2, 1, 3, 4 };
        Assert.assertEquals(sort(originalArray), new int[] { 1, 2, 3, 4, 5 });
    }

}