/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.io;

import org.apache.tools.ant.util.ClasspathUtils;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * @Description: 通过token/translating individual break 格式化的数据
 * @author: xiangji
 * @date: 2018/1/11 上午9:33
 * @version: V1.0.0
 */
public class ScannerTest {

    @Test
    public void token() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xanadu.txt");
        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(inputStream)))) {
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void translatingIndividualToken() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("usnumbers.txt");
        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(inputStream)))) {
            s.useLocale(Locale.US);

            double sum = 0;
            while (s.hasNext()) {
                sum += s.nextDouble();
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}