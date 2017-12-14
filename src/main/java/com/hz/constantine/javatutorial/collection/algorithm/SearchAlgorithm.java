/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection.algorithm;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/15 9:15
 * @version: V1.0.0
 */
public class SearchAlgorithm {

    public static void main(String[] args) {
        List<String> comparedList = Lists.newArrayList("a", "c", "A");
        // compared order search hit
        System.out.println(comparedList.get(Collections.binarySearch(comparedList, "c")));
        // compared order search miss
        System.out.println(Collections.binarySearch(comparedList, "b"));
        System.out.println(Collections.binarySearch(comparedList, "d"));
        // -(insertion point)-1
        comparedList.add(-Collections.binarySearch(comparedList, "b") - 1, "b");
        System.out.println(comparedList);
    }

}