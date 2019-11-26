package com.ece.computational.model.assignment.ten;

import java.util.Stack;

/**
 * Problem 12.5
 *
 * @author anubhav tomar (ID: 112268905)
 */
class TreeHeapNode {

    public int iData;
    public TreeHeapNode leftChild;
    public TreeHeapNode rightChild;
    public TreeHeapNode parent;
    boolean isLeftChild = false;

    public int getKey() {
        return iData;
    }

    public void setKey(int id) {
        iData = id;
    }

    public void display() {
        System.out.print(getKey() + " ");
    }
}

public class TreeHeap {

    private TreeHeapNode root;
    private int numNodes;

    public TreeHeap() {
        numNodes = 0;
        root = new TreeHeapNode();
    }

    public boolean isEmpty() {
        return numNodes == 0;
    }

    public void insert(int key) {

        TreeHeapNode newNode = new TreeHeapNode();
        newNode.setKey(key);

        if (numNodes == 0) {
            root = newNode;
        } else {
            TreeHeapNode current = root;
            int n = numNodes+1;
            int k;
            int[] path = new int[n];
            int j = 0;
            while (n >= 1) {
                path[j] = n % 2;
                n /= 2;
                j++;
            }

            for (k = j - 2; k > 0; k--) {
                if (path[k] == 1) {
                    current = current.rightChild;
                } else {
                    current = current.leftChild;
                }
            }

            if (current.leftChild != null) {
                current.rightChild = newNode;
                newNode.isLeftChild = false;
            } else {
                current.leftChild = newNode;
                newNode.isLeftChild = true;
            }

            newNode.parent = current;

            trickleUp(newNode);
        }
        numNodes++;
    }

    public void trickleUp(TreeHeapNode newNode) {

        int bottom = newNode.getKey();
        TreeHeapNode current = newNode;

        while (current.parent != null && current.parent.getKey() < bottom) {
            current.setKey(current.parent.getKey());
            current = current.parent;
        }

        current.setKey(bottom);
    }

    public TreeHeapNode remove() {

        TreeHeapNode removedNode = root;
        if (numNodes==0) {
            return null;
        }

        if (numNodes==1) {
            root = null;
            numNodes--;
            return removedNode;
        }

        TreeHeapNode current = root;
        int n = numNodes;
        int k;
        int[] path = new int[n];
        int j = 0;

        while (n >= 1) {
            path[j] = n % 2;
            n /= 2;
            j++;
        }

        for (k = j-2; k >= 0; k--) {
            if (path[k] == 1) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
        }

        root.setKey(current.getKey());

        if (current.isLeftChild) {
            current.parent.leftChild = null;
        } else {
            current.parent.rightChild = null;
        }

        trickleDown(root);
        numNodes--;
        return removedNode;
    }

    public void trickleDown(TreeHeapNode newNode) {

        TreeHeapNode current = newNode;
        int top = newNode.getKey();
        TreeHeapNode largerChild;
        while (current.leftChild != null || current.rightChild != null) {

            if (current.rightChild != null && current.leftChild.getKey() < current.rightChild.getKey()) {
                largerChild = current.rightChild;
            } else {
                largerChild = current.leftChild;
            }

            if (top >= largerChild.getKey()) {
                break;
            }

            current.setKey(largerChild.getKey());
            current = largerChild;
        }

        current.setKey(top);
    }

    public boolean change(int index, int newValue) {

        if (index < 0 || index > numNodes) {
            return false;
        }

        TreeHeapNode current = root;
        int n = index;
        int k;
        int[] path = new int[n];
        int j = 0;

        while (n >= 1) {
            path[j] = n % 2;
            n /= 2;
            j++;
        }

        for (k = j - 2; k >= 0; k--) {
            if (path[k] == 1) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
        }

        int oldValue = current.getKey();
        current.setKey(newValue);

        if (oldValue < newValue) {
            trickleUp(current);
        } else {
            trickleDown(current);
        }

        return true;
    }

    public void display() {

        Stack<TreeHeapNode> globalStack = new Stack<TreeHeapNode>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".......................................................");

        while (isRowEmpty==false) {
            Stack<TreeHeapNode> localStack = new Stack<TreeHeapNode>();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }

            while (globalStack.isEmpty() == false) {
                TreeHeapNode temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int j = 0; j < nBlanks*2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();
            nBlanks /= 2;

            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println(".......................................................");
    }

    public static void main(String[] args) {

        TreeHeap treeHeap = new TreeHeap();
        boolean success;

        treeHeap.insert(10);
        treeHeap.insert(90);
        treeHeap.insert(20);
        treeHeap.insert(70);
        treeHeap.insert(40);
        treeHeap.insert(50);
        treeHeap.insert(60);
        treeHeap.insert(100);
        treeHeap.insert(80);
        treeHeap.insert(30);

        treeHeap.display();

        treeHeap.insert(110);

        if(!treeHeap.isEmpty()) {
            treeHeap.remove();
            treeHeap.display();
        } else {
            System.out.println("Can't remove; heap empty");
        }

        success = treeHeap.change(10, 150);
        if (!success) {
            System.out.println("Invalid index\n");
        } else {
            treeHeap.display();
        }
    }
}
