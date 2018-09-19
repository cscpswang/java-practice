/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.commonlang;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/7/10 下午3:13
 * @version: V1.0.0
 */
public class MapsTest {

    @Test
    public void uniqueIndex() {
        List<String> list = Lists.newArrayList("abc", "add");
        Map<String, String> result = Maps.uniqueIndex(list, new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.substring(0, 2);
            }
        });

        Assert.assertEquals(result.size(), 2);
        Assert.assertTrue(result.containsKey("ab"));
        Assert.assertEquals(result.get("ab"), "abc");
    }

}