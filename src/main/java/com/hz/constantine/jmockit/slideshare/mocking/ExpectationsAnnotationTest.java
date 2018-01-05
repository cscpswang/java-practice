/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.internal.expectations.invocation.MissingInvocation;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/4 下午2:03
 * @version: V1.0.0
 */
public class ExpectationsAnnotationTest {
    @Tested
    ClassUnderTest classUnderTest;

    @Test
    public void expectationsPositive(@Injectable ClassUnderTest.Eye eye,@Injectable ClassUnderTest.Repository repository,@Injectable
            ClassUnderTest.Name name) {
        final String nameStr = String.valueOf(RandomUtils.nextInt(0, 999));
        final String data = String.valueOf(RandomUtils.nextInt(0, 999));

        new Expectations() {
            {
                name.toString();
                result = nameStr;
                eye.find();
                result = data;
                repository.insert(data);
            }
        };

        String result = classUnderTest.action();

        Assert.assertTrue(result.contains(data));
    }

    @Test(expectedExceptions = MissingInvocation.class)
    public void expectationsUnCorrectParam(@Injectable ClassUnderTest.Eye eye,@Injectable ClassUnderTest.Repository repository,@Injectable
            ClassUnderTest.Name name) {
        final String nameStr = String.valueOf(RandomUtils.nextInt(0, 999));
        final String data = String.valueOf(RandomUtils.nextInt(0, 999));

        new Expectations() {
            {
                name.toString();
                result = nameStr;
                eye.find();
                result = data;
                repository.insert(String.valueOf(RandomUtils.nextInt(1000, 2000)));
            }
        };

        String result = classUnderTest.action();

        Assert.assertTrue(result.contains(data));
    }

    /**
     * 1.times 默认为1. <br/>
     * 2.times=2,实际上repository.insert只执行了一次 <br/>
     * @param eye
     * @param repository
     * @param name
     */
    @Test(expectedExceptions = {MissingInvocation.class})
    public void expectationsUnCorrectTimes(@Injectable ClassUnderTest.Eye eye,@Injectable ClassUnderTest.Repository repository,@Injectable
            ClassUnderTest.Name name) {
        final String data = String.valueOf(RandomUtils.nextInt(0, 999));
        final String nameStr = String.valueOf(RandomUtils.nextInt(0, 999));

        new Expectations() {
            {
                name.toString();
                result = nameStr;
                eye.find();
                result = data;
                repository.insert(data);
                times = 2;
            }
        };

        String result = classUnderTest.action();

        Assert.assertTrue(result.contains(data));
    }

    /**
     *  期望repository.insert执行一次. 但实际并没有执行
     * @param repository
     */
    @Test(expectedExceptions = {MissingInvocation.class})
    public void expectationsLake(@Injectable ClassUnderTest.Repository repository){
        final String data = String.valueOf(RandomUtils.nextInt(0, 999));

        new Expectations() {
            {
                repository.insert(data);
                times = 1;
            }
        };
    }
}