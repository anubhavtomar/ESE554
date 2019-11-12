package com.ece.computational.model.assignment.seven;

import java.util.Stack;

/**
 * Problem 8.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class PostfixTree {

    private TreeNode root;
    private Stack<TreeNode> charStack;

    public PostfixTree(String postfixString) {
        charStack = new Stack<TreeNode>();

        for (int i = 0; i < postfixString.length(); i++) {
            if (postfixString.charAt(i) == '+'
                    || postfixString.charAt(i) == '-'
                    || postfixString.charAt(i) == '*'
                    || postfixString.charAt(i) == '/') {

                TreeNode operatorTemp = new TreeNode();
                operatorTemp.data = Character.toString(postfixString.charAt(i));
                operatorTemp.rightChild = charStack.pop();
                operatorTemp.leftChild = charStack.pop();
                charStack.push(operatorTemp);
            } else {
                TreeNode operandTemp = new TreeNode();
                operandTemp.data = Character.toString(postfixString.charAt(i));
                charStack.push(operandTemp);
            }
        }

        root = charStack.pop();
    }

    public void traverse(int traverseType)
    {
        switch(traverseType) {
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

    private void preOrder(TreeNode localRoot) {

        if (localRoot != null) {
            System.out.print(localRoot.data + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(TreeNode localRoot) {

        if (localRoot != null) {
            System.out.print("(");
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.data + " ");
            inOrder(localRoot.rightChild);
            System.out.print(")");
        }
    }

    private void postOrder(TreeNode localRoot) {

        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.data + " ");
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

            for(int i = 0; i < numberOfBlanks; i++) {
                System.out.print(" ");
            }

            while (outStack.isEmpty() == false) {
                TreeNode temp = outStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    inStack.push(temp.leftChild);
                    inStack.push(temp.rightChild);

                    if(temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    inStack.push(null);
                    inStack.push(null);
                }
                for(int i = 0; i < numberOfBlanks * 2 - 2; i++) {
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

        PostfixTree theTree = new PostfixTree("MNOPQR+/-*+");

        theTree.displayTree();
        theTree.traverse(1);
        theTree.traverse(2);
        theTree.traverse(3);
    }
}

class TreeNode {

    public String data;
    public TreeNode leftChild;
    public TreeNode rightChild;
}
