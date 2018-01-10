/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.dependency;

/**
 * @Description: 我是一个机器人<br/>
 *               我有名字，我会介绍自己<br/>
 *               我会查找数据，存储数据<br/>
 *               我会把消息告诉给其他人<br/>
 *               我能识别一个晶体是否是雪花<br/>
 * @author: xiangji
 * @date: 2017/12/7 下午6:46
 * @version: V1.0.0
 */
public class ClassUnderTest {
    public static final String FIND_TARGET = "paper ";

    public static final String DESC_MYSELF = "i'am constantine ";

    public static final String EATING = "i'm eating";

    public static final String DEFAULT_NAME = "default name";

    public static final String IS_SNOW_YES = "i see a snowflake";

    public static final String IS_SNOW_NO = "i see a crystal";

    public static final String NAME_PREFIX = "name: ";

    public static final String FIND_PREFIX = "i'm find ";

    public static final String INSERT_PREFIX = "i'm insert ";

    public static final String MQ_NOTIFY_SUFFIX = " send to other person";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Eye eye;

    private Repository repository;

    private Name name;

    private Alias alias;

    public ClassUnderTest(){
        this.name = new Name();
    }

    /**
     * 1.描述自己<br/>
     * 2.寻求信息<br/>
     * 3.存入自己的记忆<br/>
     * 4.发消息给主人<br/>
     * 5.上报自己的姓名<br/>
     * @return
     */
    public String action() {
        StringBuilder footPrint = new StringBuilder(10);
        footPrint.append(desc()).append(LINE_SEPARATOR);

        String data = this.eye.find();
        footPrint.append(FIND_PREFIX).append(data).append(LINE_SEPARATOR);

        this.repository.insert(data);
        footPrint.append(INSERT_PREFIX).append(data);

        Mq mq = new Mq();
        footPrint.append(mq.send(data + MQ_NOTIFY_SUFFIX)).append(LINE_SEPARATOR);

        footPrint.append(NAME_PREFIX + name).append(LINE_SEPARATOR);
        return footPrint.toString();
    }

    /**
    * 机器人判断面前的晶体是否是雪.并保存到记忆中.<br/>
    */
    public void saveIsSnow(){
        boolean isNorth = isNorth();
        if(isNorth){
            this.repository.insert(IS_SNOW_YES);
        }else{
            this.repository.insert(IS_SNOW_NO);
        }
    }

    private boolean isNorth(){
        return true;
    }

    private String desc() {
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

    public static class Mq {
        public String send(String msg) {
            return msg + " has been send!!! ";
        }
    }

    public static class Name {
        public String name;

        public Name(){this.name = DEFAULT_NAME;}

        public Name(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static class Alias {
        public String alias;

        public Alias(String alias){this.alias = alias;}

        @Override
        public String toString() {
            return this.alias;
        }
    }

    public static String eat(){
        return EATING;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public Eye getEye() {
        return eye;
    }

    public Repository getRepository() {
        return repository;
    }

    public Alias getAlias() {
        return alias;
    }
}
