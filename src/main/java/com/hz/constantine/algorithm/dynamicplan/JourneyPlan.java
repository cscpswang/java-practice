/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * @Description: 用动态规划解决旅行行程问题
 * 
 *               <pre>
 *       算法图解 9.2.6
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2018/12/20 9:29 AM
 * @version: V1.0.0
 */
public class JourneyPlan {

    private static final String CHURCH = "CHURCH";

    private static final String GLOBAL_THEATER = "GLOBAL_THEATER";

    private static final String NATIONAL_GALLERY = "NATIONAL_GALLERY";

    private static final String MUSEUM = "MUSEUM";

    private static final String PAUL_CHURCH = "PAUL_CHURCH";

    public List<String> plan() {
        List<Object[]> travelList = Lists.newArrayListWithCapacity(5);
        travelList.add(new Object[] { CHURCH, 0.5d, 7 });
        travelList.add(new Object[] { GLOBAL_THEATER, 0.5d, 6 });
        travelList.add(new Object[] { NATIONAL_GALLERY, 1d, 9 });
        travelList.add(new Object[] { MUSEUM, 2d, 9 });
        travelList.add(new Object[] { PAUL_CHURCH, 0.5d, 8 });

        // 行代表旅行地
        int row = 5;
        // 列代表天数
        int column = 4;
        // 取天数的最小公约数.
        double granularity = 0.5;
        // 矩阵. 行-列-分数.
        Integer[][] scoreMatrix = new Integer[row][column];
        List<String>[][] optimizeListMatrix = new ArrayList[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // 第一行，优化处理.
                if (i == 0) {
                    if ((Double) travelList.get(i)[1] <= granularity * j + 1) {
                        scoreMatrix[i][j] = (Integer) travelList.get(i)[2];
                        optimizeListMatrix[i][j] = Lists.newArrayList((String) travelList.get(i)[0]);
                    } else {
                        scoreMatrix[i][j] = 0;
                        optimizeListMatrix[i][j] = Lists.newArrayList();
                    }
                    continue;
                }

                /** 计算公示 : 评分= Max( score[i-1][j], score[i-1][j-targetScore/granularity]+targetScore **/
                // score[i-1][j] : (不包含本商品,j天约束下最高评分)
                // score[i-1][j-targetScore/granularity]+targetScore : (扣除本商品占用天数的最高评分 + 本商品的评分)
                Integer scoreNotIncludeThis = scoreMatrix[i - 1][j];
                // 如果当前列/granularity 小于 本商品占用天数,即本商品天数过高，则直接用不包含本商品的最高分数
                if (j < new Double((Double) travelList.get(i)[1] / granularity).intValue()) {
                    scoreMatrix[i][j] = scoreNotIncludeThis;
                    optimizeListMatrix[i][j] = new ArrayList<>(optimizeListMatrix[i - 1][j]);
                    continue;
                }

                int indexOfNotIncludeThisScore = j - new Double((Double) travelList.get(i)[1] / granularity).intValue();
                Integer scoreIncludeThis = scoreMatrix[i - 1][indexOfNotIncludeThisScore]
                        + (Integer) travelList.get(i)[2];
                if (scoreNotIncludeThis > scoreIncludeThis) {
                    scoreMatrix[i][j] = scoreNotIncludeThis;
                    optimizeListMatrix[i][j] = new ArrayList<>(optimizeListMatrix[i - 1][j]);
                } else {
                    scoreMatrix[i][j] = scoreIncludeThis;
                    List<String> tmp = new ArrayList<>(optimizeListMatrix[i - 1][indexOfNotIncludeThisScore]);
                    tmp.add((String) travelList.get(i)[0]);
                    optimizeListMatrix[i][j] = tmp;
                }
            }
        }

        return optimizeListMatrix[row - 1][column - 1];
    }

    @Test
    public void test() {
        JourneyPlan dynamicPlan = new JourneyPlan();
        List<String> result = dynamicPlan.plan();
        Assert.assertEquals(result, Lists.newArrayList(CHURCH, NATIONAL_GALLERY, PAUL_CHURCH));
    }
}