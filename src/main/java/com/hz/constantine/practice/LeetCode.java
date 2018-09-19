/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/5/26 下午7:12
 * @version: V1.0.0
 */
public class LeetCode {
    public int myAtoi(String str) {
        str = str.trim();
        int defaultResult = 0;

        /** only white space.  **/
        char[] chars = str.toCharArray();
        if (chars.length < 1) {
            return defaultResult;
        }

        List<Character> numberStr = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> signStr = Arrays.asList('-','+');
        int minLimit = new Double(-Math.pow(2, 31)).intValue();
        int maxLimit = new Double(Math.pow(2, 31) - 1).intValue();

        int startIndex = -1;
        int endIndex = 0;
        int minusSignCount = 0;
        boolean startNumberFlag = false;
        for (int i = 0; i < chars.length; i++, endIndex++) {
            if(!startNumberFlag){
                if(chars[i] == '-'){
                    minusSignCount ++;
                }

                if(numberStr.contains(chars[i])){
                    startNumberFlag = true;
                    startIndex = i;
                }

                if(!numberStr.contains(chars[i])&&!signStr.contains(chars[i])){
                    return 0;
                }
            }else {
                if(!numberStr.contains(chars[i])){
                    break;
                }
            }
        }

        if(startIndex == -1){
            return 0;
        }

        char[] resultChars = new char[endIndex-startIndex];
        System.arraycopy(chars, startIndex, resultChars, 0, resultChars.length);
        String resultStr = new String(resultChars);
        Long result;
        try {
            result = minusSignCount%2==0 ? Long.parseLong(resultStr) : -Long.parseLong(resultStr);
        }catch (NumberFormatException e){
            return 0;
        }
        if (result < minLimit) {
            return minLimit;
        }
        if (result > maxLimit) {
            return maxLimit;
        }
        return result.intValue();
    }

    @Test
    public void myAtoi1() {
        Assert.assertEquals(myAtoi("42"), 42);
    }

    @Test
    public void myAtoi2() {
        Assert.assertEquals(myAtoi("   -42"), -42);
    }

    @Test
    public void myAtoi3() {
        Assert.assertEquals(myAtoi("csddfds"), 0);
    }

    @Test
    public void myAtoi4() {
        Assert.assertEquals(myAtoi("4213 words"), 4213);
    }

    @Test
    public void myAtoi5() {
        Assert.assertEquals(myAtoi("-91283472332"), -2147483648);
    }

    @Test
    public void myAtoi6() {
        Assert.assertEquals(myAtoi("+1"), 1);
    }

    @Test
    public void myAtoi7() {
        Assert.assertEquals(myAtoi("+"), 0);
    }

    @Test
    public void myAtoi8() {
        Assert.assertEquals(myAtoi("  0000000000012345678"), 12345678);
    }

    @Test
    public void myAtoi9() {
        Assert.assertEquals(myAtoi("-+1"), -1);
    }
}