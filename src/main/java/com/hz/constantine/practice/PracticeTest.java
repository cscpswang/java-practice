/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.google.common.base.Joiner;
import com.sun.istack.internal.NotNull;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.jvm.hotspot.tools.StackTrace;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/14 上午9:28
 * @version: V1.0.0
 */
public class PracticeTest {

    @Test
    public void charset() {
        Assert.assertEquals(StandardCharsets.UTF_8, Charset.forName("UTF-8"));
    }

    @Test
    public void symbol() {
        Assert.assertEquals(System.getProperty("line.separator"), "\n");
    }

    @Test
    public void codec() throws UnsupportedEncodingException, DecoderException {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(new String(Hex.decodeHex("336197d359c0ec355fdaa9ef6f43310ce401030"), "utf-8"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void npe(){
        String s = null;
        s.equals("a");
        "a".equals(s);
    }

    @Test
    public void notNull(){
        @NotNull String s;
        s=null;
    }

    @Test
    public void strictFp(){
        Assert.assertNotEquals(0.05+0.01,0.06);
        Assert.assertNotEquals(add(0.05f,0.01),0.06);
        System.out.println(add(0.6710339f,0.04150553411984792));
        System.out.println(0.6710339f+0.04150553411984792);
    }

    private strictfp double add(float a,double b){
        return a+b;
    }

    static class A{
        public static int a(){
            return 1;
        }
    }

    static class B extends A{
        public static int a(){
            return 2;
        }
    }

    @Test
    public void inheritStatic(){
        Assert.assertEquals(B.a(),2);
    }

    @Test
    public void printStack(){
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            System.out.println(ste);
        }
    }

    static long a1 = 4l;
    static long a2 = ~(-1L << a1);
}