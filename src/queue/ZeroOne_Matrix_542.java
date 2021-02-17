package queue;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 

Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
*/

public class ZeroOne_Matrix_542 {
    public int[][] updateMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        Queue<int[]> queue = new LinkedList<int[]>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


        while(!queue.isEmpty()) {
            int[] item = queue.poll();

            for (int[] direction: directions) {
                int r = item[0] + direction[0];
                int c = item[1] + direction[1];

                if (r < 0 || r >= n || c < 0 || c >= m || matrix[r][c] <= matrix[item[0]][item[1]] + 1) {
                    continue;
                }

                queue.offer(new int[] {r, c});


                matrix[r][c] = matrix[item[0]][item[1]] + 1;
            }

        }

        return matrix;
    }


    public static void main(String[] args) {

    }
}


public class Solution {
     public int[][] updateMatrix(int[][] matrix) {
        // traverse all 1 cell to get the nearest 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0) {
                    search(matrix, i, j);
                }
            }
        }
        return matrix;
    }

    public void search(int[][] matrix, int x, int y) {
        int size = matrix.length + matrix[0].length - 1; // the max step

        // traverse all possible step in ascending order
        // if it encounters a '0' cell, the matrix stores the current step
        for (int step = 1; step < size; step++) {
            for (int i = 0; i <= step; i++) {
                if (isZero(matrix, x + i, y + step - i)  
                ||  isZero(matrix, x + i, y - step + i)  
                ||  isZero(matrix, x - i, y + step - i)  
                ||  isZero(matrix, x - i, y - step + i)) {
                    matrix[x][y] = step;
                    return;
                }
            }
        }
    }
     
    // check if the given x and y of matrix is '0'
    public boolean isZero(int[][] matrix, int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
            if (matrix[x][y] == 0) {
                return true;
            }
        }
        
        return false;
    }  
}