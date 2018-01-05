/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.exception;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.collections.functors.IdentityPredicate;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2017/12/8 上午9:24
 * @version: V1.0.0
 */
public class ExceptionTest {

    public final class FileSearcher{

        public void search()throws IllegalAccessException{
            throw new IllegalAccessException("file not found");
        }

        public void action()throws Exception{
            try{
                search();
            }catch (IllegalAccessException e){
                throw new Exception(e);
            }
        }
    }


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
    public void chainedException(){
        FileSearcher searcher = new FileSearcher();
        try {
            searcher.action();
        }catch (Exception e){
            org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error("",e);
        }
    }

    @Test
    public void cat() {
        RandomAccessFile input = null;
        String line = null;

        try {
            File file = File.createTempFile("tmp", "tmp.txt");
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                }catch (IOException e){

                }
            }
        }
    }

}