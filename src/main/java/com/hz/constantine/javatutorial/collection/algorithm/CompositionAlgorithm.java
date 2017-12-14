/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection.algorithm;

import com.google.common.collect.Lists;

import java.util.Collections;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/15 9:29
 * @version: V1.0.0
 */
public class CompositionAlgorithm {
    public static void main(String[] args) {
        System.out.println(Collections.frequency(Lists.newArrayList(1, 2, 3, 1, 1), 1));
        System.out.println(Collections.disjoint(Lists.newArrayList(1, 2, 3), Lists.newArrayList(3, 4, 5)));
    }

}