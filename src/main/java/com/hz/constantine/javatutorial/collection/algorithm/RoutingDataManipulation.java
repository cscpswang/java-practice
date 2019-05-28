/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: Practice for RoutingDataManipulation
 * @author: xianghaibo
 * @date: 2017/11/6 9:26
 * @version: V1.0.0
 */
public class RoutingDataManipulation {

    private static void reverse() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        Collections.reverse(list0);
        System.out.println(list0);
    }

    private static void fill() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        Collections.fill(list0, "fillObject");
        System.out.println(list0);
    }

    private static void copy() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        List<String> list1 = Arrays.asList(new String[] { "4", "5", "6", "7", "8" });
        Collections.copy(list1, list0);
        System.out.println(list1);
    }

    private static void swap() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        Collections.swap(list0, 1, 2);
        System.out.println(list0);
    }

    private static void addAll() {
        List<String> list0 =new ArrayList<>();
        List<String> list1 = Arrays.asList(new String[] { "4", "5", "6", "7", "8" });
        Collections.addAll(list0,list1.toArray(new String[0]));
        System.out.println(list0);
    }

    public static void main(String[] args) {
        copy();
    }

}