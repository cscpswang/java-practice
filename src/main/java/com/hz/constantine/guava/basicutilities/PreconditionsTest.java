/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.guava.basicutilities;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-02-28 13:54
 * @version: V1.0.0
 */
public class PreconditionsTest {

    public void test() {
        int num = 3;
        Preconditions.checkNotNull(new PreconditionsTest());
        Preconditions.checkArgument(num == 3);
    }

    public static void main(String[] args) {
        System.out.println(BigDecimal.ONE.divide(BigDecimal.valueOf(3), 2,RoundingMode.HALF_UP));
    }

}