/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit;

import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/8 上午11:46
 * @version: V1.0.0
 */
public class FakeTest {
    class EchoServer {
        private Dependency dependency=new Dependency();

        public String echo(String msg) {
            return msg;
        }

        public void run(){
            dependency.run();
            System.out.println("echo server run");
        }
    }

    class Dependency {
        public void run(){
            System.out.println("dependency run");
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
        EchoServer echoServer = new EchoServer();

        final class DependencyMock extends MockUp<Dependency> {
            @Mock
            public void run(){
                System.out.println("after by mock Dependency");
            }
        }
        new DependencyMock();

        echoServer.run();
    }

}