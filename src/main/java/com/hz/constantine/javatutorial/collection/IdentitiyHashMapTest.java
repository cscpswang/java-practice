/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/18 9:51
 * @version: V1.0.0
 */
public class IdentitiyHashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("a", 1);
        identityHashMap.put(new String("a"), 2);
        identityHashMap.put("a", 1);
        System.out.println(identityHashMap);

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put(new String("a"), 2);
        map.put("a", 1);
        System.out.println(map);
    }

}