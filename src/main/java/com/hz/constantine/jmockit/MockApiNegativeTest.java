/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/12 下午4:08
 * @version: V1.0.0
 */
public class MockApiNegativeTest {
    @Tested
    ClassUnderTest testedUnit;

    ClassUnderTest.Eye eye;

    @Injectable
    ClassUnderTest.Repository repository;

    @Test(expectedExceptions = NullPointerException.class)
    public void exerciseUnitInIsolationFromDependency() {
        final String data = "2";
        new Expectations() {
            {
                eye.find();
                result = data;
                repository.insert(data);
            }
        };

        testedUnit.action();
    }
}