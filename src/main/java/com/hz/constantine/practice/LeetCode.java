/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/5/26 下午7:12
 * @version: V1.0.0
 */
public class LeetCode {
    public int reverse(int num) {
        if (num == -Math.pow(2, 31)) {
            return 0;
        }

        // get signed
        String firstPad = "";
        if (num < 0) {
            firstPad = "-";
        }

        // reverse the num.
        char[] chars = String.valueOf(Math.abs(num)).toCharArray();
        char[] reverseChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reverseChars[chars.length - i - 1] = chars[i];
        }
        String resultStr = firstPad + new String(reverseChars);

        // make it not over flow.
        double result = Double.valueOf(resultStr);
        if (result >= Math.pow(2, 31) || result < -Math.pow(2, 31)) {
            return 0;
        }
        return Double.valueOf(result).intValue();
    }

    @Test
    public void reverse() {
        Assert.assertEquals(new LeetCode().reverse(123), 321);
    }

    @Test
    public void reverse1() {
        Assert.assertEquals(new LeetCode().reverse(-123), -321);
    }

    @Test
    public void reverse2() {
        Assert.assertEquals(new LeetCode().reverse(120), 21);
    }

    @Test
    public void reverseOverflow() {
        Assert.assertEquals(new LeetCode().reverse(Double.valueOf(Math.pow(2, 31)).intValue()), 0);
    }

    @Test
    public void reverse3() {
        Assert.assertEquals(new LeetCode().reverse(-2147483648), 0);
    }
}