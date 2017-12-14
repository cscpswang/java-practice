/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.xj.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/17 16:30
 * @version: V1.0.0
 */
public class LogbackTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("com.xj.test");
        logger.info("test12");
    }

}