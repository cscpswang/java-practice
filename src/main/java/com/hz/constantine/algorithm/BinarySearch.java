/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/2 下午9:39
 * @version: V1.0.0
 */
public class BinarySearch {

    public int binarySearch(List<String> list, String target) {
        int start = 0;
        int end = list.size() - 1;
        int index = -1;
        while (true) {
            if (start >= end) {
                return index;
            }
            int mid = end / 2;
            if (list.get(mid).compareTo(target) == 0) {
                return mid;
            }
            if (target.compareTo(list.get(mid)) < 0) {
                end = mid - 1;
                continue;
            }
            start = mid + 1;
            continue;
        }
    }

    @Test
    public void test1() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "3"), "2"), 1);
    }

    @Test
    public void test2() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("a", "b", "c", "d", "e"), "a"), 0);
    }

    @Test
    public void test3() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2"), -1);
    }

}