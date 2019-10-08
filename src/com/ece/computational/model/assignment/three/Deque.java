package com.ece.computational.model.assignment.three;

/**
 * Problem 4.2
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class Deque {

    private int maxSize;
    private long[] arr;
    private int front;
    private int rear;
    private int numberOfElements;

    public Deque (int maxSize) {

        this.maxSize = maxSize;
        arr = new long[maxSize];
        rear = -1;
        front = -1;
        numberOfElements = 0;
    }

    public void insertLeft(int val) {

        if(isFull()) {
            System.out.println("Overflow: Deque is full");
        } else {

            if (rear == -1) {
                front = rear = 0;
            } else if (front == 0) {
                front = maxSize - 1;
            } else {
                front--;
            }

            arr[front] = val;
            numberOfElements++;
        }
    }

    public void insertRight(int val) {

        if(isFull()) {
            System.out.println("Overflow: Deque is full");
        } else {

            if (front == -1) {
                front = 0;
                rear = 0;
            } else if (rear == maxSize - 1) {
                rear = 0;
            } else {
                rear++;
            }

            arr[rear] = val;
            numberOfElements++;
        }
    }

    public void removeLeft() {

        if(isEmpty()) {
            System.out.println("Underflow: Deque is empty");
        } else {
            if (front == rear) {
                front = rear = -1;
            } else {
                if (front == maxSize -1) {
                    front = 0;
                } else {
                    front++;
                }
            }
            numberOfElements--;
        }
    }

    public void removeRight() {

        if(isEmpty()) {
            System.out.println("Underflow: Deque is empty");
        } else {
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (rear == 0) {
                rear = maxSize - 1;
            } else {
                rear = rear-1;
            }
            numberOfElements--;
        }
    }

    public void display() {

        int j = front;
        for(int i = 0; i < numberOfElements; i++) {
            System.out.print(arr[j] + " ");
            j++;
            if(j == maxSize) {
                j = 0;
            }
        }
        System.out.println();
    }

    public boolean isFull() {
        return ((front == 0 && rear == maxSize - 1)|| front == rear + 1);
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public static void main(String[] args) {

        Deque deque = new Deque(5);

        deque.insertLeft(150);
        deque.insertLeft(120);
        System.out.println("Array after 2 left insertions");
        deque.display();
        System.out.println();

        deque.insertLeft(430);
        System.out.println("Array after 1 left insertion");
        deque.display();
        System.out.println();

        System.out.println("Array after 1 right insertion");
        deque.insertRight(50);
        deque.display();
        System.out.println();

        deque.removeRight();
        System.out.println("Array after 1 right deletion");
        deque.display();
        System.out.println();

        deque.removeLeft();
        System.out.println("Array after 1 left deletion");
        deque.display();
        System.out.println();

        deque.removeLeft();
        System.out.println("Array after 1 left deletion");
        deque.display();
        System.out.println();

    }

}
