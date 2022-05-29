package union_find;

public class Graph {
    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge[] edges; // /collection of all edges

    // Creates a graph with V vertices and E edges
    Graph(int v, int e, int[][] links) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge(links[i][0], links[i][1]);
    }

    // A utility function to find the subset of an element i
    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;

        return find(parent, parent[i]);
    }

    // A utility function to do union of two subsets
    void Union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }
}