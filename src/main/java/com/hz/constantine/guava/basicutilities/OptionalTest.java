/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.guava.basicutilities;

import com.google.common.base.Optional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/11/28 下午9:32
 * @version: V1.0.0
 */
public class OptionalTest {

    @Test
    public void get() {
        String nullable = null;
        Optional<String> optional = Optional.fromNullable(nullable);
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        optional= Optional.absent();
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
    }

    @Test
    public void inherit(){
        Optional<Boolean> optional = Optional.of(true);
        Optional<?> optional1 = Optional.of(optional);
        System.out.println(optional1.get());

    }

}