package union_find;

public class UnionFindByRank {

    // A utility function to find
    // set of an element i (uses
    // path compression technique)
    int find(Subset [] subsets , int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets,
                                     subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union
    // of two sets of x and y
    // (uses union by rank)
    void Union(Subset [] subsets,
               int x , int y ) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[yroot].rank < subsets[xroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    // The main function to check whether
    // a given graph contains cycle or not
    int isCycle(Graph graph) {
        int V = graph.V;
        int E = graph.E;

        Subset [] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {

            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        for (int e = 0; e < E; e++) {
            int x = find(subsets, graph.edges[e].src);
            int y = find(subsets, graph.edges[e].dest);
            if (x == y)
                return 1;
            Union(subsets, x, y);
        }
        return 0;
    }

    // Driver Code
    public static void main(String [] args) {
        /* Let us create the following graph
            0
            | \
            | \
            1-----2 */

        int V = 4, E = 3;
        Graph graph = new Graph(V, E, new int[][] {{ 0, 1 }, { 1, 2 }, { 3, 2 }});

        if (new UnionFindByRank().isCycle(graph) == 1) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }

        V = 3;
        E = 3;
        graph = new Graph(V, E, new int[][] {{ 0, 1 }, { 1, 2 }, { 0, 2 }});

        if (new UnionFindtoDetectCycle().isCycle(graph) == 1) {
            System.out.println( "graph contains cycle" );
        } else {
            System.out.println( "graph doesn't contain cycle" );
        }
    }
}