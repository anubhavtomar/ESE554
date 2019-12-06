package com.ece.computational.model.assignment.eleven;

/**
 * Problem 13.5
 *
 * @author anubhav tomar (ID: 112268905)
 */
public class KnightsTour {

    public static final int SIZE = 5;
    public static final int AREA = 5 * 5;
    private KnightsVertex vertexList[];
    private int adjacencyMatrix[][];
    private int numberOfVertices;
    private KnightsStackX knightsStack;

    public KnightsTour() {

        numberOfVertices = AREA;
        adjacencyMatrix = new int[AREA][AREA];
        vertexList = new KnightsVertex[AREA];
        knightsStack = new KnightsStackX();

        for(int i = 0; i < AREA; i++) {
            vertexList[i] = new KnightsVertex();
        }

        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                addPossibleMoves(i, j);
            }
        }

        for(int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                adjacencyMatrix[j][k] = 0;
            }
        }
    }

    public void addPossibleMoves(int i, int j) {

        int currentRow = i * SIZE;
        int currentCol = j;
        int currentVertex = currentRow+currentCol;

        if(i - 1 >= 0) {
            if(j - 2 >= 0) {
                addEdge(currentVertex, currentVertex - SIZE - 2);
            }

            if(j + 2 < SIZE) {
                addEdge(currentVertex, currentVertex - SIZE + 2);
            }
        }

        if(i + 1 < SIZE) {
            if(j - 2 >= 0) {
                addEdge(currentVertex, currentVertex + SIZE - 2);
            }

            if(j + 2 < SIZE) {
                addEdge(currentVertex, currentVertex + SIZE + 2);
            }
        }

        if(i -  2 >= 0) {
            if(j - 1 >= 0) {
                addEdge(currentVertex, currentVertex - (SIZE * 2) - 1);
            }

            if(j + 1 < SIZE) {
                addEdge(currentVertex, currentVertex - (SIZE * 2) + 1);
            }
        }

        if (i + 2 < SIZE) {
            if (j - 1 >= 0) {
                addEdge(currentVertex, currentVertex + (SIZE * 2) - 1);
            }

            if (j + 1 < SIZE) {
                addEdge(currentVertex, currentVertex + (SIZE * 2) + 1);
            }
        }
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void displayBoard() {

        System.out.println("..................................");
        for (int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                System.out.print(vertexList[i * SIZE + j].label);
            }
            System.out.println();
        }
        System.out.println("..................................");
    }

    public boolean dfs(int start) {

        vertexList[start].label = 'S';
        vertexList[start].wasVisited = true;
        knightsStack.push(start);
        while (!knightsStack.isEmpty()) {
            int m = knightsStack.peek();
            vertexList[m].label = 'M';

            if (knightsStack.isFull()) {
                for (int j = 0; j < numberOfVertices; j++) {

                    vertexList[j].label = '-';
                    vertexList[j].wasVisited = false;
                    vertexList[j].lastVisited = -1;

                    while (!vertexList[j].justVisited.isEmpty()) {
                        vertexList[j].justVisited.pop();
                    }

                    KnightsStackX winningMoves = new KnightsStackX();

                    while (!knightsStack.isEmpty()) {
                        winningMoves.push(knightsStack.pop());
                    }

                    while (!winningMoves.isEmpty()) {
                        System.out.print(winningMoves.pop() + " ");
                    }
                }

                vertexList[0] = new KnightsVertex();
                System.out.println();

                return true;
            }

            int v = getNextVertex( knightsStack.peek() );

            if (v == -1) {
                int unmark = knightsStack.pop();
                vertexList[unmark].label = '-';
                vertexList[unmark].wasVisited = false;
                vertexList[unmark].lastVisited = -1;
            } else {
                vertexList[v].wasVisited = true;
                int curVertex = knightsStack.peek();
                vertexList[curVertex].label = 'K';
                vertexList[curVertex].lastVisited = v;
                knightsStack.push(v);
            }
        }

        for (int j = 0; j < numberOfVertices; j++) {
            vertexList[j].label = '-';
            vertexList[j].wasVisited = false;
            vertexList[j].lastVisited = -1;
            while (!vertexList[j].justVisited.isEmpty()) {
                vertexList[j].justVisited.pop();
            }
        }

        return false;
    }

    public int getNextVertex(int v) {

        for (int j = vertexList[v].lastVisited + 1; j < numberOfVertices; j++) {
            if (adjacencyMatrix[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }

    public int getAdjUnvisitedVertex(int v) {

        for (int j = 0; j < numberOfVertices; j++) {
            if (adjacencyMatrix[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }

    public void unmarkVisited(int v) {

        while (!vertexList[v].justVisited.isEmpty()) {

            int j = vertexList[v].justVisited.pop();
            vertexList[j].label = '-';
            vertexList[j].wasVisited = false;
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void displayMatrix(int[][] matrix) {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        KnightsTour knightsTour = new KnightsTour();
        System.out.println("CREATED GRAPH");
        boolean solutionFound = false;
        knightsTour.displayMatrix(knightsTour.getAdjacencyMatrix());

        for (int i = 0; i < knightsTour.AREA; i++) {
            solutionFound = knightsTour.dfs(i);
            if (solutionFound) {
                System.out.println("FOUND A SOLUTION STARTING AT ("
                        + (i / knightsTour.SIZE) + ", " + (i % knightsTour.SIZE) + ")");
            } else {
                System.out.println("NO SOLUTION FROM ("
                        + (i / knightsTour.SIZE) + ", " + (i % knightsTour.SIZE) + ")");
            }
        }
    }
}

class KnightsStackX {

    private int[] stack;
    private int top;

    public KnightsStackX() {

        stack = new int[KnightsTour.AREA];
        top = -1;
    }

    public void push(int j) {
        stack[++top] = j;
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }

    public boolean oneItem() {
        return top == 0;
    }
}

class KnightsVisitedStackX {

    private int[] stack;
    private int top;

    public KnightsVisitedStackX() {

        stack = new int[8];
        top = -1;
    }

    public void push(int j) {
        stack[++top] = j;
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return top == (stack.length - 1);
    }
}

class KnightsVertex {

    public char label;
    public boolean wasVisited;
    public KnightsVisitedStackX justVisited;
    public int lastVisited;

    public KnightsVertex() {

        label = '-';
        wasVisited = false;
        lastVisited = -1;
        justVisited = new KnightsVisitedStackX();
    }
}
