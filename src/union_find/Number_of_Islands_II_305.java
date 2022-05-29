package union_find;
import java.util.ArrayList;
import java.util.List;
/*
You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
Example 2:

Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]

Constraints:

1 <= m, n, positions.length <= 104
1 <= m * n <= 104
positions[i].length == 2
0 <= ri < m
0 <= ci < n

Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
*/
class UnionFind {
    int count; // # of connected components
    int[] parent;
    int[] rank;

    public UnionFind(char[][] grid) { // for problem 200
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * n + j] = i * n + j;
                    ++count;
                }
                rank[i * n + j] = 0;
            }
        }
    }

    public UnionFind(int N) { // for problem 305 and others
        count = 0;
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = -1;
            rank[i] = 0;
        }
    }

    public boolean isValid(int i) { // for problem 305
        return parent[i] >= 0;
    }

    public void setParent(int i) {
        parent[i] = i;
        ++count;
    }

    public int find(int i) { // path compression
        if (parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int x, int y) { // union with rank
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx] += 1;
            }
            --count;
        }
    }

    public int getCount() {
        return count;
    }
}

class DSU {
    int[] parent;
    public DSU (int n) {
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}

class Number_of_Islands_II_305 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);
        boolean[][] island = new boolean[m][n];

        List<Integer> res = new ArrayList<>();

        int count = 0;

        for (int[] position : positions) {
            if (island[position[0]][position[1]]) {
                res.add(count);
                continue;
            }

            island[position[0]][position[1]] = true;
            count++;

            for (int[] dir : dirs) {
                int newI = position[0] + dir[0], newJ = position[1] + dir[1];
                if (newI < 0 || newJ < 0 || newI >= m || newJ >= n || !island[newI][newJ]) continue;
                if (dsu.find(newI * n + newJ) != dsu.find(position[0] * n + position[1])) {
                    dsu.union(newI * n + newJ, position[0] * n + position[1]);
                    count--;
                }
            }

            res.add(count);
        }

        return res;
    }

    public static void main(String[] args) {
        Number_of_Islands_II_305 obj = new Number_of_Islands_II_305();
    }

    public List<Integer> numIslands(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            List<Integer> overlap = new ArrayList<>();

            if (r - 1 >= 0 && uf.isValid((r - 1) * n + c)) overlap.add((r - 1) * n + c);
            if (r + 1 < m && uf.isValid((r + 1) * n + c)) overlap.add((r + 1) * n + c);
            if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) overlap.add(r * n + c - 1);
            if (c + 1 < n && uf.isValid(r * n + c + 1)) overlap.add(r * n + c + 1);

            int index = r * n + c;
            uf.setParent(index);
            for (int i : overlap) uf.union(i, index);
            ans.add(uf.getCount());
        }

        return ans;
    }
}

class DSUbyRank {
    int[] parent;
    int[] rank;

    public DSUbyRank(int N) { // for problem 305 and others
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; ++i) parent[i] = i;
        Arrays.fill(rank, 1);
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rootx = find(x), rooty = find(y);
        if (rootx == rooty) return;
        if (rank[rootx] < rank[rootY]) parent[rootx] = rooty;
        else if (rank[rooty] < rank[rootx]) parent[rooty] = rootx;
        else {
            parent[rootx] = rooty;
            rank[rooty]++;
        }
    }
}

class DSUbySize {
    int[] parent;
    int[] size;

    public DSUbySize(int N) { // for problem 305 and others
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; ++i) parent[i] = i;
        Arrays.fill(size, 1);
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rootx = find(x), rooty = find(y);
        if (rootx == rooty) return;
        if (size[rootx] <= size[rootY]) {
            parent[rootx] = rooty;
            size[rooty] += size[rootx];
        } else if (size[rootx] > size[rooty]) {
            parent[rooty] = rootx;
            size[rootx] += size[rooty];
        }
    }
}
