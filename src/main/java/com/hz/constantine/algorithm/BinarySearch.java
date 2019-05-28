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

    enum Mode{
        /**
         * 普通二分查找
         */
        NORMAL(1)
        /**
        * 第一个等于给定值
        */
        ,FIRST(2)
        /**
        * 最后一个等于给定值
        */
        ,LAST(3)
        /**
        * 第一个大于给定值
        */
        ,GREATER(4)
        /**
        * 第一个小于给定值
        */
        ,LESS(5)
        /**
        * 循环有序数组
        */
        ,CYCLE(6);

        private int value;

        Mode(int value) {
            this.value = value;
        }
    }

    public int binarySearch(List<String> list, String target,Mode mode) {
        int start = 0;
        int end = list.size() - 1;
        while (end >= start) {
            int mid = start + ((end - start)>> 1) ;
            if (list.get(mid).equals(target)) {
                switch (mode){
                    case FIRST: if(mid ==0 || !list.get(mid-1).equals(target)){
                        return mid;
                    }else{
                        end = mid - 1;
                    } break;
                    case NORMAL:return mid;
                    case LAST:if(mid ==0 || !list.get(mid+1).equals(target)){
                        return mid;
                    }else{
                        start = mid + 1;
                    }break;
                    case GREATER:if(mid ==0 || !list.get(mid+1).equals(target)){
                        return mid+1;
                    }else{
                        start = mid + 1;
                    }break;
                    case LESS: if(mid ==0 || !list.get(mid-1).equals(target)){
                        return mid-1;
                    }else{
                        end = mid - 1;
                    }break;
                    case CYCLE:return mid;
                    default: return mid;
                }
            }else if (list.get(mid).compareTo(target) > 0) {
                if(mode.equals(Mode.CYCLE) && list.get(start).compareTo(target) >0){
                        start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }else {
                if(mode.equals(Mode.CYCLE) && list.get(end).compareTo(target) <0){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void testBinarySearch() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "3"), "2",Mode.NORMAL), 1);
        Assert.assertEquals(binarySearch(Lists.newArrayList("a", "b", "c", "d", "e"), "a",Mode.NORMAL), 0);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.NORMAL), -1);
    }

    @Test
    public void testBinarySearchFirst() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "4", "6", "6", "8", "10"), "6",Mode.FIRST), 3);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.FIRST), -1);
    }

    @Test
    public void testBinarySearchLast() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "4", "6", "6", "8", "10"), "6",Mode.LAST), 4);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.LAST), -1);
    }

    @Test
    public void testBinarySearchGrater() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "4", "6", "6", "8", "10"), "6",Mode.GREATER), 5);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.GREATER), -1);
    }

    @Test
    public void testBinarySearchLess() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("1", "2", "4", "6", "6", "8", "10"), "6",Mode.LESS), 2);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.LESS), -1);
    }

    @Test
    public void testBinarySearchCycle() {
        Assert.assertEquals(binarySearch(Lists.newArrayList("4", "6", "8", "8", "10", "1", "2"), "6",Mode.CYCLE), 1);
        Assert.assertEquals(binarySearch(Lists.newArrayList("1"), "2",Mode.CYCLE), -1);
    }
}