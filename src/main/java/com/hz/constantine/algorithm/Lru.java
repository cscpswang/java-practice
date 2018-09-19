/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Description: standard implement
 * @author: xiangji
 * @date: 2018/8/26 下午7:56
 * @version: V1.0.0
 */
public class Lru {

    public static final int CAPACITY = 10;

    private Map<String,String> map = new WeakHashMap<>();

    private LinkedList<String> lruList = new LinkedList<>();

    public void set(String key, String value) {
        if(map.size()+1>CAPACITY){
            map.remove(lruList.pollLast());
        }
        map.put(key,value);
        lruList.offerFirst(key);
    }

    public String get(String key) {
        lruList.remove(key);
        lruList.offerFirst(key);
        return map.get(key);
    }

    @Test
    public void lru() {
        Lru lru = new Lru();
        lru.set("1", "v1");
        Assert.assertEquals(lru.get("1"), "v1");
    }

    @Test
    public void lruOverLimit() {
        Lru lru = new Lru();
        for (int i = 0; i < CAPACITY + 1; i++) {
            lru.set(String.valueOf(i), "v" + i);
        }
        Assert.assertNull(lru.get("0"));
    }

    @Test
    public void lruOverLimitWithGet() {
        Lru lru = new Lru();
        for (int i = 0; i < CAPACITY + 1; i++) {
            lru.set(String.valueOf(i), "v" + i);
            if (i >= 5) {
                lru.get(String.valueOf(i - 5));
            }
        }
        Assert.assertNull(lru.get("5"));
    }

}