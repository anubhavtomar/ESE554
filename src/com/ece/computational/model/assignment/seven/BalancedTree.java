package com.ece.computational.model.assignment.seven;

import java.util.Stack;

/**
 * Problem 8.2
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class BalancedTree {

    private TreeNode root;

    public BalancedTree(String initString) {

        root = null;
        int numberOfElements = 0;

        TreeNode[] nodeArray = new TreeNode[initString.length()];
        for(int i = 0; i < initString.length(); i++) {
            nodeArray[i] = new TreeNode();
            nodeArray[i].data = String.valueOf(initString.charAt(i));
            numberOfElements++;
        }

        while (numberOfElements > 1) {
            int fillCounter = 0;
            for (int i = 0; i < numberOfElements; i++) {
                if (i % 2 == 1) {
                    TreeNode tempNode = new TreeNode();
                    tempNode.data = "+";
                    tempNode.leftChild = nodeArray[i - 1];
                    tempNode.rightChild = nodeArray[i];
                    nodeArray[fillCounter] = tempNode;
                    fillCounter++;
                }

                if (i % 2 == 0 && i == numberOfElements - 1) {
                    nodeArray[fillCounter] = nodeArray[i];
                }
            }
            numberOfElements -= fillCounter;
        }
        root = nodeArray[0];
    }

    public void traverse(int traverseType) {

        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    private void preOrder(TreeNode root) {

        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    private void inOrder(TreeNode root) {

        if (root != null) {
            inOrder(root.leftChild);
            System.out.print(root.data + " ");
            inOrder(root.rightChild);
        }
    }

    private void postOrder(TreeNode root) {

        if (root != null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            System.out.print(root.data + " ");
        }
    }

    public void displayTree() {

        Stack<TreeNode> outStack = new Stack<TreeNode>();
        outStack.push(root);
        int numberOfBlanks = 32;
        boolean isRowEmpty = false;

        while (isRowEmpty==false) {
            Stack<TreeNode> inStack = new Stack<TreeNode>();
            isRowEmpty = true;

            for(int j = 0; j < numberOfBlanks; j++) {
                System.out.print(" ");
            }

            while (outStack.isEmpty() == false) {
                TreeNode temp = outStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    inStack.push(temp.leftChild);
                    inStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    inStack.push(null);
                    inStack.push(null);
                }

                for(int j = 0; j < numberOfBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            numberOfBlanks /= 2;
            while (inStack.isEmpty() == false) {
                outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) {

        BalancedTree theTree = new BalancedTree("ABCDEFGHIJKLMNOP");

        theTree.displayTree();
        theTree.traverse(1);
        theTree.traverse(2);
        theTree.traverse(3);
    }
}
