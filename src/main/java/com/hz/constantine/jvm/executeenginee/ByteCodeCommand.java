/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jvm.executeenginee;

import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/4/6 下午3:00
 * @version: V1.0.0
 */
public class ByteCodeCommand {

    /**
    * 窄化
    */
    @Test
    public void narrow(){
        float i = 1024000112234.1f;
        int j = ((int) i);
        System.out.println(j);
    }

}