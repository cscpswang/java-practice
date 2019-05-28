/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 堆排序: <http>https://time.geekbang.org/column/article/69913</http>
 * @author: xiangji
 * @date: 2019-04-07 21:25
 * @version: V1.0.0
 */
public class HeapSort {

    int[] sort(int[] originalArray) {

        int[] middle = heapify(originalArray);

        return sortInner(middle);
    }

    private int[] sortInner(int[] middleArray) {
        for (int i = middleArray.length - 1; i > 1; i--) {
            //swap top and last of unsort part.
            int tmp = middleArray[1];
            middleArray[1] = middleArray[i];
            middleArray[i] = tmp;
            heapify(middleArray,1,i);
        }
        return middleArray;
    }

    private int[] heapify(int[] originalArray) {
        int start = originalArray.length / 2 + 1;
        for (int i = start; i > 0; i--) {
            heapify(originalArray, i, originalArray.length);
        }
        return originalArray;
    }

    private void heapify(int[] originalArray, int currentIndex, int totalLen) {
        int leftNodeIndex = currentIndex * 2;
        boolean hasLeftNodeIndex = leftNodeIndex < totalLen;
        if (!hasLeftNodeIndex) {
            return;
        }
        int rightNodeIndex = currentIndex * 2 + 1;
        boolean hasRightNode = rightNodeIndex < totalLen;

        boolean isNeedSwap = originalArray[leftNodeIndex] > originalArray[currentIndex]
                || (hasRightNode && originalArray[leftNodeIndex + 1] > originalArray[currentIndex]);
        if (!isNeedSwap) {
            return;
        }

        boolean isLeftNeedSwap = !hasRightNode || originalArray[leftNodeIndex] > originalArray[rightNodeIndex];
        if (isLeftNeedSwap) {
            int tmp = originalArray[currentIndex];
            originalArray[currentIndex] = originalArray[leftNodeIndex];
            originalArray[leftNodeIndex] = tmp;
            heapify(originalArray, leftNodeIndex, totalLen);
            return;
        } else {
            int tmp = originalArray[currentIndex];
            originalArray[currentIndex] = originalArray[rightNodeIndex];
            originalArray[rightNodeIndex] = tmp;
            heapify(originalArray, rightNodeIndex, totalLen);
        }
    }

    @Test
    public void test() {
        int[] arrays = new int[] { -1, 1, 3, 5, 2, 4 };
        Assert.assertEquals(sort(arrays), new int[] { -1, 1, 2, 3, 4, 5 });
    }
}