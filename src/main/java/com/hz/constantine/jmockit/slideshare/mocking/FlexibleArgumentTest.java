/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/5 上午10:06
 * @version: V1.0.0
 */
public class FlexibleArgumentTest {

    @Test
    public void anyFileds(@Mocked ClassUnderTest.Repository repository) {
        new Expectations() {
            {
                repository.insert(anyString);
            }
        };

        repository.insert(null);

        new Verifications() {
            {
                repository.insert((String) any);
                times = 1;
            }
        };
    }

    @Test
    public void withMethods(@Mocked ClassUnderTest.Repository repository) {
        new Expectations() {
            {
                repository.insert(withAny(""));
            }
        };

        repository.insert(null);

        new Verifications() {
            {
                repository.insert(withNull());
                times = 1;
            }
        };
    }
}