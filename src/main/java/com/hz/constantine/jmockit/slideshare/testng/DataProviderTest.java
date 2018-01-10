/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/10 下午3:22
 * @version: V1.0.0
 */
public class DataProviderTest {
    static final String expectData = DataProviderTest.class.getSimpleName();

    static final class DataProvider{
        @org.testng.annotations.DataProvider(name = "str")
        public static Object[][] provide(){
            return new Object[][]{{expectData}};
        }
    }

    @Test(dataProvider = "str",dataProviderClass = DataProvider.class)
    public void dataProviderTest(String data){
        Assert.assertEquals(data,expectData);
    }

}