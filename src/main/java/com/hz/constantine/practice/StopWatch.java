/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice;

import com.google.common.base.Stopwatch;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/6/29 下午10:52
 * @version: V1.0.0
 */
public class StopWatch {

    @Test
    public void stopWatch(){
        Stopwatch stopwatch = Stopwatch.createStarted();

        System.out.println(stopwatch.toString());
    }
}