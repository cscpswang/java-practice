/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 插入排序算法
 * @author: xiangji
 * @date: 2018/9/30 上午9:23
 * @version: V1.0.0
 */
public class InsertionSort {

    public int[] sort(int[] arrays) {
        int aLen = arrays.length;
        for (int i = 0; i < aLen; i++) {
            for (int j=0;j<i;j++){
                if(arrays[j]>arrays[i]){
                    int tmp = arrays[j];
                    arrays[j] = arrays[i];
                    arrays[i] = tmp;
                }
            }
        }
        return arrays;
    }

    @Test
    public void test() {
        int[] arrays = new int[] { 1, 3, 5, 2, 4 };
        Assert.assertEquals(sort(arrays), new int[] { 1, 2, 3, 4, 5 });
    }

}