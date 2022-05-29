package union_find;

// Java Program for union-find algorithm to detect cycle in a graph
import java.util.*;
import java.lang.*;
import java.io.*;

public class UnionFindtoDetectCycle {
    // The main function to check whether a given graph
    // contains cycle or not
    int isCycle(Graph graph) {
        // Allocate memory for creating V subsets
        int parent[] = new int[graph.V];

        // Initialize all subsets as single element sets
        for (int i = 0; i < graph.V; ++i) {
            parent[i] = -1;
        }

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.

        for (int i = 0; i < graph.E; ++i) {
            int x = graph.find(parent, graph.edges[i].src);
            int y = graph.find(parent, graph.edges[i].dest);

            if (x == y) {
                return 1;
            }

            graph.Union(parent, x, y);
        }
        return 0;
    }

    // Driver Method
    public static void main(String[] args) {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */

        /* Let us create following graph
         0
        |
        |
        1-----2-----3 */
        int V = 4;
        int[][] links = new int[][] {{ 0, 1 }, { 1, 2 }, { 3, 2 }};
        int E = links.length;

        Graph graph = new Graph(V, E, links);

        if (new UnionFindtoDetectCycle().isCycle(graph) == 1) {
            System.out.println( "graph contains cycle" );
        } else {
            System.out.println( "graph doesn't contain cycle" );
        }

        V = 3;
        links = new int[][] {{ 0, 1 }, { 1, 2 }, { 0, 2 }};
        E = links.length;

        graph = new Graph(V, E, links);

        if (new UnionFindtoDetectCycle().isCycle(graph) == 1) {
            System.out.println( "graph contains cycle" );
        } else {
            System.out.println( "graph doesn't contain cycle" );
        }
    }
}