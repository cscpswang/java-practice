/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import java.lang.reflect.InvocationTargetException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;

import mockit.*;
import mockit.internal.reflection.MethodReflection;

/**
 * @Description: Delegate表达式<br/>
 *               1.用在参数中<br/>
 *               2.用在result=表达式中
 * @author: xiangji
 * @date: 2018/1/4 下午5:14
 * @version: V1.0.0
 */
public class DelegateTest {

    @Tested
    ClassUnderTest classUnderTest;

    @Test
    public void verifyMiddleResultWithFake(@Injectable ClassUnderTest.Repository repository) {
        new MockUp<ClassUnderTest>() {
            @Mock
            @SuppressWarnings("unused")
            private boolean isNorth() {
                return false;
            }
        };

        final ClassUnderTest classUnderTest = new ClassUnderTest();
        classUnderTest.setRepository(repository);

        classUnderTest.saveIsSnow();

        new Verifications() {
            {
                repository.insert(with(new Delegate<String>() {
                    @SuppressWarnings("unused")
                    void delegate(String data) {
                        Assert.assertEquals(data, ClassUnderTest.IS_SNOW_NO);
                    }
                }));
                times = 1;
            }
        };
    }

    @Test(expectedExceptions = AssertionError.class)
    public void verifyMiddleResultWithMocking(@Tested @Injectable ClassUnderTest.Repository repository)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        new Expectations() {
            {
                MethodReflection.invoke(classUnderTest, ClassUnderTest.class.getDeclaredMethod("isNorth"));
                result = false;
            }
        };

        classUnderTest.saveIsSnow();

        new Verifications() {
            {
                repository.insert(with(new Delegate<String>() {
                    @SuppressWarnings("unused")
                    void delegate(String data) {
                        Assert.assertEquals(data, ClassUnderTest.IS_SNOW_NO);
                    }
                }));
            }
        };
    }
}