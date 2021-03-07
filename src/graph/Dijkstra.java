package graph;

import java.util.List;
import java.util.ArrayList;

public class Dijkstra {
    public double[] ShortestDistances() {
        return shortestDistances;
    }
    public int[] PreviousVertices() {
        return previousVertices;
    }

    private double[] shortestDistances;
    private int[] previousVertices;
    private List<Integer> unvisitedVertices = new ArrayList<>();

    public Dijkstra(double[][] adjacencyMatrix, int totalVertices) {
         shortestDistances = new double[totalVertices];
         previousVertices = new int[totalVertices];

         for(int i = 0; i < totalVertices; i++) {
             unvisitedVertices.add(i);
             shortestDistances[i] = Double.MAX_VALUE;
         }

         shortestDistances[0] = 0;

         while (unvisitedVertices.size() > 0) {
             int currentVertex = getNextVertex();

             for (int i = 0; i < totalVertices; i++) {
                if (adjacencyMatrix[currentVertex][i] > 0) {
                     if (shortestDistances[currentVertex] + adjacencyMatrix[currentVertex][i] < shortestDistances[i]) {
                         shortestDistances[i] = shortestDistances[currentVertex] + adjacencyMatrix[currentVertex][i];
                         previousVertices[i] = currentVertex;
                     }
                }
             }
         }
    }

    private int getNextVertex() {
        // return the unvisited vertex with the smallest know distance from the start
        double smallestKnownDistance = Double.MAX_VALUE;
        int vertex = -1;

        for (int value: unvisitedVertices) {
            if  (shortestDistances[value] <= smallestKnownDistance) {
                smallestKnownDistance = shortestDistances[value];
                vertex = value;
            }
        }

        unvisitedVertices.remove(unvisitedVertices.indexOf(vertex));
        return vertex;
    }

    // A------B-----C
    // |    / |     |
    //    /   |    |
    // D   -  E ---
    //
    //

    public static void main(String[] args) {
        double[][] adjacencyMatrix = new double[][] {{0, 6, 0, 1, 0}, {6, 0, 5, 2, 2}, {0, 5, 0, 0, 5}, {1, 2, 0, 0, 1}, {0, 2, 5, 1, 0}};
        Dijkstra graph = new Dijkstra(adjacencyMatrix, 5);
        double[] distances = graph.ShortestDistances();
        int[] previousVertices = graph.PreviousVertices();

        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + " Shortest Distance from Vertex 0 is " + distances[i] + " Via Vertex:" + previousVertices[i]);
        }
    }

    class Graph {
        List<String> vertexs = new ArrayList<String>();
        void AddVertex(String vertex) {
            vertexs.add(vertex);
        }

        void AddEdge(int start, int end, int weight) { }
    }

/*

      0 1 2 3 4
    0 0 6 0 1 0
    1 6 0 5 2 2
    2 0 5 0 0 5
    3 1 2 0 0 1
    4 0 2 5 1 0
*/
}
