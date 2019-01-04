/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @Description: 字符串相似度算法
 * @author: xiangji
 * @date: 2018/9/19 下午3:02
 * @version: V1.0.0
 */
public class SimilarityOfString {

    public int shortestDistanceWithDynamicPlan(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (0 == aLen) {
            return bLen;
        }
        if (0 == bLen) {
            return aLen;
        }
        int[][] shortDistance = new int[aLen][bLen];

        /** fill fist row of matrix and first column of matrix **/
        for (int i = 0; i < bLen; i++) {
            shortDistance[0][i] = i;
        }
        for (int i = 0; i < aLen; i++) {
            shortDistance[i][0] = i;
        }
        /** use dynamic plan **/
        for (int i = 1; i < aLen; i++) {
            for (int j = 1; j < bLen; j++) {
                int abSubtract = a.charAt(i) == b.charAt(j) ? shortDistance[i - 1][j - 1]
                        : shortDistance[i - 1][j - 1] + 1;
                int aSubtract = shortDistance[i - 1][j] + 1;
                int bSubtract = shortDistance[i][j - 1] + 1;
                shortDistance[i][j] = Math.min(Math.min(aSubtract, bSubtract), abSubtract);
            }
        }
        return shortDistance[aLen - 1][bLen - 1];
    }

    public BigDecimal similarityWithDynamicPlan(String str1, String str2) {
        int ld = shortestDistanceWithDynamicPlan(str1, str2);
        // formula is: (1 - ld / max(str1.length(), str2.length()))
        return BigDecimal.ONE
                .subtract(BigDecimal.valueOf(ld).divide(BigDecimal.valueOf(Math.max(str1.length(), str2.length()))));
    }

    @Test
    public void similarityWithDynamicPlan() {
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "E123442342").doubleValue(), 1.0);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "E123442340").doubleValue(), 0.9);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "E12344234").doubleValue(), 0.9);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "E123442300").doubleValue(), 0.8);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "E1234423").doubleValue(), 0.8);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "123").doubleValue(), 0.3);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "342").doubleValue(), 0.3);
        Assert.assertEquals(similarityWithDynamicPlan("E123442342", "").doubleValue(), 0.0);
        Assert.assertEquals(similarityWithDynamicPlan("", "E123442342").doubleValue(), 0.0);
    }

}