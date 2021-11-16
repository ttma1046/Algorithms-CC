
package dp;
/*
Given a 2D grid of 0s and 1s,

return the number of elements in the largest square subgrid that has all 1s on its border,

or 0 if such a subgrid doesn't exist in the grid.

Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9

Example 2:

Input: grid = [[1,1,0,0]]
Output: 1

Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
*/


class Cell {
    int ver;
    int hori;
}


class Largest_1_Bordered_Square_1139 {
    public int largest1BorderedSquare(int[][] grid) {
        Cell T[][] = new Cell[grid.length][grid[0].length];
        for(int i = 0; i < T.length; i++) {
            for(int j = 0; j < T[0].length; j++) {
                T[i][j] = new Cell();
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    if(i == 0 && j == 0) {
                        T[i][j].hori = 1;
                        T[i][j].ver = 1;
                    } else if(i == 0) {
                        T[i][j].hori = T[i][j - 1].hori + 1;
                        T[i][j].ver = 1;
                    } else if(j == 0) {
                        T[i][j].ver = T[i - 1][j].ver + 1;
                        T[i][j].hori = 1;
                    } else {
                        T[i][j].hori = T[i][j - 1].hori + 1;
                        T[i][j].ver = T[i - 1][j].ver + 1;
                    }
                }
            }
        }

        //start iterating from bottom right corner and find min of hori or ver at every cell.
        //If this is greater than 1 then see if you can find a number between this min and 1
        //such that on left's ver and top's hori is greater greater than or equal to k.
        int max = 0;
        for(int i = T.length - 1; i >= 0; i--) {
            for(int j = T[0].length - 1 ; j >= 0; j--) {
                int min = Math.min(T[i][j].ver, T[i][j].hori);
                int k = 0;
                for(k = min; k > 1; k--)
                    if(T[i][j - k + 1].ver >= k && T[i - k + 1][j].hori >= k) break;

                if(max < k) max = k;
            }
        }

        return max * max;
    }

    public int largest1BorderedSquareII(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] left = new int[m][n], top = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] == 1) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0  ? top[i - 1][j] + 1 : 1;
                }
            }
        }

        for (int l = Math.min(m, n); l > 0; --l)
            for (int i = 0; i < m - l + 1; ++i)
                for (int j = 0; j < n - l + 1; ++j)
                    if (top[i + l - 1][j] >= l && top[i + l - 1][j + l - 1] >= l && left[i][j + l - 1] >= l && left[i + l - 1][j + l - 1] >= l)
                        return l * l;
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
            { 0, 0, 0, 0, 1},
            { 1, 0, 1, 1, 1},
            { 1, 0, 1, 0, 1},
            { 1, 1, 1, 1, 1},
            { 0, 0, 1, 1, 1},
        };

        // int[][] grid = new int[][] {{1,1,0,0}};

        Largest_1_Bordered_Square_1139 obj = new Largest_1_Bordered_Square_1139();

        int max = obj.largest1BorderedSquare(grid);

        // max = obj.largest1BorderedSquareII(grid);


        // grid = new int[][] {{0,0,0,0}};

        // max = obj.largest1BorderedSquare(grid);

        System.out.println(max);
    }


    public int findSubSquare(char input[][]) {
        Cell T[][] = new Cell[input.length][input[0].length];
        for(int i = 0; i < T.length; i++) {
            for(int j = 0; j < T[0].length; j++) {
                T[i][j] = new Cell();
            }
        }

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                if(input[i][j] == 'X') {
                    if(i == 0 && j == 0) {
                        T[i][j].hori = 1;
                        T[i][j].ver = 1;
                    } else if(i == 0) {
                        T[i][j].hori = T[i][j - 1].hori + 1;
                        T[i][j].ver = 1;
                    } else if(j == 0) {
                        T[i][j].ver = T[i - 1][j].ver + 1;
                        T[i][j].hori = 1;
                    } else {
                        T[i][j].hori = T[i][j - 1].hori + 1;
                        T[i][j].ver = T[i - 1][j].ver + 1;
                    }
                }
            }
        }

        //start iterating from bottom right corner and find min of hori or ver at every cell.
        //If this is greater than 1 then see if you can find a number between this min and 1
        //such that on left's ver and top's hori is greater greater than or equal to k.
        int max = 1;
        for(int i = T.length - 1; i >= 0 ; i--) {
            for(int j = T[0].length - 1; j >= 0; j--) {
                if(T[i][j].ver == 0 || T[i][j].ver == 1 || T[i][j].hori == 1 ) continue;

                int min = Math.min(T[i][j].ver, T[i][j].hori);
                int k = 0;
                for(k = min; k > 1; k--)
                    if(T[i][j - k + 1].ver >= k && T[i - k + 1][j].hori >= k) break;

                if(max < k) max = k;
            }
        }

        return max * max;
    }
}