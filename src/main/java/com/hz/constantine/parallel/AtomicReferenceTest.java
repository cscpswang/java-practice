/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.parallel;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/11/18 6:08 PM
 * @version: V1.0.0
 */
public class AtomicReferenceTest {

    private volatile int a;

    private volatile int b;

    @Test
    public void test(){
        AtomicReferenceTest referenceTest = new AtomicReferenceTest();
        referenceTest.a = 1;
        referenceTest.b = 2;

        AtomicReference<AtomicReferenceTest> atomicReference = new AtomicReference<>(referenceTest);
        atomicReference.compareAndSet(referenceTest,new AtomicReferenceTest());
    }

}