package com.hz.constantine.javatutorial.collection;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/10/11
 */
public class FindDups {

    public static void main(String[] args) {
        String[] args1 = new String[] { "a", "A", "dd", "f", "a" };
        SortedSet<String> sets = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        for (String arg : args1) {
            sets.add(arg);
        }
        System.out.printf("size: %d, %s", sets.size(), sets);

    }
}

