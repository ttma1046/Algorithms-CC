package matrix;

/*
Given a 2D array of characters grid of size m x n,

you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell.

From a given cell, you can move to one of the cells adjacent to it -

in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move.

For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid

because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

Example 1:

Input: grid = [
    ["a","a","a","a"],
    ["a","b","b","a"],
    ["a","b","b","a"],
    ["a","a","a","a"]
    ]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:

Input: grid = [
    ["c","c","c","a"],
    ["c","d","c","c"],
    ["c","c","e","c"],
    ["f","c","c","c"]
]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:

Input: grid = [
    ["a","b","b"],
    ["b","z","b"],
    ["b","b","a"]
]
Output: false

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.
*/
class Detect_Cycles_in_2D_Grid_1559 {
    public boolean containsCycle(char[][] grid) {
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int m = grid.length;
        int n = grid[0].length;

        boolean hasCycle = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j])
                    hasCycle |= dfs(i, j, -1, -1, m, n, visited, grid, grid[i][j]);
            }
        }

        return hasCycle;
    }

    private boolean dfs(int i, int j, int oldI, int oldJ, int m, int n, boolean[][] visited, char[][] grid, char startChar) {
        visited[i][j] = true;
        boolean hasCycle = false;

        for (int d = 0; d < 4; ++d) {
            int newI = i + direction[d][0];
            int newJ = j + direction[d][0];

            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n) {
                if (!(newI == lastI && newJ == lastJ)) {
                    if (grid[newI][newJ] == startChar) {
                        if (visited[newI][newJ]) {
                            return true;
                        } else {
                            hasCycle |= dfs(newI, newJ, i, j, m, n, visited, grid, startChar);
                        }
                    }
                }
            }
        }

        return hasCycle;
    }
}