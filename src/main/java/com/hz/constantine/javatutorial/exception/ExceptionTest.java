/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.exception;

import org.testng.annotations.Test;

import javax.management.RuntimeErrorException;
import java.io.IOException;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/8 上午9:24
 * @version: V1.0.0
 */
public class ExceptionTest {


    @Test
    public void finalException(){

        try{
            IOException e = new IOException();
            throw e;
        }catch (IOException|RuntimeException ex){
            ex.printStackTrace();
            //compile error , because ex is final
            //ex = new IndexOutOfBoundsException();
        }
    }

    @Test
    public void tryresource(){
        //TODO
    }


}