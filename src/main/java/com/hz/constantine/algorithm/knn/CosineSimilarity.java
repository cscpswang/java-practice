/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.knn;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 用余弦相似度算法推荐
 * @author: xiangji
 * @date: 2018/12/28 9:45 AM
 * @version: V1.0.0
 */
public class CosineSimilarity {

    public int[] recommend(int[] source, int[]... targets) {

        int mostSimilarityIndex = 0;
        double mostSimilarityValue = -1;
        for (int i = 0; i < targets.length; i++) {
            double similarityValue = calculateCosineSimilarity(source, targets[i]);
            if (similarityValue > mostSimilarityValue) {
                mostSimilarityIndex = i;
                mostSimilarityValue = similarityValue;
            }
        }

        return targets[mostSimilarityIndex];
    }

    /**
     * 用欧几里得点积公式算余弦相似度.
     * 
     * @param source
     * @param target
     * @return
     */
    private double calculateCosineSimilarity(int[] source, int[] target) {
        double numerator = 0;
        double denominatorSource = 0;
        double denominatorTarget = 0;
        for (int i = 0; i < source.length; i++) {
            numerator += source[i] * target[i];
            denominatorSource += Math.pow(source[i], 2);
            denominatorTarget += Math.pow(target[i], 2);
        }
        denominatorSource = Math.sqrt(denominatorSource);
        denominatorTarget = Math.sqrt(denominatorTarget);
        return numerator / (denominatorSource * denominatorTarget);
    }

    @Test
    public void test() {
        // 喜剧片、动作片、生活片、恐怖片、爱情片的打分
        int[] priyanka = new int[] { 3, 4, 4, 1, 4 };
        int[] justin = new int[] { 4, 3, 5, 1, 5 };
        int[] morpheus = new int[] { 2, 5, 1, 3, 1 };

        Assert.assertEquals(recommend(priyanka, justin, morpheus), justin);
    }

}