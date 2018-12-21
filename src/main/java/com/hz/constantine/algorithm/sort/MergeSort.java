/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 合并排序x,O(nlogN) 时间复杂度
 * @author: xiangji
 * @date: 2018/9/30 上午9:35
 * @version: V1.0.0
 */
public class MergeSort {

    @SuppressWarnings("Duplicates")
    public String[] sort(String[] target) {
        if (target.length <= 1) {
            return target;
        }
        String[] result = new String[target.length];

        int middleIndex = target.length >> 1;
        int leftPartLen = middleIndex;
        int rightPartLen = target.length - middleIndex;

        /** base line condition **/
        String[] leftPart = new String[leftPartLen];
        String[] rightPart = new String[rightPartLen];
        System.arraycopy(target, 0, leftPart, 0, leftPartLen);
        System.arraycopy(target, middleIndex, rightPart, 0, rightPartLen);

        leftPart = sort(leftPart);
        rightPart = sort(rightPart);

        /** out condition **/
        if (leftPart[leftPartLen - 1].compareTo(rightPart[0]) <= 0) {
            result = ArrayUtils.addAll(leftPart, rightPart);
            return result;
        }
        if (rightPart[rightPartLen - 1].compareTo(leftPart[0]) <= 0) {
            result = ArrayUtils.addAll(rightPart, leftPart);
            return result;
        }

        int leftStartIndex = 0;
        int rightStartIndex = 0;
        for (int i = 0; i < target.length; i++) {
            boolean leftSmaller = leftPart[leftStartIndex].compareTo(rightPart[rightStartIndex]) <= 0;
            if (leftSmaller) {
                result[i] = leftPart[leftStartIndex];
                leftStartIndex++;
            } else {
                result[i] = rightPart[rightStartIndex];
                rightStartIndex++;
            }
            if (leftStartIndex >= leftPartLen) {
                System.arraycopy(rightPart, rightStartIndex, result, i + 1, target.length - i - 1);
                break;
            }
            if (rightStartIndex >= rightPartLen) {
                System.arraycopy(leftPart, leftStartIndex, result, i + 1, target.length - i - 1);
                break;
            }
        }
        return result;
    }

    @Test
    public void test() {
        MergeSort mergeSort = new MergeSort();
        Assert.assertEquals(mergeSort.sort(new String[] { "a", "c", "f", "b", "e" }),
                new String[] { "a", "b", "c", "e", "f" });
    }

}