/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import mockit.internal.reflection.MethodReflection;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/21 下午4:29
 * @version: V1.0.0
 */
public class MockApiPrivateMethod {

    ClassUnderTest testedUnit = new ClassUnderTest();

    ClassUnderTest.Eye eye = new ClassUnderTest.Eye();

    ClassUnderTest.Repository repository = new ClassUnderTest.Repository();

    @BeforeClass
    public void before(){
        testedUnit.setEye(eye);
        testedUnit.setRepository(repository);
    }

    @Test
    public void privateMethod()throws Exception{
        final String desc = "test-desc";
        new Expectations(){
            {
                MethodReflection.invoke(ClassUnderTest.class,ClassUnderTest.class.getDeclaredMethod("desc"));
                result = desc;
            }
        };

        String result = testedUnit.action();

        System.out.println(result);

        Assert.assertTrue(result.contains(desc));
    }

}