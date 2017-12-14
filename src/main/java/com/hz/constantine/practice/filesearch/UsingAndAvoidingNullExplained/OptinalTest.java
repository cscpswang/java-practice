/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.xj.practice.filesearch.UsingAndAvoidingNullExplained;

import com.google.common.base.Optional;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/16 19:05
 * @version: V1.0.0
 */
public class OptinalTest {

    public static void main(String[] args) {
        String a = null;
        Optional<String> s = Optional.absent();
        System.out.println(s.isPresent());
        Optional.of("sdf");
    }

}