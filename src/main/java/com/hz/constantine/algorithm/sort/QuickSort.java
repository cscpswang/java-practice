/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 快速排序,O(nlogN) 时间复杂度
 * @author: xiangji
 * @date: 2018/11/6 11:08 AM
 * @version: V1.0.0
 */
@SuppressWarnings("Duplicates")
public class QuickSort {

    public String[] sort(String[] primitive) {
        if (primitive.length <= 1) {
            return primitive;
        }

        String baseLine = primitive[0];
        String[] smaller = new String[primitive.length - 1];
        String[] bigger = new String[primitive.length - 1];
        int smallerIndex = 0;
        int biggerIndex = 0;
        for (int i = 1; i < primitive.length; i++) {
            boolean isSmaller = primitive[i].compareTo(baseLine) <= 0;
            if (isSmaller) {
                smaller[smallerIndex] = primitive[i];
                smallerIndex++;
            } else {
                bigger[biggerIndex] = primitive[i];
                biggerIndex++;
            }
        }
        String[] smallerEnsure =new String[smallerIndex];
        String[] biggerEnsure =new String[biggerIndex];
        System.arraycopy(smaller,0,smallerEnsure,0,smallerIndex);
        System.arraycopy(bigger,0,biggerEnsure,0,biggerIndex);

        String[] smallerSorted = sort(smallerEnsure);
        String[] biggerSorted = sort(biggerEnsure);

        String[] result = new String[primitive.length];
        System.arraycopy(smallerSorted,0,result,0,smallerSorted.length);
        result[smallerSorted.length] = baseLine;
        System.arraycopy(biggerSorted,0,result,smallerSorted.length+1,biggerSorted.length);
        return result;
    }

    @Test
    public void test() {
        QuickSort quickSort = new QuickSort();
        Assert.assertEquals(quickSort.sort(new String[] { "a", "c", "k", "f", "b" }),
                new String[] { "a", "b", "c", "f", "k" });
    }
}