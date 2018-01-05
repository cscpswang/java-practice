/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/5 上午9:55
 * @version: V1.0.0
 */
public class RecordingResultTest {

    @Test
    public void multipleResult(@Mocked ClassUnderTest classUnderTest) {
        final String[] expectResult = new String[] { "1", "2", "3" };
        final RuntimeException runtimeException = new RuntimeException();

        new Expectations() {
            {
                classUnderTest.action();
                returns(expectResult[0], expectResult[1], expectResult[2]);
                result = runtimeException;
            }
        };

        assertEquals(new ClassUnderTest().action(), expectResult[0]);
        assertEquals(new ClassUnderTest().action(), expectResult[1]);
        assertEquals(new ClassUnderTest().action(), expectResult[2]);
        assertThrows(() -> {
            new ClassUnderTest().action();
        });

    }

}