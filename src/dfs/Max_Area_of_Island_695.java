package dfs;
/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:

Input: grid = [
	[0,0,1,0,0,0,0,1,0,0,0,0,0],
	[0,0,0,0,0,0,0,1,1,1,0,0,0],
	[0,1,1,0,1,0,0,0,0,0,0,0,0],
	[0,1,0,0,1,1,0,0,1,0,1,0,0],
	[0,1,0,0,1,1,0,0,1,1,1,0,0],
	[0,0,0,0,0,0,0,0,0,0,1,0,0],
	[0,0,0,0,0,0,0,1,1,1,0,0,0],
	[0,0,0,0,0,0,0,1,1,0,0,0,0]
	]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
*/
class Max_Area_of_Island_695 {
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

    /*
    Complexity Analysis:
    Time Complexity: O(R*C)O(R∗C), where RR is the number of rows in the given grid, and CC is the number of columns. We visit every square once.

    Space complexity: O(R*C)O(R∗C), the space used by seen to keep track of visited squares, and the space used by the call stack during our recursion.
    */

    public maxAreaOfIslandII(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] directions = new int[gird.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;

                    int area = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] {i, j});

                    while (stack.size() > 0) {
                        int[] node = stack.pop();
                        area++;

                        for (int[] direction : directions) {
                            int newI = node[0] + direction[0];
                            int newJ = node[1] + direction[1];

                            if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && grid[newI][newJ] == 1 && !visited[newI][newJ]) {
                                stack.push(new int[] {newI, newJ});
                                visited[newI][newJ] = true;
                            }
                        }
                    }
                }

                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    	Max_Area_of_Island_695 obj = new Max_Area_of_Island_695();
    }
}