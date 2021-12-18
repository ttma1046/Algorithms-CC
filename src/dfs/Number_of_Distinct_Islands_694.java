package dfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.

Example 1:

Input: grid = [
    [1,1,0,0,0],
    [1,1,0,0,0],
    [0,0,0,1,1],
    [0,0,0,1,1]]
Output: 1

Example 2:

Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
*/
class Number_of_Distinct_Islands_694 {
    /*
    private List<List<int[]>> uniqueIslands = new ArrayList<>(); // All known unique islands.
    private List<int[]> currentIsland = new ArrayList<>(); // Current Island

    private boolean[][] seen; // Cells that have been explored.

    public int numDistinctIslands(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        this.seen = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                dfs(row, col, grid);
                if (currentIsland.isEmpty()) continue;

                // Translate the island we just found to the top left.
                int minCol = cols - 1;

                for (int i = 0; i < currentIsland.size(); i++)
                    minCol = Math.min(minCol, currentIsland.get(i)[1]);

                for (int[] cell : currentIsland) {
                    cell[0] -= row;
                    cell[1] -= minCol;
                }

                // If this island is unique, add it to the list.
                if (currentIslandUnique()) uniqueIslands.add(currentIsland);

                currentIsland = new ArrayList<>();
            }
        }

        return uniqueIslands.size();
    }

    private void dfs(int row, int col, int[][] grid) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || this.seen[row][col] || grid[row][col] == 0) return;
    6com
        this.seen[row][col] = true;
        this.currentIsland.add(new int[] {row, col});
        dfs(row + 1, col, grid);
        dfs(row - 1, col, grid);
        dfs(row, col + 1, grid);
        dfs(row, col - 1, grid);
    }

    private boolean currentIslandUnique() {
        for (List<int[]> otherIsland : uniqueIslands) {
            if (currentIsland.size() != otherIsland.size()) continue;
            if (equalIslands(currentIsland, otherIsland)) {
                return false;
            }
        }
        return true;
    }

    private boolean equalIslands(List<int[]> island1, List<int[]> island2) {
        for (int i = 0; i < island1.size(); i++) {
            if (island1.get(i)[0] != island2.get(i)[0] || island1.get(i)[1] != island2.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
    */

    List<List<int[]>> uniqueIslands = new ArrayList<>();
    List<int[]> currentIsland = new ArrayList<>();
    boolean[][] visited;

    public int numDistinctIslandsI(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        this.visited = new boolean[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                dfs(i, j, grid);

                if (this.currentIsland.size() == 0) continue;

                int minCol = cols - 1;

                for (int t = 0; t < this.currentIsland.size(); ++t)
                    minCol = Math.min(minCol, this.currentIsland.get(t)[1]);

                for (int[] cell : this.currentIsland) {
                    cell[0] -= i;
                    cell[1] -= minCol;
                }

                if (isIsandUnique()) this.uniqueIslands.add(currentIsland);
                this.currentIsland = new ArrayList<>();
            }
        }

        return this.uniqueIslands.size();
    }

    private void dfs(int i, int j, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || j < 0 || i > rows - 1 || j > cols - 1 || grid[i][j] == 0 || this.visited[i][j]) return;

        this.visited[i][j] = true;
        this.currentIsland.add(new int[] {i, j});
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }

    private boolean isIsandUnique() {
        for (List<int[]> otherIsland : this.uniqueIslands) {
            if (otherIsland.size() != this.currentIsland.size()) continue;
            if (isEqualIsland(this.currentIsland, otherIsland)) return false;
        }

        return true;
    }

    private boolean isEqualIsland(List<int[]> currIsland, List<int[]> otherIsland) {
        for (int i = 0; i < currIsland.size(); ++i)
            if (currIsland.get(i)[0] != otherIsland.get(i)[0] || currIsland.get(i)[1] != otherIsland.get(i)[1]) return false;

        return true;
    }

    /*
    private boolean[][] visited;

    public int numDistinctIslandsIIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        this.visited = new boolean[rows][cols];

        Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                HashSet<Pair<Integer, Integer>> currentIsland = new HashSet<>();
                dfs(row, col, grid, currentIsland, row, col);
                if (currentIsland.size() > 0) islands.add(currentIsland);
            }
        }
        return islands.size();
    }

    private void dfs(int row, int col, int[][] grid, HashSet<Pair<Integer, Integer>> currentIsland, int currRowOrigin, int currColOrigin) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row > rows - 1 || col < 0 || col > cols - 1 || grid[row][col] == 0 || this.visited[row][col]) return;

        this.visited[row][col] = true;

        currentIsland.add(new Pair<>(row - currRowOrigin, col - currColOrigin));
        dfs(row + 1, col, grid, currentIsland, currRowOrigin, currColOrigin);
        dfs(row - 1, col, grid, currentIsland, currRowOrigin, currColOrigin);
        dfs(row, col + 1, grid, currentIsland, currRowOrigin, currColOrigin);
        dfs(row, col - 1, grid, currentIsland, currRowOrigin, currColOrigin);
    }
    */

    /*
    private StringBuilder currentIsland;

    public int numDistinctIslandsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Set<String> islands = new HashSet<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                currentIsland = new StringBuilder();
                dfs(row, col, '0', grid, visited);
                if (currentIsland.length() == 0) continue;
                islands.add(currentIsland.toString());
            }
        }

        return islands.size();
    }

    private void dfs(int row, int col, char dir, int[][] grid, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col] || grid[row][col] == 0) return;

        visited[row][col] = true;
        currentIsland.append(dir);
        dfs(row + 1, col, 'D', grid, visited);
        dfs(row - 1, col, 'U', grid, visited);
        dfs(row, col + 1, 'R', grid, visited);
        dfs(row, col - 1, 'L', grid, visited);
        currentIsland.append('0');
    }
    */

    public int numDistinctIslandsII(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "start#");
                    set.add(sb.toString());
                }

        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == 0) return;

        sb.append(dir);

        grid[i][j] = 0;

        dfs(grid, i - 1, j, sb, "u");
        dfs(grid, i + 1, j, sb, "d");
        dfs(grid, i, j - 1, sb, "l");
        dfs(grid, i, j + 1, sb, "r");

        sb.append("#end");
    }

    int[][] dirs = new int[][] {{ 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};

    public int numDistinctIslandsMy(int[][] grid) {
        Set<List<List<Integer>>> islands = new HashSet<>();

        int count = 0;

        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 1) {
                    List<List<Integer>> island = new ArrayList<>();
                    dfs(grid, i, j, i, j, island);
                    islands.add(island);
                }

        return islands.size();
    }

    public void dfs(int[][] grid, int i, int j, int i0, int j0, List<List<Integer>> island) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != 1) return;

        grid[i][j] = -1;

        List<Integer> currentIsland = new ArrayList<>();
        currentIsland.add(i - i0);
        currentIsland.add(j - j0);

        island.add(currentIsland);

        // for (int[] dir: dirs) dfs(grid, i + dir[0], j + dir[1], i0, j0, island);

        dfs(grid, i + 1, j, i0, j0, island);
        dfs(grid, i - 1, j, i0, j0, island);
        dfs(grid, i, j + 1, i0, j0, island);
        dfs(grid, i, j - 1, i0, j0, island);
    }

    public static void main(String[] args) {
        Number_of_Distinct_Islands_694 obj = new Number_of_Distinct_Islands_694();
    }
}