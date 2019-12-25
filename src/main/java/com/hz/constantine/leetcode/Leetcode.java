package com.hz.constantine.leetcode;

import com.hz.constantine.practice.PracticeTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: for leetcode
 * @author: xiangji
 * @date: 2019-12-07 22:54
 * @version: V1.0.0
 */
public class Leetcode {

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }

        Map<String, String> digitMap = new HashMap<>(9);
        digitMap.put("2", "abc");
        digitMap.put("3", "def");
        digitMap.put("4", "ghi");
        digitMap.put("5", "jkl");
        digitMap.put("6", "mno");
        digitMap.put("7", "pqrs");
        digitMap.put("8", "tuv");
        digitMap.put("9", "wxyz");

        char[] digitCharArray = digits.toCharArray();

        List<String> result = new ArrayList<>();
        recursiveInner(digits, digitMap, "", 0, result);

        return result;
    }

    private void recursiveInner(String digits, Map<String, String> digitMap, String prefix, int index,
            List<String> result) {
        if (index >= digits.length()) {
            result.add(prefix);
            return;
        }

        String digitValueStr = digitMap.get(digits.substring(index, index + 1));

        for (int i = 0; i < digitValueStr.length(); i++) {
            recursiveInner(digits, digitMap, prefix + digitValueStr.substring(i, i + 1), index + 1, result);
        }
    }

    @Test
    public void test() {
        Leetcode practiceTest = new Leetcode();
        System.out.println(practiceTest.letterCombinations("23"));
    }
}