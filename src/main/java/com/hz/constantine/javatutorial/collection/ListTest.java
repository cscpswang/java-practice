/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/12/22 15:34
 * @version: V1.0.0
 */
public class ListTest {

    @Test
    public void equals() {
        List<String> src = Lists.newArrayList("a", "b", "c");
        List<String> dest = Lists.newArrayList("a", "b", "c");
        Assert.assertEquals(src, dest);
    }

}