package com.hz.constantine.jdk8;

import org.testng.annotations.Test;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-06-22 21:58
 * @version: V1.0.0
 */
public class InterfaceTest {

    @Test
    public void test(){
        System.out.println(Boy.getName());
        Boy boy = new Boy() {
        };
        System.out.println(boy.isGirl());
    }


    interface Boy{
        /**
         * 获得名称
         * @return 名称
         */
        static String getName(){
            return "anan";
        }

        /**
         * 是否女孩
         * @return 是否女孩
         */
        default Boolean isGirl(){
            return false;
        }

    }
}