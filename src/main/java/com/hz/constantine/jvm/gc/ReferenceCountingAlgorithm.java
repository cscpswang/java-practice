package com.hz.constantine.jvm.gc;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/30
 */
public class ReferenceCountingAlgorithm {

    private static int _1MB = 1024 * 1024;

    private byte[] size = new byte[_1MB * 10];

    private ReferenceCountingAlgorithm obj = null;

    public static void main(String[] args) {
        ReferenceCountingAlgorithm a = new ReferenceCountingAlgorithm();
        ReferenceCountingAlgorithm b = new ReferenceCountingAlgorithm();
        a.obj = b;
        b.obj = a;
        a = null;
        b = null;
        System.gc();
    }
}
