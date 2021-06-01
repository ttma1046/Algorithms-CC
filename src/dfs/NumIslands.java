package dfs;

import java.util.List;

public class NumIslands {
    class Node {
        int value;
        List<Node> children;
        boolean visited;
    }

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
