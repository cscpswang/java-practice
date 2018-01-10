/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.faking;

import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: faking, state-oriented
 * @author: xiangji
 * @date: 2017/12/8 上午11:46
 * @version: V1.0.0
 */
public class FakeSimpleTest {
    class EchoServer {
        private Dependency dependency=new Dependency();

        public String echo(String msg) {
            return msg;
        }

        public String run(){
           return dependency.run();
        }
    }

    class Dependency {
        public String run(){
            return "dependency run";
        }
    }

    @Test
    public void applyFakes() {
        final String msg = "i'm constantine";
        final String mockPrfeix = "mock,";

        final class EchoServerMock extends MockUp<EchoServer> {
            @Mock
            public String echo(String msg) {
                return mockPrfeix+msg;
            }
        }
        new EchoServerMock();

        String reply = new EchoServer().echo(msg);

        Assert.assertEquals(reply,mockPrfeix+msg);
    }

    @Test
    public void applyFakesWithDependency(){
        final String msg = "i'm constantine";
        EchoServer echoServer = new EchoServer();

        final class DependencyMock extends MockUp<Dependency> {
            @Mock
            public String run(){
                return msg;
            }
        }
        new DependencyMock();

        String actualMsg =  echoServer.run();

        Assert.assertEquals(actualMsg,msg);
    }

}