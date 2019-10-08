package com.ece.computational.model.assignment.five;

/**
 * Problem 6.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class KnapsackProblem {

    int length;
    int[] weights;

    KnapsackProblem(int length, int[] weights) {

        this.length = length;
        this.weights = weights;
    }

    public boolean knapsack(int startingIndex, int targetWeight){

        for(int i = startingIndex; i < length; i++){
            if (weights[i] == targetWeight){
                System.out.print(weights[i] + " ");
                return true;
            } else {
                if(weights[i] < targetWeight){
                    for(int j = i + 1; j < length; j++){
                        if (knapsack(j, targetWeight - weights[i])) {
                            System.out.print(weights[i] + " ");
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void main(String args[]){

        KnapsackProblem knapsackProblem = new KnapsackProblem(6, new int[]{10, 4, 7, 26, 5, 1});

        knapsackProblem.knapsack(0, 35);
    }
}
