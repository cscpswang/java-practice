package com.hz.constantine.javatutorial.collection.algorithm;

import java.util.*;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/27
 */
public class Shuffle {

    public static void main(String[] args) {
        shuffleWithRandomObject();
    }

    private static void shuffleWithRandomObject() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        Collections.shuffle(list0, new Random());
        System.out.println(list0);
    }

    private static void shuffle() {
        List<String> list0 = Arrays.asList(new String[] { "1", "2", "3" });
        Collections.shuffle(list0);
        System.out.println(list0);
        for (ListIterator<String> it = list0.listIterator(); it.hasNext();) {
            System.out.println(it.previousIndex());
            if (it.next().equals("2")) {
                System.out.println(it.previousIndex());
                break;
            }
        }
    }
}
