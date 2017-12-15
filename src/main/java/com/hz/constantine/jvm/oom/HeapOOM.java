package com.hz.constantine.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/26
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            list.add("aaaaaaaaaaaassssssssssssddddddddddfffffffbbbbbbbbbbbbbbbbb");
        }
    }

}
