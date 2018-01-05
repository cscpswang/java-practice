/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}