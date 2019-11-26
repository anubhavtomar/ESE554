package com.ece.computational.model.assignment.ten;

/**
 * Problem 12.2
 *
 * @author anubhav tomar (ID: 112268905)
 */
class HeapNode {

    private int iData;

    public HeapNode(int key) {
        iData = key;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int id) {
        iData = id;
    }
}

public class Heap {

    private HeapNode[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int maxSize) {

        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new HeapNode[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void toss(int key) {
        HeapNode newNode = new HeapNode(key);
        heapArray[currentSize] = newNode;
        currentSize++;
    }

    public void restoreHeap() {
        for(int j = (currentSize / 2) - 1; j >= 0; j--) {
            trickleDown(j);
        }
    }

    public boolean insert(int key) {

        if(currentSize == maxSize) {
            return false;
        }

        HeapNode newNode = new HeapNode(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {

        int parent = (index - 1) / 2;
        HeapNode bottom = heapArray[index];

        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent-1)/2;
        }

        heapArray[index] = bottom;
    }

    public HeapNode remove() {

        HeapNode root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {

        int smallerChild;
        HeapNode top = heapArray[index];

        while (index < currentSize/2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild+1;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                smallerChild = rightChild;
            } else {
                smallerChild = leftChild;
            }
            if (top.getKey() <= heapArray[smallerChild].getKey()) {
                break;
            }

            heapArray[index] = heapArray[smallerChild];
            index = smallerChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }

        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);

        if (oldValue > newValue) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");
        for(int m = 0; m < currentSize; m++) {
            if (heapArray != null) {
                System.out.print(heapArray[m].getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }

        System.out.println();

        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "............................";
        System.out.println(dots+dots);

        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(' ');
                }
            }

            System.out.print(heapArray[j].getKey());

            if (++j == currentSize) {
                break;
            }

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n"+dots+dots);
    }

    public static void main(String[] args) {

        Heap heap = new Heap(11);

        heap.insert(10);
        heap.insert(90);
        heap.insert(20);
        heap.insert(70);
        heap.insert(40);
        heap.insert(50);
        heap.insert(60);
        heap.insert(100);
        heap.insert(80);
        heap.insert(30);

        heap.displayHeap();

        boolean success = heap.insert(110);

        if (!success) {
            System.out.println("Can't insert; heap full");
        } else {
            heap.displayHeap();
        }

        success = heap.insert(120);

        if (!success) {
            System.out.println("Can't insert; heap full");
        } else {
            heap.displayHeap();
        }

        if (!heap.isEmpty()) {
            heap.remove();
        } else {
            System.out.println("Can't remove; heap empty");
        }

        success = heap.change(110, 150);
        if (!success) {
            System.out.println("Invalid index\n");
        } else {
            heap.displayHeap();
        }
    }
}
