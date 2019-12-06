package com.ece.computational.model.assignment.eleven;

/**
 * Problem 13.4
 *
 * @author anubhav tomar (ID: 112268905)
 */
class WarshallAlgoGraph {

    private final int MAX_VERTICES = 30;
    private WarshallVertex vertexList[];
    private int adjacencyMatrix[][];
    private int numberOfVertices;
    private WarshallStackX warshallStackX;

    public WarshallAlgoGraph() {
        
        vertexList = new WarshallVertex[MAX_VERTICES];
        adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        numberOfVertices = 0;

        for (int i = 0; i < MAX_VERTICES; i++) {
            for (int j = 0; j < MAX_VERTICES; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        warshallStackX = new WarshallStackX();
    }

    public void addVertex(char label) {
        vertexList[numberOfVertices++] = new WarshallVertex(label);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void dfs(int start) {

        vertexList[start].wasVisited = true;
        displayVertex(start);
        warshallStackX.push(start);
        while (!warshallStackX.isEmpty()) {
            int v = getAdjUnvisitedVertex(warshallStackX.peek());

            if(v == -1) {
                warshallStackX.pop();
            }
            else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                warshallStackX.push(v);
            }
        }

        for (int i = 0; i < numberOfVertices; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    public int getAdjUnvisitedVertex(int v) {

        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[v][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    public void displayConnectivityTable() {

        for(int j = 0; j < numberOfVertices; j++) {
            dfs(j);
            System.out.println();
        }
    }

    public int[][] warshall() {

        int[][] newAdjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }

        for(int y = 0; y < numberOfVertices; y++) {

            for(int x = 0; x < numberOfVertices; x++) {
                if(adjacencyMatrix[y][x] == 1) {
                    for(int z = 0; z < numberOfVertices; z++) {
                        if (adjacencyMatrix[z][y] == 1) {
                            newAdjacencyMatrix[z][x] = 1;
                        }
                    }
                }
            }
        }

        return newAdjacencyMatrix;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void displayMatrix(int[][] matrix) {

        for (int i = 0; i < numberOfVertices; i++) {
            for(int j = 0; j < numberOfVertices; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        WarshallAlgoGraph warshallAlgoGraph = new WarshallAlgoGraph();
        warshallAlgoGraph.addVertex('A');
        warshallAlgoGraph.addVertex('B');
        warshallAlgoGraph.addVertex('C');
        warshallAlgoGraph.addVertex('D');
        warshallAlgoGraph.addVertex('E');
        warshallAlgoGraph.addVertex('F');
        warshallAlgoGraph.addEdge(0, 1);
        warshallAlgoGraph.addEdge(1, 0);
        warshallAlgoGraph.addEdge(2, 0);
        warshallAlgoGraph.addEdge(1, 4);
        warshallAlgoGraph.addEdge(2, 4);
        warshallAlgoGraph.addEdge(3, 4);
        warshallAlgoGraph.addEdge(4, 2);
        warshallAlgoGraph.addEdge(1, 5);

        warshallAlgoGraph.displayConnectivityTable();
        int[][] newAdjacencyMatrix = warshallAlgoGraph.warshall();
        warshallAlgoGraph.displayMatrix(warshallAlgoGraph.getAdjacencyMatrix());
        System.out.println();
        warshallAlgoGraph.displayMatrix(newAdjacencyMatrix);
    }
}

class WarshallStackX {

    private final int SIZE = 30;
    private int[] st;
    private int top;

    public WarshallStackX() {
        st = new int[SIZE];
        top = -1;
    }

    public void push(int j) {
        st[++top] = j;
    }

    public int pop() {
        return st[top--];
    }

    public int peek() {
        return st[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class WarshallVertex {

    public char label;
    public boolean wasVisited;

    public WarshallVertex(char label) {

        this.label = label;
        wasVisited = false;
    }
}
