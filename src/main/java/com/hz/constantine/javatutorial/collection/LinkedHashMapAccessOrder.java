/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import java.util.LinkedHashMap;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/18 9:21
 * @version: V1.0.0
 */
public class LinkedHashMapAccessOrder {

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10, 0.75f, true);
        map.put("1", "first");
        map.put("2", "second");
        map.put("3", "third");
        map.put("4", "four");
        map.put("5", "fire");
        print(map);
        map.get("3");
        map.get("4");
        print(map);
    }

    private static void print(LinkedHashMap map) {
        System.out.println(map);
    }

}