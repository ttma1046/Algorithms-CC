package union_find;

public class WeightedQuickUnionUF {
    private int[] id; // parent link (site indexed)
    private int[] sz; // size of component for roots (site indexed)
    private int count; // number of components

    public WeightedQuickUnionUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    public int count()
    { return count; }

    public boolean connected(int p, int q)
    { return find(p) == find(q); }

    private int find(int p)
    { // Follow links to find a root.
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        
        // Make smaller root point to larger one.

        if (sz[i] < sz[j]) { 
            id[i] = j; 
            sz[j] += sz[i]; 
        } else { 
            id[j] = i; 
            sz[i] += sz[j]; 
        }

        

        count--;
    }
}

/*

        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1
4, 3        
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 1, 2, 4, 4, 5, 6, 7, 8, 9
        1, 1, 1, 1, 2, 1, 1, 1, 1, 1
3, 8   
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 1, 2, 4, 4, 5, 6, 7, 4, 9  
        1, 1, 1, 1, 3, 1, 1, 1, 1, 1
6, 5
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 1, 2, 4, 4, 6, 6, 7, 4, 9
        1, 1, 1, 1, 3, 1, 2, 1, 1, 1    
9, 4
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 1, 2, 4, 4, 6, 6, 7, 4, 4
        1, 1, 1, 1, 4, 1, 2, 1, 1, 1
2, 1
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 2, 2, 4, 4, 6, 6, 7, 4, 4
        1, 1, 2, 1, 4, 1, 2, 1, 1, 1
8, 9
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        0, 2, 2, 4, 4, 6, 6, 7, 4, 4
        1, 1, 2, 1, 4, 1, 2, 1, 1, 1
5, 0
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        6, 2, 2, 4, 4, 6, 6, 7, 4, 4
        1, 1, 2, 1, 4, 1, 3, 1, 1, 1
7, 2
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        6, 2, 2, 4, 4, 6, 6, 2, 4, 4
        1, 1, 3, 1, 4, 1, 3, 1, 1, 1
6, 1
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        ----------------------------
        6, 2, 6, 4, 4, 6, 6, 2, 4, 4
        1, 1, 3, 1, 4, 1, 6, 1, 1, 1
1, 0
6, 7
*/