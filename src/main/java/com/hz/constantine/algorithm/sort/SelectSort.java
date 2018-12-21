/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/8/7 下午4:14
 * @version: V1.0.0
 */
public class SelectSort {

    public String[] sort(String[] target) {
        String[] result = new String[target.length];
        for (int i = 0; i <target.length ; i++) {
            int indexOfMin = indexOfMin(target);
            result[i] = target[indexOfMin];
            target[indexOfMin] = null;
        }
        return result;
    }

    private int indexOfMin(String[] strings){
        int index = 0;
        String minStr = strings[0];
        for (int i = 1; i <strings.length ; i++) {
            if(minStr ==null || (strings[i]!=null && strings[i].compareTo(minStr)<0)){
                index = i;
                minStr = strings[i];
            }
        }
        return index;
    }

    @Test
    public void testSort1() {
        String[] result = sort(new String[] { "b", "c", "a" });
        Assert.assertEquals(result, new String[] { "a", "b", "c" });
    }

}