/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/9/13 下午11:27
 * @version: V1.0.0
 */
public class DCArraySum {

    public int sum(int[] array){
        return sum(array,0);
    }

    private int sum(int[] array,int middle){
        if(array.length==1){
            return middle+array[0];
        }
        if(array.length==0){
            return middle;
        }
        int[] newArray = new int[array.length-1];
        System.arraycopy(array,1,newArray,0,newArray.length);
        return sum(newArray,middle+array[0]);
    }

    @Test
    public void test1(){
        int result = new DCArraySum().sum(new int[]{1,2,3});
        Assert.assertEquals(result,6);
    }
}