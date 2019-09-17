package com.ece.computational.model.assignment.two;

/**
 * Problem 3.6
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class ArrayModifiedInsertionSort {

    private long[] arr;
    private int numberOfElements;

    public ArrayModifiedInsertionSort(int max) {
        arr = new long[max];
        numberOfElements = 0;
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

    public void insertionSort() {

        int in, out;
        for (out = 1; out < numberOfElements; out++) {
            long temp = arr[out];
            in = out;
            while(in > 0 && arr[in-1] >= temp) {
                arr[in] = arr[in-1];
                --in;
            }
            arr[in] = temp;
        }
    }

    public void insertionSortRemovingDuplicates() {

        int in, out;
        int numberOfDuplicates = 0;
        for (out = 1; out < numberOfElements; out++) {
            long temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] != -1 && arr[in - 1] >= temp) {

                // setting the duplicate value to -1
                if (arr[in - 1] == temp) {
                    temp = -1;
                    numberOfDuplicates++;
                }

                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }

        if (numberOfDuplicates != 0) {
            for (int i = numberOfDuplicates; i < numberOfElements; i++) {
                arr[i - numberOfDuplicates] = arr[i];
            }
        }

        numberOfElements -= numberOfDuplicates;
    }

    public static void main(String[] args) {

        int maxSize = 100;
        ArrayModifiedInsertionSort arr = new ArrayModifiedInsertionSort(maxSize);

        arr.insert(12);
        arr.insert(14);
        arr.insert(23);
        arr.insert(67);
        arr.insert(23);
        arr.insert(20);
        arr.insert(67);
        arr.insert(23);
        arr.insert(1);
        arr.insert(30);
        arr.insert(4);
        arr.insert(1);
        arr.insert(67);
        arr.insert(10);
        arr.insert(20);
        arr.insert(23);
        arr.insert(40);
        arr.insert(20);
        arr.insert(1);

        System.out.println("Array before sorting");
        arr.display();
        System.out.println();

        arr.insertionSortRemovingDuplicates();

        System.out.println("Array after sorting and removing duplicates");
        arr.display();
    }
}
