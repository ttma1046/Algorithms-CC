class Solution {
    public int countSquares(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int count = 0;

        for(int i = 0; i < n; i++) if(matrix[i][0] == 1) dp[i][0] = 1;
        for(int j = 0; j < m; j++) if(matrix[0][j] == 1) dp[0][j] = 1;

        // dp[i][j] will tell you the max side of the square with bottom right corner at (i, j).
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(matrix[i][j] == 1) {
                    int min = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    dp[i][j] = min;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) count += dp[i][j];
        }

        return count;
    }
}