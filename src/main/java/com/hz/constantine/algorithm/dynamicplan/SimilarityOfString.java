/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
            if (a.charAt(0) == b.charAt(i)) {
                shortDistance[0][i] = i;
            } else {
                if (i == 0) {
                    shortDistance[0][i] = 1;
                } else {
                    shortDistance[0][i] = shortDistance[0][i - 1] + 1;
                }
            }
        }
        for (int i = 0; i < aLen; i++) {
            if (a.charAt(i) == b.charAt(0)) {
                shortDistance[i][0] = i;
            } else {
                if (i == 0) {
                    shortDistance[i][0] = 1;
                } else {
                    shortDistance[i][0] = shortDistance[i - 1][0] + 1;
                }
            }
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
        return BigDecimal.ONE.subtract(BigDecimal.valueOf(ld)
                .divide(BigDecimal.valueOf(Math.max(str1.length(), str2.length())), 2, RoundingMode.HALF_UP));
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

    @Test
    public void test() {
        String main = "onWHiTE出七外五位为您洛感up!3%30一名世茶用美白美容液之A1T9片匕产品细节包装一盒30片装，使用时间更长3S材质面膜纸选用长纤维无纺布材质，一片富含充足精华使用说明在洁面和水乳后，取出面膜敷在脸上，10-15分钟后取下，用温水洗净即可";
        String pattern = "使用方法";
        System.out.println(main.length());
        System.out.println(similarityWithDynamicPlan(main, "使用时间"));
        System.out.println(similarityWithDynamicPlan(main, "使用"));
        System.out.println(similarityWithDynamicPlan(main, "时间"));
        System.out.println(similarityWithDynamicPlan(main, "用时"));
        System.out.println(similarityWithDynamicPlan(main, "使用时"));
        System.out.println(similarityWithDynamicPlan(main, "使用xx时间"));
    }

    /**
     * see: https://time.geekbang.org/column/article/75794 莱文斯特距离.
     *
     */
    @Test
    public void testLDP() {
        Assert.assertEquals(shortestDistanceWithDynamicPlan("E1", "M2"), 2);
    }

}