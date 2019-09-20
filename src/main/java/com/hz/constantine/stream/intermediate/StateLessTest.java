package com.hz.constantine.stream.intermediate;

import org.testng.annotations.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2019-06-11 10:01
 * @version: V1.0.0
 */
public class StateLessTest {

    class Obj{
        int i = 1;

        public Obj(int i) {
            this.i = i;
        }

        @Override public String toString() {
            return "Obj{" + "i=" + i + '}';
        }
    }


    @Test
    public void test(){

        Stream.of(new Obj(1),new Obj(2),new Obj(3),new Obj(3)).map(o->{o.i=o.i+1;return o;})
                .filter(o->o.i!=4).forEach(System.out::println);

        IntStream.of(1,2,3,3).distinct().unordered().forEach(System.out::println);

    }

}