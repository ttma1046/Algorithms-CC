Assume there are k cells containing gold. Use 0 ~ k - 1 to identify the gold cells; Since k <= 25, we can set 0th ~ (k - 1)th bit of an int to record the trace of the correspoinding cell in a path; e.g., in example 1,

[[0,6,0],
 [5,8,7],
 [0,9,0]]
the id of gold cell (0, 1) and (1, 1) are 0 and 2 respectively. The one cell trace of them are 1 (1 << 0) and 4 (1 << 2); The trace of of the path including only the two cells is 1 | 4 = 5, and the sum of the path is 6 + 8 = 14.

Loop through the grid, for each gold cell put into a Queue the coordinate (i, j), gold value grid[i][j], and the trace (1 << goldCellId), then perform BFS to get the max value;
For any gold cell neighbor (r, c) during BFS, ignore it if it is already in the trace ((trace & oneCellTrace[r][c]) != 0); otherwise, add its one cell trace to the current trace (trace | oneCellTrace[r][c]).

JAVA

 private static final int[] d = {0, 1, 0, -1, 0};
    public int getMaximumGold(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        int[][] oneCellTrace = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0, goldCellId = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    oneCellTrace[i][j] = 1 << goldCellId++;
                    q.offer(new int[]{i, j, grid[i][j], oneCellTrace[i][j]});
                }
            }
        }
        while (!q.isEmpty()) {
            int i = q.peek()[0], j = q.peek()[1], sum = q.peek()[2], trace = q.poll()[3];
            ans = Math.max(sum, ans);
            for (int k = 0; k < 4; ++k) {
                int r = i + d[k], c = j + d[k + 1];
                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] > 0 && (trace & oneCellTrace[r][c]) == 0) {
                    q.offer(new int[]{r, c, sum + grid[r][c], trace | oneCellTrace[r][c]});
                }
            }
        }
        return ans;
    }