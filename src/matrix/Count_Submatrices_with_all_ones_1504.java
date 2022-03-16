package matrix;

class Count_Submatrices_With_All_Ones_1504 {
    public int numSubmat(int[][] mat) {
        int submatrices = 0;
        int rowLength = mat.length;
        int colLength = mat[0].length;

        for (int row = 1; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                mat[row][col] = mat[row][col] == 1 ? mat[row - 1][col] + 1 : 0;
            }
        }

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                if (mat[row][col] != 0) {
                    int min = mat[row][col];
                    submatrices += min;
                    for (int k = col + 1; k < colLength && mat[row][k] != 0; k++) {
                        min = Math.min (min, mat[row][k]);
                        submatrices += min;
                    }
                }
            }
        }

        return submatrices;
    }
}