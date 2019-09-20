package com.hz.constantine.algorithm.stringmatch;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: 字符串查找 Trie Tree 实现
 *               <p>
 *               see https://time.geekbang.org/column/article/72414
 *               </p>
 * @author: xiangji
 * @date: 2019-05-28 22:18
 * @version: V1.0.0
 */
public class TrieTest {

    private TrieTreeNode root = new TrieTreeNode('/');

    /**
     * TrieTree 上添加字符串
     * 
     * @param root 根结点
     * @param str 字符串
     */
    public void insertString(TrieTreeNode root, String str) {

        char[] chars = str.toCharArray();
        TrieTreeNode currentIteratedNode = root;

        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            TrieTreeNode child = currentIteratedNode.children[index];
            if (null != child) {
                currentIteratedNode = child;
                continue;
            }
            TrieTreeNode newChild = new TrieTreeNode(chars[i]);
            currentIteratedNode.children[index] = newChild;
            currentIteratedNode.isLeaf = false;

            currentIteratedNode = newChild;
        }

        currentIteratedNode.isEndOfString = true;
    }

    /**
     * 根据字符串前缀,在Trie Tree上查找匹配的字符串list
     * 
     * @param root 根结点
     * @param prefix 字符串前缀
     * @return 匹配的字符串list
     */
    public List<String> searchAllMatchPrefix(TrieTreeNode root, String prefix) {
        char[] chars = prefix.toCharArray();
        TrieTreeNode currentIteratedNode = root;

        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            TrieTreeNode child = currentIteratedNode.children[index];
            if (null != child) {
                currentIteratedNode = child;
                continue;
            }
            return Collections.emptyList();
        }
        return recursiveSearch(prefix, currentIteratedNode);

    }

    /**
     * 递归遍历所有的字符串.
     * <p>
     * 递归函数 f(node) = ∑ f(child). 退出条件: 遍历到叶子节点.
     * </p>
     * 
     * @param prefix 字符前缀
     * @param trieTreeNode 当前树节点
     * @return 遍历的字符串
     */
    private List<String> recursiveSearch(String prefix, TrieTreeNode trieTreeNode) {
        List<String> result = Lists.newArrayList();
        if (trieTreeNode.isEndOfString) {
            result.add(prefix);
        }
        // 退出条件: 当前节点是叶子节点
        if (trieTreeNode.isLeaf) {
            return result;
        }
        TrieTreeNode[] children = trieTreeNode.children;

        for (TrieTreeNode node : children) {
            if (null == node) {
                continue;
            }
            if (node.isEndOfString) {
                result.add(prefix + node.data);
            }
            // 退出条件: 当前节点是叶子节点
            if (node.isLeaf) {
                continue;
            } else {
                result.addAll(recursiveSearch(prefix + node.data, node));
            }
        }
        return result;
    }

    /**
     * 搜索所有命中的敏感词
     * 
     * @param root 敏感词组成的Trie 树
     * @param mainStr 主串
     * @return 命中的敏感词
     */
    private List<String> searchAllHitSensitive(TrieTreeNode root, String mainStr) {
        List<String> result = Lists.newArrayList();
        char[] chars = mainStr.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            TrieTreeNode currentNode = root.children[currentChar - 'a'];
            if (null == currentNode) {
                continue;
            }

            for (int j = i; j < chars.length; j++) {
                if (null == currentNode) {
                    break;
                }
                if (0 != j && currentNode.isEndOfString) {
                    result.add(String.valueOf(chars, i, j - i+1));
                }
                if (j + 1 < chars.length) {
                    currentChar = chars[j + 1];
                    currentNode = currentNode.children[currentChar - 'a'];
                }
            }

        }
        return result;
    }

    class TrieTreeNode {
        char data;

        TrieTreeNode[] children;

        boolean isEndOfString;

        boolean isLeaf;

        public TrieTreeNode(char data) {
            this.data = data;
            this.children = new TrieTreeNode[26];
            this.isEndOfString = false;
            this.isLeaf = true;
        }
    }

    @Test
    public void test() {
        TrieTest trieTest = new TrieTest();
        trieTest.insertString(root, "her");
        trieTest.insertString(root, "he");
        trieTest.insertString(root, "hi");
        trieTest.insertString(root, "hello");
        trieTest.insertString(root, "hit");
        trieTest.insertString(root, "st");
        trieTest.insertString(root, "swr");

        Assert.assertEqualsNoOrder(trieTest.searchAllMatchPrefix(root, "he").toArray(),
                new String[] { "her", "he", "hello" });
    }

    @Test
    public void testSensitiveWord() {
        TrieTest trieTest = new TrieTest();
        trieTest.insertString(root, "her");
        trieTest.insertString(root, "he");
        trieTest.insertString(root, "hi");
        trieTest.insertString(root, "hello");

        Assert.assertEqualsNoOrder(trieTest.searchAllHitSensitive(root, "ihello").toArray(),
                new String[] { "he", "hello" });
    }

}