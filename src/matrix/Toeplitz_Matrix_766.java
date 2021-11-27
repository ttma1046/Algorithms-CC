package matrix;
import java.util.Map;
import java.util.HashMap;

/*
Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.



Example 1:


Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
Example 2:


Input: matrix = [[1,2],[2,2]]
Output: false
Explanation:
The diagonal "[1, 2]" has different elements.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99


Follow up:

What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
What if the matrix is so large that you can only load up a partial row into the memory at once?
*/
class Toeplitz_Matrix_766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!map.containsKey(r - c))
                    map.put(r - c, matrix[r][c]);
                else if (map.get(r - c) != matrix[r][c]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m <= 1 || n <= 1) return true;

        for (int r = 1; r < m; r++) 
            for (int c = 1; c < n; c++) 
                if (matrix[r][c] != matrix[r - 1][c - 1]) return false;
            
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c])
                    return false;
        return true;
    }

    /*
    Time Complexity: O(M * N). (Recall in the problem statement that M, NM,N are the number of rows and columns in matrix.)

    Space Complexity: O(M + N).
    */

    public staic void main(String[] args) {
        Toeplitz_Matrix_766 obj = new Toeplitz_Matrix_766();
    }
}
