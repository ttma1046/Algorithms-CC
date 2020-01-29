package graph;

import java.util.List;

public class Dijkstra {
    public double[] ShortestDistances() {
        return shortestDistances;
    }
    public double[] Paths() {
        return previousVerticws;
    }

    private double[] shortestDistances;
    private double[] previousVerticws;
    private List<Integer> unvisitedVertices;

    public Dijkstra(double[][] adjacencyMatrix, int totalVertices) {
         shortestDistances = new double[totalVertices];
         previousVerticws = new double[totalVertices];

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
                         previousVerticws[i] = currentVertex;
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

        unvisitedVertices.remove(vertex);
        return vertex;
    }

    // A------B-----C
    // |    / |     |
    //    /   |    |
    // D   -  E ---
    //
    //

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra(new double[3][3], 5);
        double[] distances = graph.ShortestDistances();
        double[] paths = graph.Paths();

        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + "  Distance = " + distances[i] + " Via Vertex" + paths[i]);
        }
    }
}
