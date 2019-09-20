/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.lombok;

import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/5/13 上午9:46
 * @version: V1.0.0
 */
@Data
public class DataAnnotation {

    private int i = 1;

    private int j = 2;

    @NonNull
    private String s;

    @Test
    public void test() {
        DataAnnotation dataAnnotation = new DataAnnotation(null);
        Assert.assertEquals(dataAnnotation.getI(), i);
    }

    @SneakyThrows(InterruptedException.class)
    public void testSneakyThrows(String[] args) {
        Thread.sleep(1L);
    }

    @SneakyThrows
    public void cleanUp(){
        @Cleanup
        InputStream inputStream = new FileInputStream("/user/xiangji/test.out");
    }
}