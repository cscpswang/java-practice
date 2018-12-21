/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 字符串的最大公共部分
 * 
 *               <pre>
 *     算法图解. 9.3.3
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2018/12/21 9:50 AM
 * @version: V1.0.0
 */
public class SamePartOfString {

    private String samePartOf(String strA, String strB) {
        char[] charA = strA.toCharArray();
        char[] charB = strB.toCharArray();

        String maxSamePart = new String();
        String[][] matrix = new String[charA.length][charB.length];

        for (int i = 0; i < charA.length; i++) {
            for (int j = 0; j < charB.length; j++) {
                if (charA[i] != charB[j]) {
                    matrix[i][j] = "";
                    continue;
                }
                if (i == 0 || j == 0) {
                    matrix[i][j] = new String(new char[] { charA[i] });
                    if (maxSamePart.length() < 1) {
                        maxSamePart = matrix[i][j];
                    }
                    continue;
                }

                matrix[i][j] = matrix[i - 1][j - 1] + new String(new char[] { charA[i] });
                if (matrix[i][j].length() > maxSamePart.length()) {
                    maxSamePart = matrix[i][j];
                }
            }
        }

        return maxSamePart;
    }

    @Test
    public void test() {
        String strA = "fish";
        String strB = "vshA";
        SamePartOfString similarityOfStringSimple = new SamePartOfString();
        String result = similarityOfStringSimple.samePartOf(strA, strB);
        Assert.assertEquals(result, "sh");
    }
}