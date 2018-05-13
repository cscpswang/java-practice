/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.io;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/2/3 下午3:56
 * @version: V1.0.0
 */
public class FileTest {

    @Test
    public void releaseAndException() {
        String fileStr = "usr/tmp/notExists";
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileStr))) {
            bufferedReader.readLine();
        } catch (FileSystemException e1) {
            Assert.assertEquals(e1.getFile(),fileStr);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void varargs() throws IOException {
        String source = "usr/tmp/source";
        String target = "usr/tmp/target";
        Files.move(Paths.get(source),Paths.get(target), StandardCopyOption.COPY_ATTRIBUTES,StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void globSyntax(){
        Assert.assertTrue(FileSystems.getDefault().getPathMatcher("glob:*").matches(Paths.get("a")));
        Assert.assertTrue(FileSystems.getDefault().getPathMatcher("glob:[0-9]").matches(Paths.get("1")));
        Assert.assertTrue(FileSystems.getDefault().getPathMatcher("glob:{1,2,3}?[a-z]").matches(Paths.get("1sd")));
    }


}