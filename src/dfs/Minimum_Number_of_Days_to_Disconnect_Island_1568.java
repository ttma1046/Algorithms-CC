package dfs;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
/*
Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

 

Example 1:



Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:

Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
Example 3:

Input: grid = [[1,0,1,0]]
Output: 0
Example 4:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,0,1,1]]
Output: 1
Example 5:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[i].length <= 30
grid[i][j] is 0 or 1.
*/
class Minimum_Number_of_Days_to_Disconnect_Island_1568 {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> timeMap = new HashMap<>();
    boolean foundCrutucalEdge;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int root = -1, time = 0, count = 0;

    public int minDays(int[][] grid) {
        if (noOfIsland(grid) != 1) return 0;

        if (count == 1) return 1;
        if (count == 2) return 2;
        buildGraph(grid);
        tarjan(-1, root, 0, new HashSet<>());
        return foundCrutucalEdge ? 1 : 2;
    }

    private void tarjan(int parent, int curr, int time, Set<Integer> visited) {
        visited.add(curr);
        timeMap.put(curr, time);

        for (int nei: graph.get(curr)) {
            if (nei == parent) continue;
            if (!visited.contains(nei)) tarjan(curr, nei, time + 1, visited);
            if (time < timeMap.get(nei)) foundCrutucalEdge = true;
            timeMap.put(curr, Math.min(timeMap.get(curr), timeMap.get(nei)));
        }
    }

    public void buildGraph(int[][] grid) {
        for (int i = 0; i < grid.length; ++i) 
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 1)
                    for (int[] dir: dirs)
                        mark(grid, i, j, i + dir[0], j + dir[1]);
    }

    public void mark(int[][] grid, int prevX, int prevY, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) return;

        int n1 = prevX * grid[0].length + prevY;
        int n2 = x * grid[0].length + y;

        graph.computeIfAbsent(n1, value -> new ArrayList<>()).add(n2);
        graph.computeIfAbsent(n2, value -> new ArrayList<>()).add(n1);    
    }

    private int noOfIsland(int[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) 
            for (int j = 0; j < grid[0].length; ++j) 
                if (!visited[i][j] && grid[i][j] == 1) {
                    if (root == -1) root = i * grid[0].length + j;
                    res++;
                    dfs(visited, grid, i, j);
                }

        return res;
    }

    private void dfs(boolean[][] visited, int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == 0) return;
        count++;

        visited[i][j] = true;

        for (int[] dir: dirs)
            dfs(visited, grid, i + dir[0], j + dir[1]);
    }

    public static void main(String[] args)  {
        Minimum_Number_of_Days_to_Disconnect_Island_1568 obj = new Minimum_Number_of_Days_to_Disconnect_Island_1568();
    }
}

