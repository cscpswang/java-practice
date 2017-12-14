/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Description: see java tutorial collection implementation exercises
 * @author: xianghaibo
 * @date: 2017/10/26 9:13
 * @version: V1.0.0
 */
public class ImplementationExercises {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));
        System.out.println(lines.get(Integer.valueOf(args[1])));
    }
}