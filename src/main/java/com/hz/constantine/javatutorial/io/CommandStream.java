/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.io;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/15 上午9:20
 * @version: V1.0.0
 */
public class CommandStream {

    public void standardStream(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void console(){
        Console console = System.console();
        Objects.requireNonNull(console);

        String userName = console.readLine("please write your account");
        char[] password = console.readPassword("please write your password");
        System.out.println(userName);
        System.out.println(new String(password));
    }
}