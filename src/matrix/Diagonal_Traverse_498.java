package matrix;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]

Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105
*/
class Diagonal_Traverse_498 {
    public int[] findDiagonalOrderII(int[][] matrix) {
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) return new int[0];


        // Variables to track the size of the matrix
        int rows = matrix.length;
        int cols = matrix[0].length;

        // The two arrays as explained in the algorithm
        int[] result = new int[rows * cols];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<Integer>();

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int i = 0; i < rows + cols - 1; ++i) {
            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int r = i < cols ? 0 : i - cols + 1;
            int c = i < cols ? i : cols - 1;

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < rows && c > -1) {
                intermediate.add(matrix[r][c]);
                r++;
                c--;
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (i % 2 == 0) Collections.reverse(intermediate);

            for (int j = 0; j < intermediate.size(); j++) result[k++] = intermediate.get(j);
        }
        return result;
    }

    /*
    Complexity Analysis

    Time Complexity: O(N * M) considering the array has N rows and M columns.

    An important thing to remember is that for all the odd numbered diagonals,

    we will be processing the elements twice since we have to reverse the elements before adding to the result array.

    Additionally, to save space, we have to clear the intermediate array before we process a new diagonal.

    That operation also takes O(K) where K is the size of that array. So, we will be processing all the elements of the array at least twice.

    But, as far as the asymptotic complexity is concerned, it remains the same.

    Space Complexity: O(min(N, M)) since the extra space is occupied by the intermediate arrays we use for storing diagonal elements and the maximum it can occupy

    is the equal to the minimum of N and M. Remember, the diagonal can only extend till one of its indices goes out of scope.
    */

    /*
    public int[] findDiagonalOrder(int[][] matrix) {
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) return new int[0];
        
        
        // Variables to track the size of the matrix
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Incides that will help us progress through 
        // the matrix, one element at a time.
        int row = 0, col = 0;
        
        // As explained in the article, this is the variable
        // that helps us keep track of what direction we are
        // processing the current diaonal
        int direction = 1;
        
         // The final result array
        int[] result = new int[rows * cols];
        int r = 0;
        
        // The uber while loop which will help us iterate over all
        // the elements in the array.
        while (row < rows && col < cols) {
            
            // First and foremost, add the current element to 
            // the result matrix. 
            result[r++] = matrix[row][col];
            
            // colsove along in the current diagonal depending upon
            // the current direction.[i, j] -> [i - 1, j + 1] if 
            // going up and [i, j] -> [i + 1][j - 1] if going down.
            int new_row = row + (direction == 1 ? -1 : 1);
            int new_col = col + (direction == 1 ? 1 : -1);
            
            // Checking if the next element in the diagonal is within the
            // bounds of the matrix or not. If it's not within the bounds,
            // we have to find the next head. 
            if (new_row < 0 || new_row == rows || new_col < 0 || new_col == cols) {
                // If the current diagonal was going in the upwards
                // direction.
                if (direction == 1) {
                    // For an upwards going diagonal having [i, j] as its tail
                    // If [i, j + 1] is within bounds, then it becomes
                    // the next head. Otherwise, the element directly below
                    // i.e. the element [i + 1, j] becomes the next head
                    row += (col == cols - 1 ? 1 : 0) ;
                    col += (col < cols - 1 ? 1 : 0);
                } else {
                    // For a downwards going diagonal having [i, j] as its tail
                    // if [i + 1, j] is within bounds, then it becomes
                    // the next head. Otherwise, the element directly below
                    // i.e. the element [i, j + 1] becomes the next head
                    col += (row == rows - 1 ? 1 : 0);
                    row += (row < rows - 1 ? 1 : 0);
                }
                    
                // Flip the direction
                direction = 1 - direction;
            } else {
                row = new_row;
                col = new_col;
            }
        }
        return result;      
    }
    */

    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int rows = mat.length;
        int cols = mat[0].length;

        int row = 0, col = 0, direction = 1;

        int[] res = new int[rows * cols];
        int index = 0;

        while (row < rows && col < cols) {
            res[index++] = mat[row][col];

            int new_row = row + (direction == 1 ? -1 : 1);
            int new_col = col + (direction == 1 ? 1 : -1);

            if (new_row < 0 || new_row == rows || new_col < 0 || new_col == cols) {
                if (direction == 1) {
                    row += (col == cols - 1 ? 1 : 0);
                    col += (col < cols - 1 ? 1 : 0);
                } else {
                    col += (row == rows - 1 ? 1 : 0);
                    row += (row < rows - 1 ? 1 : 0);
                }

                direction = 1 - direction;
            } else {
                row = new_row;
                col = new_col;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Diagonal_Traverse_498 obj = new Diagonal_Traverse_498();
    }
}