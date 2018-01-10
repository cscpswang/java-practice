/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.faking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.hz.constantine.jmockit.dependency.ClassUnderTest.*;

/**
 * @Description: 在faking里获取真实实例的上下文
 * @author: xiangji
 * @date: 2018/1/10 上午9:38
 * @version: V1.0.0
 */
public class InvocationContextTest {

    @Test
    public void invocationContextTest() {
        String fakeStr = this.getClass().getSimpleName();

        final class NameFake extends MockUp<ClassUnderTest.Name> {
            @Mock
            public String toString(Invocation invocation) {
                ClassUnderTest.Name actualInstance = (ClassUnderTest.Name) invocation.getInvokedInstance();
                Assert.assertEquals(actualInstance.name,DEFAULT_NAME);
                return fakeStr;
            }
        }
        new NameFake();

        String actualResult = new ClassUnderTest.Name().toString();

        Assert.assertEquals(actualResult,fakeStr);
    }

}