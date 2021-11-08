package dfs;
/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/
class Making_a_Large_Island_827 {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int res = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                res = Math.max(res, dfs(i, j, grid, visited));

        return res;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) return 0;

        visited[i][j] = true;

        return 1 + dfs(i + 1, j, grid, visited) + dfs(i - 1, j, grid, visited) + dfs(i, j + 1, grid, visited) + dfs(i, j - 1, grid, visited);
    }

    public int largestIsland(int[][] grid) {
        int max = 0, m = grid.length, n = grid[0].length;

        boolean hasZero = false; //To check if there is any zero in the grid
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    grid[i][j] = 1;
                    max = Math.max(max, dfs(i, j, grid, new boolean[m][n]));
                    if(max == m * n) return max;
                    grid[i][j] = 0;
                    hasZero = true;
                }
            }
        }

        return hasZero ? max : m * n;

        /*
        int max = -1, n = a.length, m = a[0].length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (a[i][j] == 0) {
                    a[i][j] = 1;
                    max = Math.max(max, dfs(i, j, a, new boolean[n][m]));
                    a[i][j] = 0;
                }

        return max == -1 ? n * m : max;
        */
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if(i < 0 || i >= grid.length || j < 0  || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) return 0;

        visited[i][j] = true;

        return 1 + dfs(i - 1, j, grid, visited) + dfs(i + 1, j, grid, visited) + dfs(i, j + 1, grid, visited) + dfs(i, j - 1, grid, visited);
    }

    int cu = 0;
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[] area = new int[n * n + 2];
        int in = 2;
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    cu = 0;
                    dfs(i, j, grid, in);
                    area[in] = cu;
                    res = Math.max(res, cu);
                    in++;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int up = i > 0 ? grid[i - 1][j] : 0;
                    int down = i < n - 1 ? grid[i + 1][j] : 0;
                    int left = j > 0 ? grid[i][j - 1] : 0;
                    int right = j < n - 1 ? grid[i][j + 1] : 0;
                    int c = 1;
                    c += area[up];
                    if(up != down) c += area[down];
                    if(left != up && left != down) c += area[left];
                    if(right != up && right != down && right != left) c += area[right];
                    res = Math.max(c, res);
                }
            }
        }
        
        return res;
    }

    public void dfs(int i, int j, int[][] grid, int c) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid.length || grid[i][j] != 1) return;
        
        grid[i][j] = c;
        cu++;
        dfs(i - 1, j, grid, c);
        dfs(i, j - 1, grid, c);
        dfs(i + 1, j, grid, c);
        dfs(i, j + 1, grid, c);
    }
}
