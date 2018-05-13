/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.io;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/30 上午9:08
 * @version: V1.0.0
 */
public class PathTest {

    @Test
    public void create(){
        String path = "/Users/xiangji/IdeaProjects/java-practice/src/main/resource/invoice.txt";

        Path path1 = Paths.get("/Users/xiangji/IdeaProjects/java-practice/src/main/resource","invoice.txt");
        Path path2 = Paths.get(URI.create("file:///Users/xiangji/IdeaProjects/java-practice/src/main/resource/invoice.txt"));
        Path path3 = FileSystems.getDefault().getPath("/Users/xiangji/IdeaProjects/java-practice/src/main/resource","invoice.txt");

        Assert.assertEquals(path1.toString(),path);
        Assert.assertEquals(path2.toString(),path);
        Assert.assertEquals(path3.toString(),path);
    }

    @Test
    @SuppressWarnings("dupplicated")
    public void retrieverInfo4Absolute(){
        String pathStr = "/Users/xiangji/IdeaProjects/java-practice/src/main/resource/invoice.txt";

        Path path = Paths.get(pathStr);

        Assert.assertEquals(path.toString(),path.toString());
        Assert.assertEquals(path.getFileName().toString(),"invoice.txt");
        Assert.assertEquals(path.getNameCount(),8);
        Assert.assertEquals(path.getRoot().toString(),"/");
        Assert.assertEquals(path.getName(0).toString(),"Users");
        Assert.assertEquals(path.getParent().toString(),"/Users/xiangji/IdeaProjects/java-practice/src/main/resource");
        Assert.assertEquals(path.subpath(0,2).toString(),"Users/xiangji");

    }

    @Test
    public void retrieverInfo4Relative(){
        String pathStr = "test/invoice.txt";

        Path path = Paths.get(pathStr);

        Assert.assertEquals(path.toString(),path.toString());
        Assert.assertEquals(path.getFileName().toString(),"invoice.txt");
        Assert.assertEquals(path.getNameCount(),2);
        Assert.assertNull(path.getRoot());
        Assert.assertEquals(path.getName(0).toString(),"test");
        Assert.assertEquals(path.getParent().toString(),"test");
        Assert.assertEquals(path.subpath(0,2).toString(),"test/invoice.txt");


    }

    @Test
    public void convertToUri(){
        String pathStr = "test/invoice.txt";

        Path path = Paths.get(pathStr);

        Assert.assertEquals(path.toUri(),URI.create("file:///Users/xiangji/IdeaProjects/java-practice/test/invoice.txt"));
    }

    @Test
    public void convertToAbsolute(){
        String pathStr = "test/invoice.txt";

        Path path = Paths.get(pathStr);

        Assert.assertEquals(path.toAbsolutePath().toUri(),URI.create("file:///Users/xiangji/IdeaProjects/java-practice/test/invoice.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void convertToReal() throws IOException {
        String pathStr = "test/invoice.txt";

        Path path = Paths.get(pathStr);

        path.toRealPath();
    }

    @Test
    public void resolve(){
        String pathStr = "test/invoice.txt";

        Path path = Paths.get(pathStr);

        Assert.assertEquals(path.resolve("kk").toString(),"test/invoice.txt/kk");
    }

    @Test
    public void relativize(){
        Path p1 = Paths.get("/home/a");
        Path p2 = Paths.get("/home/b/c");
        Path p1_p2 = p1.relativize(p2);

        Assert.assertEquals(p1_p2.toString(),"../b/c");
    }

    @Test
    public void iterator(){
        Path p1 = Paths.get("/home/a");
        int i =0;
        for(Iterator<Path> iterator = p1.iterator(); iterator.hasNext();){
            Path path = iterator.next();
            if(0==i){
                Assert.assertEquals(path.toString(),"home");
            }else {
                Assert.assertEquals(path.toString(),"a");
            }
            i++;
        }
    }

    @Test
    public void compare(){
        Path p1 = Paths.get("/home/a");
        Path p2 = Paths.get("/home/a");

        Assert.assertEquals(p1,p2);
        Assert.assertTrue(p1.startsWith(Paths.get("/home")));
        Assert.assertTrue(p1.endsWith(Paths.get("a")));
    }
}