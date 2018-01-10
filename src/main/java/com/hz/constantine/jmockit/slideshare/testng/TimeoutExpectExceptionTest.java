/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.testng;

import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;


/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/10 下午3:18
 * @version: V1.0.0
 */
public class TimeoutExpectExceptionTest {

    @Test(timeOut = 5,expectedExceptions = {ThreadTimeoutException.class,NullPointerException.class})
    public void timeout(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }

}