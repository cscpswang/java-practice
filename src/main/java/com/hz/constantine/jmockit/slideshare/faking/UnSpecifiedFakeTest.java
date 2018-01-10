/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.faking;

import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 非确定性的.
 * @author: xiangji
 * @date: 2018/1/10 上午9:18
 * @version: V1.0.0
 */
public class UnSpecifiedFakeTest {
    interface Service{
        int getInt();
    }

    final class ServiceSimpleImp implements Service{

        @Override public int getInt() {
            return 1;
        }
    }

    class TestUnit{
        final Service service1 = new ServiceSimpleImp();
        final Service service2 = new Service() {
            @Override public int getInt() {
                return 2;
            }
        };

        public int sum(){
            return service1.getInt()+service2.getInt();
        }

    }

    @Test
    public <T extends Service> void unSpecifiedTest(){
        new MockUp<T>(){

            @Mock
            int getInt(){
                return 7;
            }
        };

        TestUnit testUnit = new TestUnit();
        int actualSum = testUnit.sum();

        Assert.assertEquals(actualSum,14);
    }
}