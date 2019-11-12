package com.ece.computational.model.assignment.six;

/**
 * Problem 7.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class SelectK {

    private long[] arr;
    private int numberOfElements;

    public SelectK(int max) {

        arr = new long[max];
        numberOfElements = 0;
    }

    public int size() {
        return numberOfElements;
    }

    public void insert(long value) {
        arr[numberOfElements] = value;
        numberOfElements++;
    }

    public void display() {
        for (int j = 0; j < numberOfElements; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public int partitionIt(int left, int right) {
        int leftPtr = left - 1;
        int rightPtr = right;

        if (rightPtr - leftPtr <= 0) {
            return -1;
        }

        long pivot = arr[right];
        while(true) {

            while (leftPtr < right && arr[++leftPtr] < pivot);
            while (rightPtr > left && arr[--rightPtr] > pivot);

            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }

        swap(leftPtr, right);
        return leftPtr;
    }

    public long findIndex(int left, int right, int index) {

        if (right-left <= 0) {
            return arr[index];
        } else {
            int partition = partitionIt(left, right);

            if(partition == index) {
                return arr[index];
            } else if (partition < index) {
                return findIndex(partition + 1, right, index);
            } else {
                return findIndex(left, partition - 1, index);
            }
        }
    }

    public static void main(String[] args) {

        SelectK selectK = new SelectK(51);

        for (int i = 0; i < 51; i++) {
            selectK.insert((long)(Math.random() * 100));
        }

        selectK.display();

        long selection = selectK.findIndex(0, selectK.size() - 1, 30);

        System.out.println("kth largest element from the array: " + selection);
    }

    private void swap(int firstIndex, int secondIndex) {
        long temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
