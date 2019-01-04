/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @Description: 字符串的最长公共子序列
 * 
 *               <pre>
 *     算法图解. 9.3.5
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2018/12/22 5:04 PM
 * @version: V1.0.0
 */
public class SameSerializePartOfString {

    public int sameSerializePart(String strA, String strB) {
        int result = 0;
        int[][] matrix = new int[strA.length()][strB.length()];
        for (int i = 0; i < strA.length(); i++) {
            for (int j = 0; j < strB.length(); j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    matrix[i][j] = i == 0 || j == 0 ? 1 : matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = i == 0 && j == 0 ? 0
                            : (i == 0 ? matrix[i][j - 1]
                                    : (j == 0 ? matrix[i - 1][j] : Math.max(matrix[i - 1][j], matrix[i][j - 1])));
                }
                if (result < matrix[i][j]) {
                    result = matrix[i][j];
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        SameSerializePartOfString sameSerializePartOfString = new SameSerializePartOfString();
        String strA = "FOSH";
        String strB = "FISH";
        int sameSerializePart = sameSerializePartOfString.sameSerializePart(strA, strB);
        Assert.assertEquals(sameSerializePart, 3);
    }
}