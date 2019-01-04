/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.knn;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 用knn算法推荐
 * 
 *               <pre>
 *     算法图解 10.1
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2018/12/25 9:28 AM
 * @version: V1.0.0
 */
public class KnnRecommend {

    public int[] recommend(int[] source, int[]... targets) {
        int nearestIndex = 0;
        double currentNearestValue = Double.MAX_VALUE;
        for (int i = 0; i < targets.length; i++) {
            int[] target = targets[i];
            // formula
            double nearestValue = 0;
            for (int j = 0; j < source.length; j++) {
                nearestValue += Math.pow(Math.abs(source[j] - target[j]), 2);
            }
            nearestValue = Math.sqrt(nearestValue);
            if (nearestValue < currentNearestValue) {
                currentNearestValue = nearestValue;
                nearestIndex = i;
            }
        }

        return targets[nearestIndex];
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