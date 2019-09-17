package com.ece.computational.model.assignment.one;

/**
 * Problem 2.4
 * ----------------------------------------------------------------------------------------------------------------
 * Modify the orderedArray.java program (Listing 2.4) so that the insert() and delete() routines, as well as find(),
 * use a binary search, as suggested in the text.
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class OrdArray {

    private long[] arr;
    private int numberOfElements;

    public OrdArray(int max) {

        arr = new long[max];
        numberOfElements = 0;
    }

    public int size() {

        return numberOfElements;
    }

    public int find(long searchKey) {

        int lowerBound = 0;
        int upperBound = numberOfElements - 1;
        int curIn;
        while(true) {
            curIn = (lowerBound + upperBound) / 2;
            if(arr[curIn]==searchKey) {
                return curIn; //
            } else if (lowerBound > upperBound) {
                return numberOfElements;
            } else {
                if(arr[curIn] < searchKey) {
                    lowerBound = curIn + 1;
                }
                else {
                    upperBound = curIn - 1;
                }
            }
        }
    }

    public void insert(long value) {

        int j = findCeilUsingBinarySearch(value); // uses binary search here
        /*for(j = 0; j < numberOfElements; j++) {
            if (arr[j] > value)  {
                break;
            }
        }*/

        for(int k = numberOfElements; k > j; k--) {
            arr[k] = arr[k-1];
        }
        arr[j] = value;
        numberOfElements++;
    }

    public boolean delete(long value) {

        int j = find(value); // uses binary search here
        if(j == numberOfElements) {
            return false;
        } else {
            for(int k=j; k < numberOfElements; k++) {
                arr[k] = arr[k+1];
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

    public static void main(String[] args) {

        int maxSize = 100;
        OrdArray ordArray;

        ordArray = new OrdArray(maxSize);
        ordArray.insert(77);
        ordArray.insert(99);
        ordArray.insert(44);
        ordArray.insert(55);
        ordArray.insert(22);
        ordArray.insert(88);
        ordArray.insert(11);
        ordArray.insert(00);
        ordArray.insert(66);
        ordArray.insert(33);

        int searchKey = 55;
        if (ordArray.find(searchKey) != ordArray.size()) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can’t find " + searchKey);
        }

        ordArray.display();

        ordArray.delete(00);
        ordArray.delete(55);
        ordArray.delete(99);

        ordArray.display();
    }

    private int findCeilUsingBinarySearch(long searchKey) {

        int leftIndex = 0;
        int rightIndex = numberOfElements - 1;
        int midIndex;
        while(true) {
            midIndex = (leftIndex + rightIndex) / 2;
            if(searchKey <= arr[leftIndex]) {
                return leftIndex;
            }
            if(arr[midIndex] > searchKey && midIndex - 1 >= leftIndex && arr[midIndex - 1] < searchKey ) {
                return midIndex;
            } else if (leftIndex > rightIndex) {
                return numberOfElements;
            } else {
                if(arr[midIndex] < searchKey) {
                    leftIndex = midIndex + 1;
                }
                else {
                    rightIndex = midIndex - 1;
                }
            }
        }
    }
}
