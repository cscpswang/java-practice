/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.executeenginee;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/3/8 上午9:23
 * @version: V1.0.0
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public String sayHello(Human human) {
        return "i'm a human";
    }

    public String sayHello(Man man) {
        return "i'm a man";
    }

    public String sayHello(Woman woman) {
        return "i'm a woman";
    }

    @Test
    public void staticType() {
        Human man1 = new Man();
        Human man2 = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();

        Assert.assertEquals("i'm a human", staticDispatch.sayHello(man1));
        Assert.assertEquals("i'm a human", staticDispatch.sayHello(man2));

        //change static type.
        Assert.assertEquals("i'm a man", staticDispatch.sayHello((Man) man1));
        Assert.assertEquals("i'm a woman", staticDispatch.sayHello((Woman) man2));
    }

//    public String sayHello(char i) {
//        return "i'm char";
//    }

    public String sayHello(int i) {
        return "i'm int";
    }

    @Test
    public void staticDispatch(){
        StaticDispatch staticDispatch = new StaticDispatch();
        //Assert.assertEquals(staticDispatch.sayHello("a".charAt(0)),"i'm char");

        // annotate the method sayHello(char i)
        Assert.assertEquals(staticDispatch.sayHello("a".charAt(0)),"i'm int");
    }

}