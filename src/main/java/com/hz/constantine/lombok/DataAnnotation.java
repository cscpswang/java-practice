/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.lombok;

import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/5/13 上午9:46
 * @version: V1.0.0
 */
@Data
public class DataAnnotation {

    private int i =1;

    private int j =2;

    @Test
    public void test(){
        DataAnnotation dataAnnotation = new DataAnnotation();
        Assert.assertEquals(dataAnnotation.getI(),i);
    }

}