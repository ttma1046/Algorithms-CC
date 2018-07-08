package dp;

public class EvilIslands {
    int countPaths(boolean[][] grid, int row, int col) {
        if (!validSquiare(grid, row, col)) return 0;
        if (isAtEnd(grid, row, col)) return 1;
        return countPaths(grid, row + 1, col) + countPaths(grid, row, col + 1);
    } // O(2^(M*N))

    int countPaths(boolean[][] grid, int row, int col, int[][] paths) {
        if (!validSquiare(grid, row, col)) return 0;
        if (isAtEnd(grid, row, col)) return 1;

        if (paths[row][col] == 0) {
            paths[row][col] = countPaths(grid, row + 1, col, paths) + countPaths(grid, row, col + 1);
        }
        return paths[row][col];
    } // O(M*N)

    boolean isAtEnd(boolean[][] grid, int row, int col) {
        return false;
    }

    boolean validSquiare(boolean[][] grid, int row, int col) {
        return false;
    }
}
