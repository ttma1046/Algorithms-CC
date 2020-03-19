package bfs;

import java.util.LinkedList;

import java.util.Queue;

class RottingOranges_994 {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length <= 0 || (grid.length > 0 && grid[0].length <= 0)) {
            return -1;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int minute = 0;

        Queue<Integer> myQueue = new LinkedList<Integer>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 2) {

                    myQueue.add(grid[i][j]);
                    while (!myQueue.isEmpty()) {

                        if (i + 1 < grid.length && grid[i + 1][j] == 1) {

                            // myQueue.add(grid[i + 1][j]);
                        }

                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            // myQueue.add(grid[i - 1][j]);
                        }

                        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                            // myQueue.add(grid[i][j + 1]);
                        }

                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            // myQueue.add(grid[i][j - 1]);
                        }

                        minute++;
                    }
                }
            }
        }

        return minute;
    }
}