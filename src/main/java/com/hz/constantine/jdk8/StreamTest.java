package com.hz.constantine.jdk8;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-06-22 21:29
 * @version: V1.0.0
 */
public class StreamTest {

    @Test
    public void test() {
        List<String> collect = Stream.of("1,3", "2,4").flatMap(o -> Stream.of(o.split(",")))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}