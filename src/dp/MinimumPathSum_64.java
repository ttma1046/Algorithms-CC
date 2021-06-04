package dp;
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:

Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
*/
public class MinimumPathSum_64 {
    public static void main(String[] args) {
        int result = new MinimumPathSum_64().minPathSum(new int[][] {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        });

        System.out.println(result);
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] += grid[i][j];
                if (i > 0 && j > 0) dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                else if (i > 0) dp[i][j] += dp[i - 1][j];
                else if (j > 0) dp[i][j] += dp[i][j - 1];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];

        // return minPathSum(grid.length - 1, grid[0].length - 1, grid);
        /* 
        int[][] dp = new int[grid.length][grid[0].length];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                dp[i][j] += grid[i][j];
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
        */
        /*

        class Solution {
        public:

            int minPathSum(vector<vector<int>> &grid) {
                if(!grid.size())return 0;
                const int rows=grid.size(),cols=grid[0].size();
                // r[i] == min path sum to previous row's column i.
                vector<int> r(cols,0);
                int i,j;
                r[0]=grid[0][0];
                for(j=1;j<cols;j++){
                    r[j]=grid[0][j]+r[j-1];
                }
                for(i=1;i<rows;i++){
                    r[0]+=grid[i][0];
                    for(j=1;j<cols;j++){
                        r[j]=min(r[j-1],r[j])+grid[i][j];
                    }
                }
                return r[cols-1];
            }
        };

        public int minPathSum(int[][] grid) {
            int w = grid[0].length;
            int[] dist = new int[w];
            for (int i = 1; i < w; i++) dist[i] = Integer.MAX_VALUE;
            for (int[] r : grid) {
                for (int i = 0; i < w; i++) {
                    if (i == 0) dist[i] += r[i];
                    else dist[i] = r[i] + Math.min(dist[i], dist[i - 1]);
                }
            }
            return dist[w - 1];
        }
        */
    }

    /*
    private int minPathSum(int i, int j, int[][] grid) {
        if (i > 0 && j > 0) {
            return Math.min(minPathSum(i - 1, j, grid), minPathSum(i, j - 1, grid)) + grid[i][j];
        } else if (i > 0) {
            return minPathSum(i - 1, j, grid) + grid[i][j];
        } else if (j > 0) {
            return minPathSum(i, j - 1, grid) + grid[i][j];
        } else if (i > -1 && j > -1) {
            return grid[i][j];
        }

        return 0;
    }
    */
}
