package matrix;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place,
which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:

Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

Example 3:

Input: matrix = [[1]]
Output: [[1]]

Example 4:

Input: matrix = [[1,2],[3,4]]
Output: [[3,1],[4,2]]

Constraints:

matrix.length == n
matrix[i].length == n
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/
class Rotate_Image_48 {
    public void rotateII(int[][] matrix) {
        reflectII(matrix);
        transposeII(matrix);
    }

    public void reflectII(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1;

        while(top < bottom) {
            int[] temp = matrix[top];
            matrix[top] = matrix[bottom];
            matrix[bottom] = temp;
            top++;
            bottom--;
        }
    }

    public void transposeII(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < i; ++j) {
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
    }


    public void rotateIII(int[][] matrix) {
        transposeIII(matrix);
        reflectIII(matrix);
    }

    public void transposeIII(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < i; ++j) {
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
    }

    public void reflectIII(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                matrix[i][n - 1 - j] = matrix[i][j] + matrix[i][n - 1 - j];
                matrix[i][j] = matrix[i][n - 1 - j] - matrix[i][j];
                matrix[i][n - 1 - j] = matrix[i][n - 1 - j] - matrix[i][j];
            }
        }
    }

    public void rotateIV(int[][] matrix) {
        int start = 0, end = matrix.length - 1;
        while(start < end) {
            int[] temp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = temp;
            start++;
            end--;
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < i; ++j) {
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
    }

    public void rotateOneGo(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
    
    /*
        Time complexity : O(M), as each cell is getting read once and written once.

        Space complexity : O(1) because we do not use any other additional data structures.
    */
    public void rotateNotGood(int[][] matrix) {
        transposeNotGood(matrix);
        reflectNotGood(matrix);
    }

    public void transposeNotGood(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    public void reflectNotGood(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    /*
    Let M be the number of cells in the grid.

    Time complexity : O(M). We perform two steps; transposing the matrix, and then reversing each row.
    Transposing the matrix has a cost of O(M) because we're moving the value of each cell once.
    Reversing each row also has a cost of O(M), because again we're moving the value of each cell once.

    Space complexity : O(1) because we do not use any other additional data structures.
    */

    public static void main(String[] args) {
        Rotate_Image_48 obj = new Rotate_Image_48();
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        obj.rotateOneGo(matrix);
        for (int[] k : matrix) {
            for (int i : k) {
                System.out.print(i);
            }
            System.out.println();
        }

    }
}