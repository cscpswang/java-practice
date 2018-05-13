/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.javatutorial.io;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/1/25 下午12:56
 * @version: V1.0.0
 */
public class ObjectStreamTest {

    static final String dataFile = "invoicedata";


    static final int[] units = { 12, 8, 13, 29, 50 };

    static final String[] descs = { "Java T-shirt", "Java Mug", "Duke Juggling Dolls", "Java Pin", "Java Key Chain" };

    @Test
    public void ObjectInputStream() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = null;
        Calendar calendar = Calendar.getInstance();

        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

            out.writeObject(calendar);
            out.writeObject(calendar);

            for (int i = 0; i < units.length; i++) {
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        } finally {
            out.close();
        }

        ObjectInputStream in = null;
        double total = 0.0;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

            int unit;
            String desc;

            try {
                Calendar calendar1 = (Calendar) in.readObject();
                Calendar calendar2 = (Calendar) in.readObject();

                System.out.println(calendar1);

                Assert.assertEquals(calendar1,calendar2);
                while (true) {
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("You ordered %d units of %s \n", unit, desc);
                    total += unit;
                }
            } catch (EOFException e) {
            }
            System.out.format("For a TOTAL of: $%.2f%n", total);
        } finally {
            in.close();
        }

        // rollback
        deleteFile();
    }

    private void deleteFile() {
        File file = new File(dataFile);
        file.deleteOnExit();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setLenient(false);
        System.out.println(simpleDateFormat.parse("2017-03-01 11:22:33"));

    }

}