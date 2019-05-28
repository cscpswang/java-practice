/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.datastructure.linkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description:
 * 
 *               <pre>
 *                  see 极客时间:数据结构与算法之美:https://time.geekbang.org/column/article/41149
 *               </pre>
 * 
 * @author: xiangji
 * @date: 2019-01-29 09:36
 * @version: V1.0.0
 */
public class LinkedList {

    /**
     * 反转node
     * 
     * @param header 头结点
     * @return
     */
    public Node flip(Node header) {
        if (null == header) {
            return null;
        }

        Node tail = header;
        while (null != tail.next) {
            tail = tail.next;
        }

        Node concreteNode = tail;
        while (true) {
            Node preNode = getPreNode(header, concreteNode);
            // 迭代到首节点.
            if (null == preNode) {
                concreteNode.next = null;
                break;
            }
            concreteNode.next = preNode;
            concreteNode = preNode;
        }

        return tail;
    }

    private Node getPreNode(Node header, Node concreteNode) {
        if (header == concreteNode) {
            return null;
        }

        Node preNode = header;
        while (null != preNode.next) {
            if (preNode.next == concreteNode) {
                break;
            }
            preNode.next = preNode.next.next;
        }
        return preNode;
    }

    private static class Node {
        private Node next;

        private String data;
    }

    @Test
    public void flip_null() {
        LinkedList linkedList = new LinkedList();
        Node node = null;
        Assert.assertNull(linkedList.flip(node));
    }

    @Test
    public void flip_oneNode(){
        LinkedList linkedList = new LinkedList();
        Node node = new Node();
        node.data = "header";
        Assert.assertEquals(linkedList.flip(node).data, "header");
    }

    @Test
    public void flip_twoNode(){
        LinkedList linkedList = new LinkedList();
        Node node = new Node();
        node.data = "header";
        Node node2 = new Node();
        node2.data = "node2";
        node.next = node2;

        Node header = linkedList.flip(node);
        Assert.assertEquals(header.data, "node2");
        Assert.assertEquals(header.next.data, "header");
        Assert.assertNull(header.next.next);
    }
}