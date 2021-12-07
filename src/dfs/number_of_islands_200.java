package dfs;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
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
class Number_of_Islands_200 {
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int res = 0;

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (grid[i][j] == '1') {
                    res++;
                    bfs(grid, i, j);
                }

        return res;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] { i, j });

        while(queue.size() > 0) {
            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];

            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') continue;

            grid[x][y] = '0';

            queue.offer(new int[] {x + 1, y});
            queue.offer(new int[] {x - 1, y});
            queue.offer(new int[] {x, y + 1});
            queue.offer(new int[] {x, y - 1});
        }
    }

    public int mynumIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int res = 0;

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (grid[i][j] == '1') {
                    res++;
                    dfsIter(grid, i, j);
                }

        return res;
    }

    private void dfsIter(char[][] grid, int i, int j) {
        Stack<int[]> stack = new Stack<>();

        stack.push(new int[] {i, j});

        while(stack.size() > 0) {
            int[] cur = stack.pop();

            int x = cur[0];
            int y = cur[1];

            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') continue;

            grid[x][y] = '0';

            stack.push(new int[] {x + 1, y});
            stack.push(new int[] {x - 1, y});
            stack.push(new int[] {x, y + 1});
            stack.push(new int[] {x, y - 1});
        }
    }

    public int numIslandsIIIII(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == '1') {
                    res++;
                    setAdjacent(i, j, grid);
                }
            }
        }
        return res;
    }

    private void setAdjacent(int row, int col, char[][] grid) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == '0')  return;
        
        grid[row][col] = '0';

        for (int i = row - 1; i <= row + 1; i++) 
            if (i != row) setAdjacent(i, col, grid);

        for (int j = col - 1; j <= col + 1; j++) 
            if (j != col) setAdjacent(row, j, grid);
    }

    public int numIslandsIIII(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res += 1;
                    dfs(grid, i, j, rows, cols, visited);
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int i, int j, int rows, int cols, boolean[][] visited) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || grid[i][j] == '0') return;

        visited[i][j] = true;
        dfs(grid, i + 1, j, rows, cols, visited);
        dfs(grid, i, j + 1, rows, cols, visited);
        dfs(grid, i - 1, j, rows, cols, visited);
        dfs(grid, i, j - 1, rows, cols, visited);
    }

    public static void main(String[] args) {
        Number_of_Islands_200 obj = new Number_of_Islands_200();
        char[][] ocean = new char[][] { 
            { '1', '1', '1', '1', '0' }, 
            { '1', '1', '0', '1', '0' },
            { '1', '1', '0', '0', '0' }, 
            { '0', '0', '0', '0', '0' }
        };
        System.out.println(obj.mynumIslands(ocean));
        ocean = new char[][] { 
            { '1', '1', '0', '0', '0' }, 
            { '1', '1', '0', '0', '0' }, 
            { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' }
        };
        System.out.println(obj.mynumIslands(ocean));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) return 0;


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
        if (i > rowLength - 1 || j > columnLength - 1 || i < 0 || j < 0) return;


        if (grid[i][j] == '1' && visited[i][j] == false) {
            visited[i][j] = true;
            countIslands(grid, i + 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j + 1, rowLength, columnLength, visited);
            countIslands(grid, i - 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j - 1, rowLength, columnLength, visited);
        }
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

    public int numIslandsII(char[][] grid) {
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

    public int numIslandsIII(char[][] grid) {
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
        if (i < 0 || i >= numRow || j < 0 || j >= numCol || grid[i][j] == '0' || visited[i][j]) return;


        visited[i][j] = true;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;

        DSU dsu = new DSU(rows * cols);

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (grid[i][j] == '1') {
                    res++;

                    for (int[] dir: dirs) {
                        int x = i + dir[0]; 
                        int y = j + dir[1];

                        if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == '1') {
                            if (dsu.find(x * rows + y) != dsu.find(i * cols + j)) res--;
                            dsu.union(x * rows + y, i * cols + j);
                        }
                    }
                }

        return res;
    }
}

class DSU {
    int[] parent;

    public DSU(int n) {
        parent = new int[n];
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