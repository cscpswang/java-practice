package com.hz.constantine.jvm.oom;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/28
 */
public class StackOverflow {

    private void overflow() {
        overflow();
    }

    public static void main(String[] args) {
        new StackOverflow().overflow();
    }
}
