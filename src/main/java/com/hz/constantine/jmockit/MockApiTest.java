/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit;

import mockit.Verifications;
import org.testng.annotations.Test;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/7 下午6:52
 * @version: V1.0.0
 */
public class MockApiTest {
    @Tested
    ClassUnderTest testedUnit;

    @Injectable
    ClassUnderTest.Eye eye;

    @Injectable
    ClassUnderTest.Repository repository;

    @Test
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

    @Test
    public void recordAndVerify(){
        final String data = "2";
        new Expectations() {
            {
                eye.find();
                result = data;
                repository.insert(data);
            }
        };

        testedUnit.action();

        new Verifications(){
            {
                eye.find();
                repository.insert(anyString);
            }
        };
    }

}