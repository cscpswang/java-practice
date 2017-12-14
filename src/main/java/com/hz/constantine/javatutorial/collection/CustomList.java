/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.collection;

import java.util.AbstractList;
import java.util.List;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/11/24 9:35
 * @version: V1.0.0
 */
public class CustomList extends AbstractList<String> {

    private String[] a;

    CustomList(String[] a) {
        this.a = a;
    }

    @Override
    public String get(int index) {
        return a[index];
    }

    @Override
    public int size() {
        return a.length;
    }

    public static void main(String[] args) {
        List<String> list = new CustomList(new String[] { "1" });
        System.out.println(list);
    }
}