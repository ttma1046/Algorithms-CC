package monotonicstack;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

/*
Given a rows x cols binary matrix filled with 0's and 1's,

find the largest rectangle containing only 1's and return its area.

Example 1:

Input: matrix =
    [
        ["1","0","1","0","0"],
        ["1","0","1","1","1"],
        ["1","1","1","1","1"],
        ["1","0","0","1","0"]
    ]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:

Input: matrix = [["0"]]
Output: 0

Example 3:

Input: matrix = [["1"]]
Output: 1

Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/
class Maximal_Retangle_85 {
    // Get the maximum area in a histogram given its heights
    public int process(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(-1);

        int maxarea = 0;

        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));

            stack.push(i);
        }

        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));

        return maxarea;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[] dp = new int[matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++)
                // update the state of this row's histogram using the last row's histogram
                // by keeping track of the number of consecutive ones
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;

            // update maxarea with the maximum area from this row's histogram
            maxarea = Math.max(maxarea, process(dp));
        }

        return maxarea;
    }

    public static void main(String[] args) {
        Maximal_Retangle_85 obj = new Maximal_Retangle_85();

        char[][] board = new char[][] {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

        obj.maximalRectangle(board);
    }

    public int maximalRectangleII(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxarea = 0;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        }

        return maxarea;
    }

    /*
    Complexity Analysis

    Time complexity : O(N^2M). Computing the maximum area for one point takes O(N) time, since it iterates over the values in the same column. This is done for all N * M points, giving O(N) * O(NM) = O(N^2M).

    Space complexity : O(NM). We allocate an equal sized array to store the maximum width at each point.
    */
}