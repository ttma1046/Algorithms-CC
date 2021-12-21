package prefix_sum;
import java.util.Map;
import java.util.HashMap;
/*
Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example 1:

Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion.
*/

/*
class NumMatrix {
    int[][] m;
    public NumMatrix(int[][] matrix) {
        m = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++)
            for (int j = col1; j <= col2; j++)
                sum += this.m[i][j];

        return sum;
    }


    // Time complexity : O(mn) time per query.
    // Assume that m and n represents the number of rows and columns respectively,
    // each sumRegion query can go through at most m × n elements.

	// Space complexity : O(1). Note that data is a reference to matrix and is not a copy of it.
}
*/

class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}

class NumMatrix {
    int [][]matrix;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;

        for (int i = 0; i < matrix.length; i++) {
            int sum = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                sum += matrix[i][j];
                matrix[i][j] = sum;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            int sum = matrix[0][j];
            for (int i = 1; i < matrix.length; i++) {
                sum += matrix[i][j];
                matrix[i][j] = sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = matrix[row2][col2];

        if (row1 - 1 >= 0) sum -= matrix[row1 - 1][col2];
        
        if (col1 - 1 >= 0) sum -= matrix[row2][col1 - 1];
        
        if (row1 - 1 >= 0 && col1 - 1 >= 0) sum += matrix[row1 - 1][col1 - 1];

        return sum;
    }
}
class Range_Sum_Query_2D_304 {
    public static void main(String[] args) {
        int[][] numMatrix = new int[][] {
            {3, 0, 1, 4, 2 },

            {5, 6, 3, 2, 1 },

            {1, 2,  0, 1, 5 },

            {4, 1, 0, 1, 7 },

            {1, 0, 3, 0, 5 }
        };

        /*
        4,3

        2,1

        4,3 - 4,0 - 1 3

        row2, col2 - (row2, col1 - 1) - (row1 - 1, col2) + (row1 - 1, row2 - 1)
        */

        NumMatrix obj = new NumMatrix(numMatrix);

        System.out.println(obj.sumRegion(2, 1, 4, 3));
        System.out.println(obj.sumRegion(1, 1, 2, 2));
        System.out.println(obj.sumRegion(1, 2, 2, 4));
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */