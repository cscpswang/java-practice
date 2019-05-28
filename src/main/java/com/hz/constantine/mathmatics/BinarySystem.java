/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.mathmatics;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description:
 * 
 *               <pre>
 *          二进制: https://time.geekbang.org/column/article/71840
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-02-23 17:28
 * @version: V1.0.0
 */
public class BinarySystem {

    @Test
    public String binaryToDecimal(String value){
        BigInteger bigInteger = new BigInteger(value,2);
        return  bigInteger.toString();
    }

    public String decimalToBinary(String value){
        BigInteger bigInteger = new BigInteger(value);
        return bigInteger.toString(2);
    }

    @Test
    public void test(){
        BinarySystem binarySystem = new BinarySystem();
        Assert.assertEquals(binarySystem.binaryToDecimal("1010"),"10");
        Assert.assertEquals(binarySystem.decimalToBinary("10"),"1010");
    }
}