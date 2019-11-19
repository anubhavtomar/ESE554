package com.ece.computational.model.assignment.nine;

import java.util.Stack;

/**
 * Problem 11.5
 *
 * @author anubhav tomar (ID: 112268905)
 */
class Node {
    public int iData;
    public double dData;
    public Node leftChild;
    public Node rightChild;

    public void displayNode() {
        System.out.print("{" + iData + ", " + dData + "} ");
    }
}

class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null) ? true : false;
    }

    public Node find(int key) {

        Node current = root;
        while (current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;

        if(root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.iData) {

                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }

                } else {

                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }

                }
            }
        }
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftChild == null && current.rightChild == null) {

            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

        } else if (current.rightChild == null) {

            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

        } else if (current.leftChild == null) {

            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

        } else {

            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            successor.leftChild = current.leftChild;
        }

        return true;
    }

    public Node removeMax() {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;

        if (root == null) {
            return null;
        }

        while (current.rightChild != null) {
            parent = current;
            current = current.rightChild;
        }

        Node result = current;

        if (current.leftChild == null && current.rightChild == null) {

            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

        } else if (current.rightChild == null) {

            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

        } else if (current.leftChild == null) {

            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

        } else {
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }

        return result;
    }

    private Node getSuccessor(Node delNode) {

        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                preOrder(root);
                break;
            case 2:
                inOrder(root);
                break;
            case 3:
                postOrder(root);
                break;
        }

        System.out.println("");
    }

    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    public void displayTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(".......................................................");

        while (isRowEmpty==false) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }

            while (globalStack.isEmpty() == false) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            nBlanks /= 2;

            while(localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println(".......................................................");
    }
}

public class TreeBasedHashTable {

    private Tree[] hashArray;
    private int arraySize;

    public TreeBasedHashTable(int size) {
        arraySize = size;
        hashArray = new Tree[arraySize];
        for (int j = 0; j < arraySize; j++) {
            hashArray[j] = new Tree();
        }
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) {
            System.out.print(j + ". ");
            hashArray[j].traverse(2);
        }
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Node node) {
        int key = node.iData;
        int hashValue = hashFunc(key);
        if (find(key) != null) {
            System.out.println("Value already exists in the hash table.");
            return;
        } else {
            hashArray[hashValue].insert(node.iData, node.dData);
        }
    }

    public void delete(int key) {
        int hashVal = hashFunc(key);
        if (!hashArray[hashVal].isEmpty()) {
            hashArray[hashVal].delete(key);
        }
    }

    public Node find(int key) {
        int hashValue = hashFunc(key);
        if (!hashArray[hashValue].isEmpty()) {
            Node node = hashArray[hashValue].find(key);
            return node;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        Node node;

        TreeBasedHashTable treeBasedHashTable = new TreeBasedHashTable(20);

        node = new Node();
        node.iData = 1;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 2;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 3;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 4;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 5;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 6;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 7;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 8;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 9;
        treeBasedHashTable.insert(node);
        node = new Node();
        node.iData = 10;
        treeBasedHashTable.insert(node);

        treeBasedHashTable.displayTable();

        System.out.println();
        System.out.println("Deleted 2");
        treeBasedHashTable.delete(2);

        treeBasedHashTable.displayTable();
        System.out.println();

        node = treeBasedHashTable.find(3);
        if (node != null) {
            System.out.println("Found " + 3);
        } else {
            System.out.println("Could not find " + 3);
        }

        node = treeBasedHashTable.find(2);
        if (node != null) {
            System.out.println("Found " + 2);
        } else {
            System.out.println("Could not find " + 2);
        }
    }
}
