
/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.

Example 1:

Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
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

        this.seen[row][col] = true;
        currentIsland.add(new int[] {row, col});
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

    public static void main(String[] args) {

    }
}