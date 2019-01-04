/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.dynamicplan;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 
 *               <pre>
 *               用维特比算法解决篱笆网络Lattice的最短路径问题
 *               </pre>
 * 
 * @see https://blog.csdn.net/athemeroy/article/details/
 *      79339546#%E7%AF%B1%E7%AC%86%E7%BD%91%E7%BB%9Clattice%E7%9A%84%E6%9C%80%E7%9F%AD%E8%B7%AF%E5%BE%84%E9%97%AE%E9%A2%98
 * @author: xiangji
 * @date: 2019/1/3 9:35 AM
 * @version: V1.0.0
 */
public class Viterbi {

    private String[] shortestPath(Map<String, Object[][]>[] relations, String endNode) {
        String[] result = new String[relations.length + 1];
        // 首先取到起点
        String startNode = (String) relations[0].keySet().toArray()[0];
        result[0] = startNode;

        // 存储到达指定节点的最短路径,最短距离
        Map<String, String> nodeShortestPath = Maps.newHashMap();
        nodeShortestPath.put(startNode, startNode);
        Map<String, Integer> nodeShortestDistance = Maps.newHashMap();
        nodeShortestDistance.put(startNode, 0);

        for (Map<String, Object[][]> relation : relations) {
            // 当期这一层，得到该层所有节点的最短距离 和 到达该距离的父节点
            Map<String, Integer> currentShortestDistance = Maps.newHashMap();
            Map<String, String> currentShortestPath = Maps.newHashMap();
            for (Map.Entry<String, Object[][]> entry : relation.entrySet()) {
                String parentNode = entry.getKey();
                Object[][] nodes = entry.getValue();
                for (Object[] node : nodes) {
                    String targetNode = (String) node[0];
                    Integer targetNodeDistance = (Integer) node[1];
                    Integer targetNodeTotalDistance = nodeShortestDistance.get(parentNode) + targetNodeDistance;

                    // 取到达当前节点的最短路径.
                    Integer existsTargetNodeTotalDistance = currentShortestDistance.get(targetNode);
                    if (null == existsTargetNodeTotalDistance
                            || targetNodeTotalDistance < existsTargetNodeTotalDistance) {
                        currentShortestDistance.put(targetNode, targetNodeTotalDistance);
                        currentShortestPath.put(targetNode, parentNode);
                    }
                }
            }
            nodeShortestPath.putAll(currentShortestPath);
            nodeShortestDistance.putAll(currentShortestDistance);
        }

        int index = relations.length;
        result[index] = endNode;
        String node = endNode;
        for (int i = index - 1; i >=0 ; i--) {
            node = nodeShortestPath.get(node);
            result[i] = node;
        }
        return result;
    }

    @Test
    public void test() {
        Map<String, Object[][]>[] relations = new HashMap[3];
        Map<String, Object[][]> firstLevel = Maps.newHashMap();
        firstLevel.put("A", new Object[][] { { "B1", 6 }, { "B2", 7 }, { "B3", 5 } });
        relations[0] = firstLevel;

        Map<String, Object[][]> secondLevel = Maps.newHashMap();
        secondLevel.put("B1", new Object[][] { { "C1", 5 }, { "C2", 6 }, { "C3", 9 } });
        secondLevel.put("B2", new Object[][] { { "C1", 4 }, { "C2", 3 }, { "C3", 7 } });
        secondLevel.put("B3", new Object[][] { { "C1", 4 }, { "C2", 6 }, { "C3", 6 } });
        relations[1] = secondLevel;

        Map<String, Object[][]> thirdLevel = Maps.newHashMap();
        thirdLevel.put("C1", new Object[][] { { "D1", 7 } });
        thirdLevel.put("C2", new Object[][] { { "D1", 5 } });
        thirdLevel.put("C3", new Object[][] { { "D1", 5 } });
        relations[2] = thirdLevel;

        Viterbi viterbi = new Viterbi();
        Assert.assertEquals(viterbi.shortestPath(relations, "D1"), new String[] { "A", "B2", "C2", "D1" });
    }
}