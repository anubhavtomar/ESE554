package com.ece.computational.model.assignment.eight;

import java.util.Stack;

class Node {

    public boolean isRed = true;
    public boolean isBlack = false;

    public int iData;
    public double dData;
    public Node leftChild;
    public Node rightChild;

    public void displayNode() {
        System.out.print("{");
        System.out.print(iData);
        System.out.print(", ");
        System.out.print(dData);
        System.out.print("} ");
    }

    public void setColorBlack() {
        isBlack = true;
        isRed = false;
    }

    public void setColorRed() {
        isRed = true;
        isBlack = false;
    }
}

class Tree {

    public Node root;

    public Tree() {
        root = null;
    }

    public Node find(int key) {

        Node current = root;
        while(current.iData != key) {
            if(key < current.iData)
                current = current.leftChild;
            else
                current = current.rightChild;
            if(current == null)
                return null;
        }
        return current;
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;

        if(root==null) {
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

        } else if (current.leftChild==null) {

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
        switch(traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
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
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j=0; j<nBlanks; j++) {
                System.out.print(' ');
            }

            while (globalStack.isEmpty()==false) {
                Node temp = (Node)globalStack.pop();
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

                for (int j=0; j<nBlanks*2-2; j++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty()==false) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("......................................................");
    }
}

public class RedBlackTree extends Tree {

    public RedBlackTree() {
        super();
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;

        if (root == null) {
            root = newNode;
        }

        Node node = root;
        while (true) {
            if (id == node.iData) {
                return;
            } else if (id < node.iData) {
                if (node.leftChild == null) {
                    node.leftChild = newNode;
                    adjustAfterInsertion(node.leftChild);
                    break;
                }
                node = node.leftChild;
            } else {
                if (node.rightChild == null) {
                    node.rightChild = newNode;
                    adjustAfterInsertion(node.rightChild);
                    break;
                }
                node = node.rightChild;
            }
        }
    }

    public void rotateLeft(Node node) {
        if (node.rightChild == null) {
            return;
        }

        Node oldRight = node.rightChild;
        node.rightChild = oldRight.leftChild;

        if (parentOf(node) == null) {
            root = oldRight;
        } else if (parentOf(node).leftChild == node) {
            parentOf(node).leftChild = oldRight;
        } else {
            parentOf(node).rightChild = oldRight;
        }
        oldRight.leftChild = node;
    }

    public void rotateRight(Node node) {

        if (node.leftChild == null) {
            return;
        }

        Node oldLeft = node.leftChild;
        node.leftChild = oldLeft.rightChild;

        if (parentOf(node) == null) {
            root = oldLeft;
        } else if (parentOf(node).leftChild == node) {
            parentOf(node).rightChild = oldLeft;
        } else {
            parentOf(node).rightChild = oldLeft;
        }

        oldLeft.rightChild = node;
    }

    private void adjustAfterInsertion(Node node) {

        node.setColorRed();

        if (node != null && node != root && parentOf(node).isRed) {

            if (siblingOf(parentOf(node)) != null && siblingOf(parentOf(node)).isRed) {

                parentOf(node).setColorBlack();
                siblingOf(parentOf(node)).setColorBlack();
                grandparentOf(node).setColorRed();
                adjustAfterInsertion(grandparentOf(node));

            } else if (parentOf(node) == grandparentOf(node).leftChild) {

                if (node == parentOf(node).rightChild) {
                    rotateLeft(node = parentOf(node));
                }

                parentOf(node).setColorBlack();
                grandparentOf(node).setColorRed();
                rotateRight(grandparentOf(node));

            } else if (parentOf(node) == grandparentOf(node).rightChild) {

                if (node == parentOf(node).leftChild) {
                    rotateRight(node = parentOf(node));
                }

                parentOf(node).setColorBlack();
                grandparentOf(node).setColorRed();
                rotateLeft(grandparentOf(node));

            }
        }

        root.setColorBlack();
    }

    public static void main(String[] args) {

        RedBlackTree redBlackTree = new RedBlackTree();

        redBlackTree.insert(50, 1.5);
        redBlackTree.insert(25, 1.2);
        redBlackTree.insert(75, 1.7);
        redBlackTree.insert(12, 1.5);
        redBlackTree.insert(37, 1.2);
        redBlackTree.insert(43, 1.7);
        redBlackTree.insert(30, 1.5);
        redBlackTree.insert(33, 1.2);
        redBlackTree.insert(87, 1.7);
        redBlackTree.insert(93, 1.5);
        redBlackTree.insert(97, 1.5);

        redBlackTree.displayTree();

        redBlackTree.traverse(1);
        redBlackTree.traverse(2);
        redBlackTree.traverse(3);

        Node found = redBlackTree.find(25);

        if(found != null) {
            System.out.print("Found: ");
            found.displayNode();
            System.out.print("\n");
        } else {
            System.out.print("Could not find ");
            System.out.print(25 + '\n');
        }

        found = redBlackTree.find(19);

        if(found != null) {
            System.out.print("Found: ");
            found.displayNode();
            System.out.print("\n");
        } else {
            System.out.println("Could not find 19");
        }

        boolean didDelete = redBlackTree.delete(25);

        if(didDelete) {
            System.out.print("Deleted " + 25 + '\n');
        } else {
            System.out.println("Could not delete 25");
        }

        didDelete = redBlackTree.delete(25);

        if(didDelete) {
            System.out.print("Deleted " + 25 + '\n');
        } else {
            System.out.println("Could not delete 25");
        }

        redBlackTree.traverse(1);
        redBlackTree.traverse(2);
        redBlackTree.traverse(3);
    }

    private Node parentOf(Node node) {

        Node current = root;
        Node parent = root;

        while (current.iData != node.iData) {
            parent = current;
            if (node.iData < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        return parent;
    }

    private Node siblingOf(Node node) {
        return (node == null || parentOf(node) == null) ? null
                : (node == parentOf(node).leftChild) ? parentOf(node).rightChild
                : parentOf(node).leftChild;
    }

    private Node grandparentOf(Node node) {
        return (node == null || parentOf(node) == null) ? null : parentOf(parentOf(node));
    }
}
