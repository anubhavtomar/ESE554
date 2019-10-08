package com.ece.computational.model.assignment.three;

/**
 * Problem 4.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class PriorityQ {

    private int[] arr;
    private int maxSize;
    private int rear;
    private int front;

    public PriorityQ(int maxSize) {

        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }

    public boolean isFull() {

        if(rear == maxSize - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {

        if(rear == -1 ) {
            return true;
        } else {
            return false;
        }
    }

    public void display() {

        if(!isEmpty()) {
            for(int i = front; i <= rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        else {
            System.out.println("PriorityQ is empty");
        }
    }

    public void insert(int val) {

        if(isFull()) {
            System.out.println("Overflow: PriorityQ is full");
        } else {
            if(isEmpty()) {
                front = rear = 0;
                arr[rear] = val;
            } else {
                rear++;
                arr[rear] = val;
            }
        }
    }

    public int delete() {

        if(!isEmpty()) {
            if(rear == front) {
                int minValue = arr[rear];
                rear = front = -1;
                return minValue;
            } else {
                int minValuePosition = 0;
                int minValue = arr[0];

                for(int i = 0; i <= rear; i++) {
                    if(arr[i] < minValue) {
                        minValue = arr[i];
                        minValuePosition = i;
                    }
                }

                for(int i = minValuePosition; i < rear; i++) {
                    arr[i] = arr[i + 1];
                }

                maxSize--;
                rear--;
                return minValue;
            }
        } else {
            System.out.println("Underflow: PriorityQ is empty");
        }
        return -1;
    }

    public static void main(String[] args) {

        PriorityQ priorityQ = new PriorityQ(5);

        priorityQ.insert(30);
        priorityQ.insert(50);
        priorityQ.insert(10);
        priorityQ.insert(40);
        priorityQ.insert(20);

        System.out.println("Priority Queue after 5 insertions");
        priorityQ.display();
        System.out.println();

        System.out.println("Removal of the high-priority item");
        while(!priorityQ.isEmpty()) {
            System.out.print(priorityQ.delete() + " ");
        }
        System.out.println("");
    }
}
