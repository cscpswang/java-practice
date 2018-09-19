/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/7/3 下午10:59
 * @version: V1.0.0
 */
public class CopyOnWriteList {

    @Test
    public void copyOnWrite4Read(){
        CopyOnWriteList var1 = new CopyOnWriteList();
        CopyOnWriteArrayList<CopyOnWriteList> list = Lists.newCopyOnWriteArrayList();
        list.add(var1);
        CopyOnWriteList s1 = list.get(0);
        list.add(var1);
        CopyOnWriteList s2 = list.get(0);

        System.out.println(s1.toString());
        System.out.println(s2.toString());
        Assert.assertFalse(s1 == s2);

    }

}