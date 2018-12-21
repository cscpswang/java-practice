/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.graph;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;
import org.testng.collections.Sets;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/11/16 10:12 AM
 * @version: V1.0.0
 */
public class BreadthFirstAlgorithm {
    public String search(String base, Map<String, String[]> graph) {
        Queue<String> queue = new LinkedList();
        Set<String> processed = Sets.newHashSet();

        String[] targets = graph.get(base);
        for (String relation : targets) {
            queue.offer(relation);
        }
        while (queue.size() > 0) {

            String name = queue.poll();
            if (isRightOne(name)) {
                return name;
            }
            if (processed.contains(name)) {
                continue;
            }
            processed.add(name);
            String[] relations = graph.get(name);
            if (null == relations || relations.length <= 0) {
                continue;
            }
            for (String relation : relations) {
                queue.offer(relation);
            }
        }

        return null;
    }

    /**
     * 是否是正确的
     *
     * @param name
     * @return
     */
    private boolean isRightOne(String name) {
        return name.endsWith("m");
    }

    @Test
    public void test() {
        Map<String, String[]> graph = Maps.newHashMap();
        graph.put("xiangji", new String[] { "bob", "alice", "claire" });
        graph.put("bob", new String[] { "anuj", "peggy" });
        graph.put("claire", new String[] { "jonny", "thom" });
        graph.put("alice", new String[] { "peggy" });
        graph.put("anuj", new String[] {});
        graph.put("peggy", new String[] {});
        graph.put("thom", new String[] {});
        graph.put("jonny", new String[] {});

        BreadthFirstAlgorithm algorithm = new BreadthFirstAlgorithm();
        Assert.assertEquals(algorithm.search("xiangji", graph), "thom");
    }
}