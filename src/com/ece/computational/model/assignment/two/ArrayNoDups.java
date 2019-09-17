package com.ece.computational.model.assignment.two;

/**
 * Problem 3.3
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class ArrayNoDups {

    private long[] arr;
    private int numberOfElements;

    public ArrayNoDups(int max) {
        arr = new long[max];
        numberOfElements = 0;
    }

    public void insert(long value) {
        arr[numberOfElements] = value;
        numberOfElements++;
    }

    public void display() {
        for(int j = 0; j < numberOfElements; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public void insertionSort() {

        int in, out;
        for(out = 1; out < numberOfElements; out++) {
            long temp = arr[out];
            in = out;
            while(in > 0 && arr[in-1] >= temp) {
                arr[in] = arr[in-1];
                --in;
            }
            arr[in] = temp;
        }
    }

    public void noDups() {

        int numberOfElementsDeleted = 0, currentIndex = 0;
        for (int i = 1; i < numberOfElements; i++) {
            if (arr[i] == arr[currentIndex]) {
                numberOfElementsDeleted++;
            } else {
                arr[++currentIndex] = arr[i];
            }
        }

        numberOfElements -= numberOfElementsDeleted;
    }

    public static void main(String[] args) {

        int maxSize = 100;
        ArrayNoDups arr = new ArrayNoDups(maxSize);

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

        arr.insertionSort();

        System.out.println("Array after sorting");
        arr.display();
        System.out.println();

        arr.noDups();

        System.out.println("Array after removing duplicates");
        arr.display();
    }
}
