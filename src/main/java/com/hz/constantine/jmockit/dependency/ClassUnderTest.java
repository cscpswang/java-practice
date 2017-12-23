/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.dependency;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/7 下午6:46
 * @version: V1.0.0
 */
public final class ClassUnderTest {
    public static final String FIND_TARGET = "paper";
    public static final String DESC_MYSELF = "i'am constantine";

    private Eye eye;

    private Repository repository;

    public String action() {
        StringBuilder footpoint = new StringBuilder(10);
        footpoint.append(desc()).append(System.getProperty("line.separator"));
        String data = this.eye.find();
        footpoint.append("i'm find ").append(data).append(System.getProperty("line.separator"));
        this.repository.insert(data);
        footpoint.append("i'm insert ").append(data);
        return footpoint.toString();
    }

    private String desc(){
        return DESC_MYSELF;
    }

    public static class Eye {
        public String find() {
            return FIND_TARGET;
        }
    }

    public static class Repository {
        public void insert(String target) {
            System.out.println(target + " has been inserted!!! ");
        }
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}

