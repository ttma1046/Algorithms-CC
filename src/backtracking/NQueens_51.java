package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.
You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:

Input: n = 4
Output: [
  [
   ".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  [
   "..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
 ]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
*/

// 约束是编程

class NQueens_51 {
    public List<List<String>> solveNQueensII(int n) {
        char[][] cur = new char[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(cur[i], '.');
        }
        List<List<String>> ans = new ArrayList<>();
        // mask is to record the y coordinate whose x cooridinte x<current_d, d represent current row we are checking
        int[] mask = new int[n];
        Arrays.fill(mask, -1);
        helper(ans, cur, n, 0, mask);
        return ans;
    }
    
    public void helper(List<List<String>> ans, char[][] cur, int n, int d, int[] mask){
        if(d == n){
            List<String> res = new ArrayList<>();
            for(int i=0; i<cur.length; i++){
                res.add(new String(cur[i]));
            }
            ans.add(res);
            return;
        }
        
        for(int j=0; j<n; j++){
            if(cur[d][j] == '.'){
                if(isValid(d, j, n, cur, mask)){
                    cur[d][j] = 'Q';
                    mask[d] = j;
                    helper(ans, cur, n, d+1, mask);
                    mask[d] = -1;
                    cur[d][j] = '.';
                }
            }
        }
    }
    
    public boolean isValid(int row, int col, int n, char[][] cur, int[] mask){
        // 检查斜线，检查row之前的所有Queen，是不是在当前点的斜线上？
        for(int i=0; i<row; i++){
            if(mask[i] - i == col - row || mask[i] + i == col + row)
                return false;
        }

        for(int i=0; i<n; i++){
            if(cur[row][i] != '.') return false;
            if(cur[i][col] != '.') return false;
        }
        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
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

        /*
        c c c x x x x x
        c c c x x x x x
        c c c x x x x x
        c c c x x x x x
        c c c ? x x x x
        c c c x x x x x
        c c c x x x x x
        c c c x x x x x

        4  3

        [0, 7] [1, 6] [2, 5] [3, 4] [5, 2] [6, 1] [7, 0]
        [1, 0] [2, 1] [3, 2] [5, 4] [6, 5] [7, 6]
        */
    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++)
            res.add(new String(board[i]));
        return res;
    }

    public static void main(String[] args) {
        NQueens_51 obj = new NQueens_51();
        int n = 8;
        List<List<String>> res = obj.solveNQueens(n);

        for (List<String> item : res) {
          for (String s: item)  {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; ++i) {
              if (i % n == 0) System.out.println();
              System.out.print(c[i] + " ");            
            }
          }
          System.out.println();
        }
    }
}