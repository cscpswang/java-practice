/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice.filesearch;

import java.io.File;
import java.util.Arrays;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/10/19 17:27
 * @version: V1.0.0
 */
public class GroupClassByPackageUtils {
    public static final String path = "E:\\workspace\\kaola-aftersale-compose\\kaola-refund-service-provider\\src\\main\\java";

    public static void deal(String keyword) {
        File dir = new File(path);
        retrieval(dir, keyword);
    }

    private static void retrieval(File direct, String keyword) {
        File[] matchedFiles = direct.listFiles(f -> f.isFile() && f.getName().contains(keyword));
        File[] subDirects = direct.listFiles(f -> f.isDirectory());
        if (subDirects.length > 0) {
            Arrays.asList(subDirects).forEach(f -> retrieval(f, keyword));
        }
        if (matchedFiles.length > 0) {
            System.out.printf("package:%s , num:%d \n",
                    direct.getAbsoluteFile().toString().replace(path, "").replace("\\", "."), matchedFiles.length);
        }
    }

    public static void main(String[] args) {
        String keyword = "RemoteApiImpl";
        GroupClassByPackageUtils.deal(keyword);
    }

}