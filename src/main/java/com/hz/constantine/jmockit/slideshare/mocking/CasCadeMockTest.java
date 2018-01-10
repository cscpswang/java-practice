/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import mockit.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;

/**
 * @Description: 级联Mock
 * @author: xianghaibo
 * @date: 2018/1/8 18:57
 * @version: V1.0.0
 */
public class CasCadeMockTest {

    @Mocked
    ClassUnderTest classUnderTest;

    @Mocked
    ClassUnderTest.Alias aliasMockedInstance;

    @Capturing
    ClassUnderTest.Eye eyeCapturingInstance;

    @Injectable
    ClassUnderTest.Repository repositoryInjectInstance;

    /**
    * 被mock对象的依赖也被级联mock
    */
    @Test
    public void cascadeMockTest() {
        ClassUnderTest classUnderTest1 = new ClassUnderTest();

        Assert.assertEquals(classUnderTest1.getAlias(), aliasMockedInstance);
        Assert.assertEquals(classUnderTest1.getEye(), eyeCapturingInstance);
        Assert.assertEquals(classUnderTest1.getRepository(), repositoryInjectInstance);
    }

}