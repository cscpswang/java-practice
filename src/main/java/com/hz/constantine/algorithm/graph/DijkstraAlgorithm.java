/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.graph;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.google.common.collect.Lists;

/**
 * @Description: 狄克斯特拉算法, 算法图解 第7章 第405页
 * @author: xiangji
 * @date: 2018/11/16 10:11 AM
 * @version: V1.0.0
 */
public class DijkstraAlgorithm {

    /**
     * 寻找加权图的最短路径，
     * 
     * @param graph 图模型
     * @param begin 起点
     * @param end 终点
     * @return
     */
    public String[] calculate(Map<String, Map<String, Integer>> graph, String begin, String end) {
        Map<String, Integer> cost = Maps.newHashMap();
        Map<String, String> parentNode = Maps.newHashMap();

        /** begin node **/
        Map<String, Integer> beginGraph = graph.get(begin);
        cost.putAll(beginGraph);
        cost.put(end, Integer.MAX_VALUE);
        for (String key : beginGraph.keySet()) {
            parentNode.put(key, "begin");
        }

        while (cost.keySet().size() > 0) {

            /** 如果终点是最短路径，则表示处理结束 **/
            String costLeastNode = this.getLeastCost(cost);
            if (end.equals(costLeastNode)) {
                break;
            }

            Map<String, Integer> currentNode = graph.get(costLeastNode);
            if (null == currentNode) {
                cost.remove(costLeastNode);
                continue;
            }
            /** 遍历所有邻居, 并更新他们的最短路径 **/
            for (String key : currentNode.keySet()) {
                Integer newCost = cost.get(costLeastNode) + currentNode.get(key);
                if (!cost.containsKey(key)) {
                    cost.put(key, newCost);
                    parentNode.put(key, costLeastNode);
                }
                Integer oldCost = cost.get(key);
                if (newCost < oldCost) {
                    cost.put(key, newCost);
                    parentNode.put(key, costLeastNode);
                }
            }
            cost.remove(costLeastNode);
        }

        List<String> resultList = Lists.newArrayList();
        resultList.add(end);
        String targetNode = end;
        while (null != (targetNode = parentNode.get(targetNode))) {
            resultList.add(targetNode);
        }
        String[] result = new String[resultList.size()];
        for (int i = resultList.size() - 1, j = 0; i >= 0; i--, j++) {
            result[j] = resultList.get(i);
        }
        return result;

    }

    /**
     * 获得成本最小的节点
     * 
     * @param cost
     * @return
     */
    private String getLeastCost(Map<String, Integer> cost) {
        String costLeastNode = null;
        Integer costLeast = Integer.MAX_VALUE;
        for (String key : cost.keySet()) {
            if (cost.get(key) < costLeast) {
                costLeastNode = key;
                costLeast = cost.get(key);
            }
        }
        return costLeastNode;
    }

    @Test
    public void test() {
        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        Map<String, Map<String, Integer>> graph = Maps.newHashMap();
        Map<String, Integer> begin = Maps.newHashMap();
        begin.put("A", 6);
        begin.put("B", 2);
        graph.put("begin", begin);
        Map<String, Integer> A = Maps.newHashMap();
        A.put("end", 1);
        graph.put("A", A);
        Map<String, Integer> B = Maps.newHashMap();
        B.put("A", 3);
        B.put("end", 5);
        graph.put("B", B);

        String[] result = algorithm.calculate(graph, "begin", "end");
        Assert.assertEquals(result, new String[] { "begin", "B", "A", "end" });
    }
}