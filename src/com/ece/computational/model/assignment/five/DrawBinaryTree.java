package com.ece.computational.model.assignment.five;

import java.util.Scanner;

/**
 * Problem 6.2
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class DrawBinaryTree {

    char[][] arr;
    int lineLength;
    int rows;

    DrawBinaryTree(int lineLength) {

        this.lineLength = lineLength;
        this.rows = (int) (Math.sqrt(lineLength) + 1);
        arr = new char[rows][lineLength];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lineLength; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public void makeBranches(int row, int left, int right) {

        if (row >= rows) {
            return;
        }

        if (left == right) {
            arr[row][left] = 'X';
            return;
        }

        int mid = left + (right - left) / 2;
        arr[row][mid] = 'X';

        makeBranches(row + 1, left, mid);
        makeBranches(row + 1, mid, right);
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lineLength; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter line length");
        int lineLength = s.nextInt();
        DrawBinaryTree drawBinaryTree = new DrawBinaryTree(lineLength);

        drawBinaryTree.makeBranches(0, 0, lineLength);
        System.out.println("Binary Tree:");
        drawBinaryTree.display();
    }
}
