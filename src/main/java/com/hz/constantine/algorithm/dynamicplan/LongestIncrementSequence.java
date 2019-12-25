package com.hz.constantine.algorithm.dynamicplan;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: 如:2,9,3,6,5,1,7 。 最长递增子序列: 2,3,5,7
 * @author: xiangji
 * @date: 2019-09-29 16:08
 * @version: V1.0.0
 */
public class LongestIncrementSequence {

    /**
     * 用动态规划计算最长递增子序列
     * 
     * @param originalSequence 原始序列.
     * @return 序列长度
     */
    private Integer[] cacluate(int[] originalSequence) {
        int[][] matrix = new int[originalSequence.length][originalSequence.length];
        Map<Integer, List<Integer>> longestIncrementSequenceMap = Maps
                .newHashMapWithExpectedSize(originalSequence.length);

        // 第一步, 填充矩阵第一行和第一列.
        for (int i = 0; i < originalSequence.length; i++) {
            matrix[0][i] = 1;
            matrix[i][0] = 1;
            longestIncrementSequenceMap.put(i, Lists.newLinkedList());
            longestIncrementSequenceMap.get(i).add(originalSequence[i]);
        }

        // 第二步，填充状态转移表，使用状态转移公式: if i < left: left else: left + 1;
        for (int matrixRowIndex = 1; matrixRowIndex < originalSequence.length; matrixRowIndex++) {
            for (int matrixColumnIndex = 1; matrixColumnIndex < originalSequence.length; matrixColumnIndex++) {
                if (originalSequence[matrixColumnIndex] < originalSequence[matrixColumnIndex - 1]) {
                    matrix[matrixRowIndex][matrixColumnIndex] = matrix[matrixRowIndex][matrixColumnIndex - 1];

                    // 直接用left的子序列, 如果子序列的倒数第二个值 小于 当前值，则替换.
                    final List<Integer> newList = Lists
                            .newArrayList(longestIncrementSequenceMap.get(matrixColumnIndex - 1));
                    if (newList.size() >= 2 && originalSequence[matrixColumnIndex] > newList.get(newList.size() - 2)) {
                        newList.set(newList.size() - 1, originalSequence[matrixColumnIndex]);
                    }
                    longestIncrementSequenceMap.put(matrixColumnIndex, newList);
                } else {
                    matrix[matrixRowIndex][matrixColumnIndex] = matrix[matrixRowIndex][matrixColumnIndex - 1] + 1;

                    // 复制left的子序列,并追加当前值.
                    final List<Integer> newList = Lists
                            .newArrayList(longestIncrementSequenceMap.get(matrixColumnIndex - 1));
                    newList.add(originalSequence[matrixColumnIndex]);
                    longestIncrementSequenceMap.put(matrixColumnIndex, newList);
                }
            }
        }

        // 第三步，取最后一行的最大值
        int maxSubSequenceLength = Integer.MIN_VALUE;

        List<Integer> longestIncrementSequence = null;
        for (List<Integer> value : longestIncrementSequenceMap.values()) {
            if (value.size() > maxSubSequenceLength) {
                maxSubSequenceLength = value.size();
                longestIncrementSequence = value;
            }
        }

        return null == longestIncrementSequence ? new Integer[] {} : longestIncrementSequence.toArray(new Integer[] {});
    }

    @Test
    public void test() {
        LongestIncrementSequence longestIncrementSequence = new LongestIncrementSequence();
        final Integer[] calculate = longestIncrementSequence.cacluate(new int[] { 2, 9, 3, 6, 5, 1, 7 });
        Assert.assertEquals(calculate, new Integer[] { 2, 3, 5, 7 }, calculate.toString());
    }

}