package com.ece.computational.model.assignment.nine;

/**
 * Problem 11.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
class DataItem {

    private int iData;

    public DataItem(int ii) {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}

public class HashTable {

    private DataItem[] hashArray;
    private int arraySize;
    private DataItem nonItem;
    private double numItems;

    public HashTable(int size) {

        numItems = 0;
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }

    public void displayHashTable() {
        
        for(int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println("");
    }

    public double getLoadFactor() {
        return (numItems / (double) arraySize);
    }

    public HashTable rehash() {

        int newSize = getPrime(arraySize * 2);
        HashTable newTable = new HashTable(newSize);
        newTable.setNumItems(numItems);

        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null && hashArray[j].getKey() != -1) {
                newTable.rehashInsert(hashArray[j], newSize);
            }
        }

        return newTable;
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(DataItem dataItem) {

        int key = dataItem.getKey();
        int hashValue = hashFunc(key);
        while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
            ++hashValue;
            hashValue %= arraySize;
        }
        hashArray[hashValue] = dataItem;
        numItems++;
    }

    public DataItem delete(int key) {
        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                numItems--;
                return temp;
            }
            ++hashVal;
            hashVal %= arraySize;
        }

        return null;
    }

    public DataItem find(int key) {

        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }

        return null;
    }

    public static void main(String[] args) {

        DataItem dataItem;

        HashTable hashTable = new HashTable(20);

        dataItem = new DataItem(1);
        hashTable.insert(dataItem);
        dataItem = new DataItem(2);
        hashTable.insert(dataItem);
        dataItem = new DataItem(3);
        hashTable.insert(dataItem);
        dataItem = new DataItem(4);
        hashTable.insert(dataItem);
        dataItem = new DataItem(5);
        hashTable.insert(dataItem);
        dataItem = new DataItem(6);
        hashTable.insert(dataItem);
        dataItem = new DataItem(7);
        hashTable.insert(dataItem);
        dataItem = new DataItem(8);
        hashTable.insert(dataItem);
        dataItem = new DataItem(9);
        hashTable.insert(dataItem);
        dataItem = new DataItem(10);
        hashTable.insert(dataItem);
        dataItem = new DataItem(11);
        hashTable.insert(dataItem);

        hashTable.displayHashTable();

        System.out.println();
        System.out.println("Deleted 2");
        hashTable.delete(2);

        hashTable.displayHashTable();
        System.out.println();

        dataItem = hashTable.find(3);
        if (dataItem != null) {
            System.out.println("Found " + 3);
        } else {
            System.out.println("Could not find " + 3);
        }

        dataItem = hashTable.find(2);
        if (dataItem != null) {
            System.out.println("Found " + 2);
        } else {
            System.out.println("Could not find " + 2);
        }
    }

    private int getPrime(int min) {
        for (int j = min + 1; true; j++) {
            if (isPrime(j)) {
                return j;
            }
        }
    }

    private boolean isPrime(int n) {
        for (int j = 2; (j * j <= n); j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    private void rehashInsert(DataItem item, int size) {

        int key = item.getKey();
        int hashVal = key % size;
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }

    private void setNumItems(double number) {
        numItems = number;
    }
}

