package com.hz.constantine.algorithm.backtracking;

import org.testng.annotations.Test;

/**
 * Copyright (c) 2014-2019, NetEase, Inc. All Rights Reserved.
 *
 * @Description: 解决八皇后问题 see: https://time.geekbang.org/column/article/74287(极客时间)
 *               <p>
 *               </p>
 *               重要变量说明:
 *               <ul>
 *               <li>existsPiece: 已存在的棋子, 第一维度是行,第二维度是列. 值为1: 标示已存在</li>
 *               <li>rowIndex: 当前行的索引, start at 0</li>
 *               <li>columnIndex: 当前列的索引, start at 0</li>
 *               <li>rowNum: 行数量</li>
 *               <li>columnNum: 列数量</li>
 *               </ul>
 * @author: xiangji
 * @date: 2019-07-10 09:38
 * @version: V1.0.0
 */
public class EightQueens {

    int rowNum = 8;

    int columnNum = 8;

    private void recursive(int[] existsPiece, int rowIndex) {

        for (int i = 0; i < columnNum; i++) {
            final boolean isValid = isValid(existsPiece, rowIndex, i);
            boolean isEnd = rowIndex >= 8 - 1;
            if (isValid) {
                existsPiece[rowIndex] = i;
                if (!isEnd) {
                    recursive(existsPiece, rowIndex + 1);
                } else {
                    print(existsPiece);
                    existsPiece = new int[8];
                }
            }
        }

    }

    private boolean isValid(int[] existsPiece, int rowIndex, int columnIndex) {

        // 判断行中是否有棋子.
        boolean isExistsAtRow = existsPiece[rowIndex] == columnIndex;
        if (isExistsAtRow) {
            return false;
        }

        // 判断列和左右对角线是否有棋子.
        int leftDiagonal = columnIndex;
        int rightDiagonal = columnIndex;
        for (int i = rowIndex - 1; i >= 0; i--) {
            leftDiagonal--;
            rightDiagonal++;
            if (leftDiagonal >= 0 && existsPiece[i] == leftDiagonal) {
                return false;
            }
            if (rightDiagonal < columnNum && existsPiece[i] == rightDiagonal) {
                return false;
            }
            if (existsPiece[i] == columnIndex) {
                return false;
            }
        }

        return true;
    }

    private void print(int[] pieces) {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (pieces[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void test() {

        EightQueens eightQueens = new EightQueens();
        eightQueens.recursive(new int[8], 0);
    }

}