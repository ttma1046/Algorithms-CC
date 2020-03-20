package bfs;

import java.util.LinkedList;

import java.util.Queue;

import stack.StacktoQueue.MyQueue;
import sun.jvm.hotspot.opto.RootNode;

/*
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/

class RottingOranges_994 {
    public int orangesRottingIII(int[][] grid) {
        if (grid == null || grid.length <= 0 || (grid.length > 0 && grid[0].length <= 0)) {
            return -1;
        }

        int[] directionRow = new int[] { -1, 0, 1, 0 };
        int[] directionColumn = new int[] { 0, -1, 0, 1 };

        int totalRows = grid.length;
        int totalColumns = grid[0].length;
        int freshOranges = 0;
        Queue<Integer> rottenOrangesQueue = new LinkedList<Integer>();

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                if (grid[i][j] == 2) {
                    int orangeCode = i * totalColumns + j;
                    rottenOrangesQueue.add(orangeCode);
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

        int count = 0;

        while (!rottenOrangesQueue.isEmpty()) {
            count++;

            int length = rottenOrangesQueue.size();
            for (int i = 0; i < length; i++) {
                int current = rottenOrangesQueue.poll();
                int currentRow = current / totalColumns;
                int currentColumn = current % totalColumns;

                for (int k = 0; k < 4; k++) {
                    int row = currentRow + directionRow[k];
                    int column = currentColumn + directionColumn[k];

                    if (row < 0 || row >= totalRows || column < 0 || column >= totalColumns || grid[row][column] == 0
                            || grid[row][column] == 2) {
                        continue;
                    }

                    if (grid[row][column] == 1) {
                        grid[row][column] = 2;
                        rottenOrangesQueue.add(row * totalColumns + column);
                        freshOranges--;
                    }

                }
            }
        }

        return freshOranges == 0 ? count - 1 : -1;
    }
}