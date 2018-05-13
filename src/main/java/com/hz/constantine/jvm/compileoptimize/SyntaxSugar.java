/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.compileoptimize;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/3/21 上午9:17
 * @version: V1.0.0
 */
public class SyntaxSugar {

    @Test
    public void sugar(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        Assert.assertTrue(c ==d);
        Assert.assertFalse(e ==f);
        Assert.assertTrue((a+b) ==c);
        Assert.assertTrue((a+b) ==g);
        Assert.assertFalse(c.equals(g));
        Assert.assertTrue(c.equals(a+b));

        Assert.assertTrue(e.equals(f));
        Assert.assertTrue(e.compareTo(f)==0);
        Assert.assertTrue(g.compareTo(c.longValue())==0);
    }

}