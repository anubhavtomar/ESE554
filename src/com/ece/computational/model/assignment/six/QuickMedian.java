package com.ece.computational.model.assignment.six;

/**
 * Problem 7.3
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class QuickMedian {

    private long[] arr;
    private int numberOfElements;
    int middleIndex;

    public QuickMedian(int max) {

        arr = new long[max];
        numberOfElements = 0;
        middleIndex = 0;
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
        while (true) {

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

    public long findMedian(int left, int right) {

        if(right-left <= 0) {
            return arr[middleIndex];
        } else {
            int partition = partitionIt(left, right);

            if (partition == middleIndex) {
                return arr[middleIndex];
            } else if (partition < middleIndex) {
                return findMedian(partition + 1, right);
            } else {
                return findMedian(left, partition - 1);
            }
        }
    }

    public static void main(String[] args) {

        QuickMedian quickMedian = new QuickMedian(51);

        for (int i = 0; i < 51; i++) {
            quickMedian.insert((long)(Math.random() * 100));
        }

        quickMedian.middleIndex = quickMedian.size()/2;

        quickMedian.display();

        long median = quickMedian.findMedian(0, quickMedian.size() - 1);

        System.out.println("Median: " + median);
    }

    private void swap(int firstIndex, int secondIndex) {
        long temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
