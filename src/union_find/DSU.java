package union_find;

class DSU {
    int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}

class DSU {
    int[] parent;
    int[] size;

    public DSU(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++)	 parent[i] = i;
        Arrays.fill(size, 1);
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX == rootY) return;

        if (size[rootX] <= size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else if (size[rootX] > size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
        	parent[rootX] = rootY;
        	rank[rootY]++;
        }
    }
}