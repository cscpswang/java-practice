/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.jconsole;

import org.testng.collections.Lists;

import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/7 上午9:10
 * @version: V1.0.0
 */
public class TestJconsoleVM {

    static class placeholder{
        byte[] content = new byte[1024*64];
    }

    private void fill(List<placeholder> list){
        list.add(new placeholder());
    }

    public static void main(String[] args)throws Exception {
        Thread.sleep(5000);
        TestJconsoleVM testJonsole = new TestJconsoleVM();
        List<placeholder> list = Lists.newArrayList();
        for (int i = 0; i <1000 ; i++) {
            testJonsole.fill(list);
            Thread.sleep(50);
        }
        
        System.gc();
        Thread.sleep(30000);
    }

}