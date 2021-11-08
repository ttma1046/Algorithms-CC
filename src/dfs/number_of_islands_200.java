package dfs;

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/
class number_of_islands_200 {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }

        int rowLength = grid.length;
        int columnLength = grid[0].length;

        boolean[][] visited = new boolean[rowLength][columnLength];
        int result = 0;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    result++;
                    countIslands(grid, i, j, rowLength, columnLength, visited);
                }
            }
        }

        return result;
    }

    private void countIslands(char[][] grid, int i, int j, int rowLength, int columnLength, boolean[][] visited) {
        if (i > rowLength - 1 || j > columnLength - 1 || i < 0 || j < 0) {
            return;
        }

        if (grid[i][j] == '1' && visited[i][j] == false) {
            visited[i][j] = true;
            countIslands(grid, i + 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j + 1, rowLength, columnLength, visited);
            countIslands(grid, i - 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j - 1, rowLength, columnLength, visited);
        }
    }

    public static void main(String[] args) {
        char[][] ocean = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
            { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' }
        };
        System.out.println(new number_of_islands_200().numIslands(ocean));
        ocean = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' }
        };
        System.out.println(new number_of_islands_200().numIslands(ocean));
    }

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

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }


    /*
    class Node {
        int value;
        List<Node> children;
        boolean visited;
    }
    */
    private boolean[][] visited;
    private int numRow;
    private int numCol;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }

        int total = 0;
        numRow = grid.length;
        numCol = grid[0].length;

        visited = new boolean[numRow][numCol];

        for (int i = 0; i < numRow; ++i) {
            for (int j = 0; j < numCol; ++j) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    ++total;

                    dfs(grid, i, j);
                }
            }
        }

        return total;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= numRow || j < 0 || j >= numCol || grid[i][j] == '0' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}