package dp;
/*
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
*/

class Maximal_Square_221 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        
        int[][] dp = new int[rows + 1][cols + 1];

        int maxsqlen = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        
        return maxsqlen * maxsqlen;
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;

        int maxsqlen = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == '1') {
                    int sqlen = 1;

                    boolean flag = true;

                    while(i + sqlen < rows && j + sqlen < cols && flag) {
                        for (int k = j; k <= j + sqlen; ++k) {
                            if (maxsqlen[i + sqlen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        for (int k = i; k <= i + sqlen; ++k) {
                            if (maxsqlen[k][j + sqlen] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) sqlen++;
                    }
                }


                if (sqlen > maxsqlen) {
                    maxsqlen = sqlen;
                }
            }
        }


        return maxsqlen * maxsqlen;
    }

    public int maximalSquare(char[][] matrix) {

        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;

        int[][] dp = new int[rows + 1][cols + 1];

        int maxsqlen = 0;

        for (int i = 1; i < rows + 1; ++i) {
            for (int j = 1; j < rows + 1; ++j) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > maxsqlen) maxsqlen = dp[i][j];
                }
            }            
        }

        return maxsqlen * maxsqlen;
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, 
            cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        
        int maxsqlen = 0, prev = 0;
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }


    /*
    Complexity Analysis

    Time complexity : O(mn)O(mn). Single pass.

    Space complexity : O(mn)O(mn). Another matrix of same size is used for dp.
    */

    /*


                   0 0 0 0 0 0
    0 1 1 1 0      0 0 1 1 1 0
    1 1 1 1 0      0 1 1 2 2 0
    0 1 1 1 1      0 0 1 2 3 1
    0 1 1 1 1      0 0 1 2 3 2
    0 0 1 1 1      0 0 0 1 2 3
    */

}