package com.ece.computational.model.assignment.one;

import java.util.HashMap;

/**
 * Problem 2.1
 * ----------------------------------------------------------------------------------------------------------------
 * To the HighArray class in the highArray.java program (Listing 2.3), add a method called getMedian()
 *
 * Problem 2.6
 * ----------------------------------------------------------------------------------------------------------------
 * Write a noDups() method for the HighArray class of the highArray.java program (Listing 2.3).
 */
public class HighArray {

    private long[] arr;
    private int numberOfElements;

    public HighArray(int max) {

        arr = new long[max];
        numberOfElements = 0;
    }

    public boolean find(long searchKey) {

        int j = 0;
        for (j = 0; j < numberOfElements; j++) {
            if (arr[j] == searchKey) {
                break;
            }
        }

        if (j == numberOfElements) {
            return false;
        } else {
            return true;
        }
    }

    public void insert(long value) {

        arr[numberOfElements] = value;
        numberOfElements++;
    }

    public boolean delete(long value) {

        int j;
        for (j = 0; j < numberOfElements; j++) {
            if (value == arr[j]) {
                break;
            }
        }

        if (j == numberOfElements) {
            return false;
        }
        else {
            for (int k = j; k < numberOfElements; k++) {
                arr[k] = arr[k + 1];
            }
            numberOfElements--;
            return true;
        }
    }

    public void display() {

        for(int j=0; j < numberOfElements; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public long getMedian() {

        sortElements(arr, 0, numberOfElements - 1);
        if (numberOfElements % 2 == 0) {
            return (arr[numberOfElements / 2 - 1] + arr[numberOfElements / 2]) / 2;
        } else {
            return arr[numberOfElements / 2];
        }
    }

    public void noDups() {

        HashMap<Long, Boolean> arrayValuesMap = new HashMap<>();

        for (int i = 0; i < numberOfElements; i++) {
            if (arrayValuesMap.get(arr[i]) != null) {
                deleteElementAtIndex(i);
            }
            arrayValuesMap.put(arr[i], true);
        }
    }

    private void deleteElementAtIndex(int index) {

        for (int k = index; k < numberOfElements; k++) {
            arr[k] = arr[k + 1];
        }
        numberOfElements--;
    }

    /**
     * Implementing Merge Sort algorithm
     *
     * @param arr
     * @param leftIndex
     * @param rightIndex
     */
    private void sortElements(long[] arr, int leftIndex, int rightIndex) {

        if (leftIndex < rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            sortElements(arr, leftIndex, midIndex);
            sortElements(arr, midIndex + 1, rightIndex);

            mergeSortedArrays(arr, leftIndex, midIndex, rightIndex);
        }
    }

    private void mergeSortedArrays(long[] arr, final int leftIndex, final int midIndex, final int rightIndex) {

        final int endOfLeftIndex = midIndex - leftIndex + 1;
        final int endOfRightIndex = rightIndex - midIndex;

        final long leftArr[] = new long [endOfLeftIndex];
        for (int i = 0; i < endOfLeftIndex; i++) {
            leftArr[i] = arr[leftIndex + i];
        }

        final long rightArr[] = new long [endOfRightIndex];
        for (int j = 0; j < endOfRightIndex; j++) {
            rightArr[j] = arr[midIndex + 1 + j];
        }

        int leftArrIndex = 0, rightArrIndex = 0, resultIndex = leftIndex;
        while (leftArrIndex < endOfLeftIndex && rightArrIndex < endOfRightIndex) {
            if (leftArr[leftArrIndex] == rightArr[rightArrIndex]) {
                arr[resultIndex++] = leftArr[leftArrIndex++];
                arr[resultIndex++] = rightArr[rightArrIndex++];
            } else if (leftArr[leftArrIndex] < rightArr[rightArrIndex]) {
                arr[resultIndex++] = leftArr[leftArrIndex++];
            } else {
                arr[resultIndex++] = rightArr[rightArrIndex++];
            }
        }

        while (leftArrIndex < endOfLeftIndex) {
            arr[resultIndex++] = leftArr[leftArrIndex++];
        }

        while (rightArrIndex < endOfRightIndex) {
            arr[resultIndex++] = rightArr[rightArrIndex++];
        }
    }
}

class HighArrayApp {

    public static void main(String[] args) {

        int maxSize = 100;

        HighArray highArray;
        highArray = new HighArray(maxSize);
        highArray.insert(77);
        highArray.insert(99);
        highArray.insert(44);
        highArray.insert(55);
        highArray.insert(99);
        highArray.insert(22);
        highArray.insert(88);
        highArray.insert(11);
        highArray.insert(00);
        highArray.insert(66);
        highArray.insert(22);
        highArray.insert(33);
        highArray.insert(63);

        highArray.display();

        int searchKey = 35;
        if( highArray.find(searchKey) ) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can’t find " + searchKey);
        }

        highArray.delete(00);
        highArray.delete(55);
        highArray.delete(99);

        highArray.display();

        highArray.noDups();
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Array after removing duplicates");
        highArray.display();
        System.out.println();
        System.out.println("Median -> " + highArray.getMedian());
        highArray.display();
        System.out.println("-------------------------------");
    }
}
