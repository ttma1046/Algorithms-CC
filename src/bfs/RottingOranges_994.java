package bfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class RottingOranges_994 {
    int[] dr = new int[] { -1, 0, 1, 0 };
    int[] dc = new int[] { 0, -1, 0, 1 };

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length <= 0 || (grid.length > 0 && grid[0].length <= 0)) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        Map<Integer, Integer> depth = new HashMap<Integer, Integer>();

        int R = grid.length, C = grid[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 2) {
                    int code = i * C + j;
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }

        for (int[] row : grid)
            for (int v : row)
                if (v == 1)
                    return -1;
        return ans;
    }

    public int orangesRottingII(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        // Put the position of all rotten oranges in queue
        // count the number of fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        // if count of fresh oranges is zero --> return 0
        if (count_fresh == 0)
            return 0;

        int count = 0;
        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        // bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            ++count;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int dir[] : dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    // if x or y is out of bound
                    // or the orange at (x , y) is already rotten
                    // or the cell at (x , y) is empty
                    // we do nothing
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2)
                        continue;
                    // mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    // put the new rotten orange at (x , y) in queue
                    queue.offer(new int[] { x, y });
                    // decrease the count of fresh oranges by 1
                    count_fresh--;
                }
            }
        }
        return count_fresh == 0 ? count - 1 : -1;
    }

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