package com.ece.computational.model.assignment.five;

import java.util.Scanner;

/**
 * Problem 6.5
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class PossibleCombinations {

    PossibleCombinations() {

    }

    public void showTeams(int arr[], int data[], int start, int end, int index, int teamSize) {

        if (index == teamSize) {
            for (int j = 0; j < teamSize; j++) {
                System.out.print(data[j] + " ");
            }
            System.out.println("");
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= teamSize - index; i++) {
            data[index] = arr[i];
            showTeams(arr, data, i + 1, end, index + 1, teamSize);
        }
    }

    public void print(int groupSize, int teamSize) {

        int arr[] = new int[groupSize];
        int data[] = new int[teamSize];
        int j = 0;

        for (int i = 1; i <= groupSize; i++) {
            arr[j++] = i;
        }

        showTeams(arr, data, 0, groupSize - 1, 0, teamSize);
    }

    public static void main (String[] args) {

        PossibleCombinations possibleCombinations = new PossibleCombinations();
        Scanner s = new Scanner(System.in);

        System.out.println("Enter group size");
        int groupSize = s.nextInt();

        System.out.println("Enter team size");
        int teamSize = s.nextInt();

        System.out.println("Possible combinations are:");
        possibleCombinations.print(groupSize, teamSize);
    }
}
