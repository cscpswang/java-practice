/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 只用在verification块里
 * @author: xianghaibo
 * @date: 2018/1/5 17:29
 * @version: V1.0.0
 */
public class CapturingTest {

    @Tested
    ClassUnderTest classUnderTest;

    @Test
    public void capturing(@Injectable ClassUnderTest.Eye eye, @Injectable ClassUnderTest.Repository repository) {
        new Expectations() {
            {
                eye.find();
                result = ClassUnderTest.FIND_TARGET;
            }
        };

        classUnderTest.action();

        new Verifications() {
            {
                String s;
                repository.insert(s = withCapture());
                Assert.assertEquals(s, ClassUnderTest.FIND_TARGET);
            }
        };
    }

}