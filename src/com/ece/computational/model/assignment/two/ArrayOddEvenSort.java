package com.ece.computational.model.assignment.two;

/**
 * Problem 3.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class ArrayOddEvenSort {

    private long[] arr;
    private int numberOfElements;

    public ArrayOddEvenSort(int max) {
        arr = new long[max];
        numberOfElements = 0;
    }

    public void insert(long value) {
        arr[numberOfElements] = value;
        numberOfElements++;
    }

    public void display() {
        for(int j=0; j<numberOfElements; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public void bubbleSort() {
        int out, in;

        for(out = numberOfElements - 1; out > 1; out--)
            for(in = 0; in < out; in++)
                if(arr[in] > arr[in+1]) {
                    swap(in, in + 1);
                }
    }

    public void oddEvenSort() {

        boolean elementSwapped = true;
        while(elementSwapped) {
            elementSwapped = false;

            // Odd
            for(int i = 1; i < numberOfElements - 1; i += 2) {
                if(arr[i] > arr[i+1] ) {
                    swap(i, i + 1);
                    elementSwapped = true;
                }
            }

            // Even
            for(int i = 0; i < numberOfElements - 1; i += 2) {
                if(arr[i] > arr[i+1] ) {
                    swap(i, i + 1);
                    elementSwapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        int maxSize = 100;
        ArrayOddEvenSort arr1 = new ArrayOddEvenSort(maxSize);

        arr1.insert(77);
        arr1.insert(99);
        arr1.insert(44);
        arr1.insert(55);
        arr1.insert(22);
        arr1.insert(88);
        arr1.insert(11);
        arr1.insert(00);
        arr1.insert(66);
        arr1.insert(33);

        System.out.println("Array before sorting");
        arr1.display();
        System.out.println();

        arr1.bubbleSort();

        System.out.println("Array after sorting using bubble sort");
        arr1.display();
        System.out.println();

        ArrayOddEvenSort arr2 = new ArrayOddEvenSort(maxSize);

        arr2.insert(77);
        arr2.insert(99);
        arr2.insert(44);
        arr2.insert(55);
        arr2.insert(22);
        arr2.insert(88);
        arr2.insert(11);
        arr2.insert(00);
        arr2.insert(66);
        arr2.insert(33);

        System.out.println("Array before sorting");
        arr2.display();
        System.out.println();

        arr2.oddEvenSort();

        System.out.println("Array after sorting using odd-even sort");
        arr2.display();
    }

    private void swap(int firstIndex, int secondIndex) {
        long temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
