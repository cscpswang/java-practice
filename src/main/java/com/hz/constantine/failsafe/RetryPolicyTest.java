package com.hz.constantine.failsafe;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description:  github : @link{https://github.com/jhalterman/failsafe}
 * @author: xiangji
 * @date: 2019-04-29 17:42
 * @version: V1.0.0
 */
public class RetryPolicyTest {


    @Test
    public void test(){

        RetryPolicy<Object> retryPolicy = new RetryPolicy<>().handle(Exception.class)
                .withDelay(Duration.ofSeconds(1))
                .withMaxRetries(3);

        Failsafe.with(retryPolicy).run(()->{
            System.out.println("run ,run ,run");
            throw new RuntimeException();
        });
    }

    public static void main(String[] args) {
        BigDecimal y = BigDecimal.ONE.setScale(2);

        BigDecimal x = BigDecimal.ONE.setScale(2);

        System.out.println(y.subtract(x.multiply(BigDecimal.valueOf(1)).divide(BigDecimal.valueOf(3),2, RoundingMode.HALF_UP)));
    }
}