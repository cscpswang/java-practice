/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/20 下午9:00
 * @version: V1.0.0
 */
public class MockApiArgTest {
    ClassUnderTest testedUnit = new ClassUnderTest();

    ClassUnderTest.Eye eye = new ClassUnderTest.Eye();

    ClassUnderTest.Repository repository = new ClassUnderTest.Repository();

    @BeforeClass
    public void before(){
        testedUnit.setEye(eye);
        testedUnit.setRepository(repository);
    }

    @Test
    public void changeResult(){
        final String data = "test-data";
        new Expectations(eye,repository){
            {
                eye.find();
                result = data;
                repository.insert(data);
                times = 1;
            }
        };

        String result = testedUnit.action();

        Assert.assertTrue(result.contains(data));
    }

}