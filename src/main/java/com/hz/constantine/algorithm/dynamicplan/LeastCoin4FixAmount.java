package com.hz.constantine.algorithm.dynamicplan;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description:
 * 
 *               <pre>
 *     多个面值的硬币，用最少的硬币支付一个面值金额.
 *     see : https://time.geekbang.org/column/article/75702
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-09-18 09:52
 * @version: V1.0.0
 */
public class LeastCoin4FixAmount {

    public int leastCoin(int[] coinDenomination,  int remainAmount) {
        if (remainAmount <= 0) {
            return 0;
        }
        if (remainAmount <= coinDenomination[0]) {
            return 1;
        }

        // f(n) = 1+min(f(1),f(2)...)
        int minNextLeastCoin = Integer.MAX_VALUE;
        for (int i : coinDenomination) {
            final int itemLeastCoin = leastCoin(coinDenomination, remainAmount - i);
            minNextLeastCoin = itemLeastCoin < minNextLeastCoin ? itemLeastCoin : minNextLeastCoin;
        }
        return 1 + minNextLeastCoin;
    }

    @Test
    public void test() {
        Assert.assertEquals(leastCoin(new int[] { 1, 3, 5 }, 10), 2);
        Assert.assertEquals(leastCoin(new int[] { 1, 3, 5 }, 12), 3);
        Assert.assertEquals(leastCoin(new int[] { 1, 3, 4, 5 }, 11), 3);
    }
}