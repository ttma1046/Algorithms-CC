package hashtable;

/*

Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |


Constraints:

1 <= A.length, B.length <= 100
1 <= A[i].length, B[i].length <= 100
-100 <= A[i][j], B[i][j] <= 100
*/


class Sparse_Matrix_Multiplication_311 {
    public int[][] multiply(int[][] A, int[][] B) {
        /*
        A[i][k] * B[k][j] = C[i][j];

        A[0][0] * B[0][0] = c[0][0]           7
        A[0][1] * B[1][0] = c[0][0]
        A[0][2] * B[2][0] = c[0][0]

        A[0][0] * B[0][1] = c[0][1]
        A[0][1] * B[1][1] = c[0][1]           0
        A[0][2] * B[2][1] = c[0][1]

        A[0][0] * B[0][2] = c[0][2]
        A[0][1] * B[1][2] = c[0][2]           0
        A[0][2] * B[2][2] = c[0][2]


        A[1][0] * B[0][0] = c[1][0]
        A[1][1] * B[1][0] = c[1][0]           -7
        A[1][2] * B[2][0] = c[1][0]

        A[1][0] * B[0][1] = c[1][1]
        A[1][1] * B[1][1] = c[1][1]           0
        A[1][2] * B[2][1] = c[1][1]

        A[1][0] * B[0][2] = c[1][2]
        A[1][1] * B[1][2] = c[1][2]             3
        A[1][2] * B[2][2] = c[1][2]
        */

        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        // int[][] c = new int[A.length][B[0].length];

        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }

        return C;
    }

    public static void main(String[] args) {
        int[][] A = new int[][] {{1, 0, 0}, { -1, 0, 3}};
        int[][] B = new int[][] {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        int[][] result = new Sparse_Matrix_Multiplication_311().multiply(A, B);
        for (int[] a : result) {
            for (int b : a) {
                System.out.println(b);
            }
        }
    }
}