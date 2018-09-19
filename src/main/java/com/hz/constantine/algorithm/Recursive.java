/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/8 下午5:28
 * @version: V1.0.0
 */
public class Recursive {

    public List<Integer> countDown(List<Integer> list,int start) {
        if(start<0){
            return list;
        }else {
            list.add(start);
            return countDown(list,start-1);
        }
    }

    @Test
    public void test() {
        Integer[] result = new Integer[] { 3, 2, 1, 0 };
        Assert.assertEquals(countDown(Lists.newArrayList(),3), Arrays.asList(result));
    }
}