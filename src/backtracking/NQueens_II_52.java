package backtracking;
/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:

Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
Constraints:

1 <= n <= 9
*/
class NQueens_II_52 {
    int count = 0;
    public int totalNQueensII(int n) {
        int[][] chess = new int[n][n];
        solutions(chess, 0, n);
        return count;
    }
    public void solutions(int[][] chess, int row, int n) {
        if(row == n) {
            count++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isValid(chess, row, i)) {
                chess[row][i] = 1;
                solutions(chess, row + 1, n);
                chess[row][i] = 0;
            }
        }
    }
    public boolean isValid(int[][] chess, int row, int col) {
        for(int i = row - 1, j = col; i >= 0; i--) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        for(int i = row - 1, j = col + 1; i >= 0 && j < chess[0].length; i--, j++) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private int res = 0;
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        dfs(board, 0);
        return res;
    }

    private void dfs(char[][] board, int colIndex) {
        if (colIndex == board.length) {
            res++;
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y) {
        // 因为col是从0开始加上去的，一定不会invalid重合
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < y; j++) // 只需要判断两个斜线和同行即可
                if (board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                    return false;

        return true;
    }

    public static void main(String[] args) {
        NQueens_II_52 obj = new NQueens_II_52();
        int n = 4;
        int res = obj.totalNQueens(n);
        System.out.println(res);
    }
}