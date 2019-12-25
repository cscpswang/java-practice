package com.hz.constantine.algorithm.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: 拓扑排序.
 * @author: xiangji
 * @date: 2019-10-10 10:16
 * @version: V1.0.0
 */
public class TopologicalSort {

    /**
     * 数据结构: DAG,有向无环图
     */
    static class Graph {

        /**
         * 顶点数量
         */
        private int vertexNum;

        /**
         * 邻接表.
         */
        private LinkedList<Integer>[] adjacency;

        /**
         * 逆向邻接表.
         */
        private LinkedList<Integer>[] inverseAdjacency;

        /**
         * 顶点的入度数据结构
         */
        private int[] inDegree;

        public Graph(int vertexNum) {
            this.vertexNum = vertexNum;
            this.adjacency = new LinkedList[vertexNum];
            this.inDegree = new int[vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                adjacency[i] = new LinkedList<>();
                inDegree[i] = 0;
            }
        }

        /**
         * 添加边,同时需要更新入度.
         * 
         * @param from 边的开始
         * @param to 边的结束
         */
        public void addEdge(Integer from, Integer to) {
            adjacency[from].add(to);
            addInDegree(to);
        }

        /**
         * 添加入度
         * 
         * @param vertex 顶点
         */
        public void addInDegree(Integer vertex) {
            inDegree[vertex] = inDegree[vertex] + 1;
        }

        /**
         * 减去入度
         * 
         * @param vertex 顶点
         */
        public void subtract(Integer vertex) {
            adjacency[vertex].forEach(target -> {
                inDegree[target]--;
            });
        }

        /**
         * 初始化逆向邻接表.
         */
        public void initInverseAdjacency(){
            inverseAdjacency = new LinkedList[vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                inverseAdjacency[i] = new LinkedList<>();
            }

            for (int i = 0; i < adjacency.length; i++) {
                for (int i1 = 0; i1 < adjacency[i].size(); i1++) {
                    inverseAdjacency[adjacency[i].get(i1)].add(i);
                }
            }
        }

    }

    /**
     * 用kahn算法实现拓扑排序.
     * 
     * @param vertexNum 顶点数量
     * @param edges 边数量
     */
    public List<Integer> topologicalSortByKahn(int vertexNum, List<Integer[]> edges) {
        List<Integer> result = Lists.newArrayListWithExpectedSize(vertexNum);

        // phase1. 构造DAG 并填充数据.
        Graph graph = new Graph(vertexNum);

        edges.forEach(edge -> {
            graph.addEdge(edge[0], edge[1]);
        });

        // phase2. 拓扑排序.
        while (true) {
            // step1. 找到入度为0的顶点.
            int inDegreeZeroVertex = -1;
            for (int i = 0; i < graph.inDegree.length; i++) {
                if (graph.inDegree[i] == 0) {
                    inDegreeZeroVertex = i;
                    break;
                }
            }
            // 找不到入度为0 的顶点.
            if (-1 == inDegreeZeroVertex) {
                break;
            }

            // step2. 减去入度.
            graph.subtract(inDegreeZeroVertex);

            // step3. 移除该顶点.
            graph.inDegree[inDegreeZeroVertex] = -1;

            result.add(inDegreeZeroVertex);
        }

        return result;
    }

    /**
     * 基于深度优先的拓扑排序.
     * @param vertexNum 顶点数量
     * @param edges 边数量
     */
    public List<Integer> topologicalByDfs(int vertexNum, List<Integer[]> edges){
        List<Integer> result = Lists.newArrayListWithExpectedSize(vertexNum);
        List<Integer> visitedList = Lists.newArrayListWithExpectedSize(vertexNum);

        // phase1. 构造DAG 并填充数据.
        Graph graph = new Graph(vertexNum);

        edges.forEach(edge -> {
            graph.addEdge(edge[0], edge[1]);
        });

        graph.initInverseAdjacency();

        for (int i = 0; i < vertexNum; i++) {
            dfs(graph,i,result,visitedList);
        }

        return result;
    }

    public void dfs(Graph graph,int vertexNum,List<Integer> result,List<Integer> visitedList){
        if(graph.inverseAdjacency[vertexNum] == null || visitedList.contains(vertexNum)){
            return;
        }

        for (int i = 0; i < graph.inverseAdjacency[vertexNum].size(); i++) {
            dfs(graph,graph.inverseAdjacency[vertexNum].get(i),result,visitedList);
        }

        visitedList.add(vertexNum);
        result.add(vertexNum);
    }

    @Test
    public void testTopologicalSortByKahn() {
        TopologicalSort topologicalSort = new TopologicalSort();

        final List<Integer> result = topologicalSort.topologicalSortByKahn(4,
                Lists.newArrayList(new Integer[] { 1, 2 }, new Integer[] { 1, 3 }, new Integer[] { 3, 0 }));

        Assert.assertEquals(result,Lists.newArrayList(1,2,3,0));
    }

    @Test
    public void testTopologicalByDfs(){
        TopologicalSort topologicalSort = new TopologicalSort();

        final List<Integer> result = topologicalSort.topologicalByDfs(4,
                Lists.newArrayList(new Integer[] { 1, 2 }, new Integer[] { 1, 3 }, new Integer[] { 3, 0 }));

        System.out.println(result);
        Assert.assertEquals(result,Lists.newArrayList(1,3,0,2));
    }

}