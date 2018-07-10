/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/7/2 上午9:26
 * @version: V1.0.0
 */
public class MetaAnnotation {

    @Target(ElementType.LOCAL_VARIABLE)
    @interface TestAnnotation {

    }

    public void target() {

        @TestAnnotation
        Integer a = 1;


    }
}