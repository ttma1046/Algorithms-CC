package matrix;
/*
According to Wikipedia's article: "The Game of Life, also known simply as Life,

is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells,

where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).

Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)

using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The next state is created by applying the above rules simultaneously to every cell in the current state,

where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

Example 1:
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

Example 2:
Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.

Follow up:

Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
*/
class Game_of_Life_289 {
    public static void main(String[] args) {
        Game_of_Life_289 obj = new Game_of_Life_289();
    }

    public void gameOfLife(int[][] board) {
        int[] direction = new int[] { -1, 0, 1 };

        int rows = board.length;
        int cols = board[0].length;

        int[][] copyBoard = new int[rows][cols];

        for (int row = 0; row < rows; ++row)
            for (int col = 0; col < cols; ++col)
                copyBoard[row][col] = board[row][col];

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                int liveNeighbors = 0;
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (!(direction[i] == 0 && direction[j] == 0)) {
                            int r = row + direction[i];
                            int c = col + direction[j];

                            if (r >= 0 && r < rows && c < cols && c >= 0 && copyBoard[r][c] == 1)
                                liveNeighbors += 1;
                        }
                    }
                }


                if ((liveNeighbors < 2 || liveNeighbors > 3) && copyBoard[row][col] == 1)
                    board[row][col] = 0;

                if (liveNeighbors == 3 && copyBoard[row][col] == 0)
                    board[row][col] = 1;
            }
        }
    }
    /*
    Complexity Analysis
    Time Complexity: O(M×N), where M is the number of rows and N is the number of columns of the Board.

    Space Complexity: O(M×N), where M is the number of rows and N is the number of columns of the Board. This is the space occupied by the copy board we created initially.
    */
    public void gameOfLifeII(int[][] board) {
    	int[] directions = new int[] { -1, 0, 1 };
    	int rows = board.length;
    	int cols = board[0].length;

    	for (int i = 0; i < rows; ++i) {
    		for (int j = 0; j < cols; ++j) {
    			int liveNeighbors = 0;

    			for (int t = 0; t < 3; ++t) {
    				for (int s = 0; s < 3; ++s) {
    					if (!(directions[t] == 0 && directions[s] == 0)) {
    						int newI = i + directions[t];
    						int newJ = j + directions[s];

    						if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && Math.abs(board[newI][newJ]) == 1) 
    							liveNeighbors++;
    					}
    				}
    			}

    			if ((liveNeighbors < 2 || liveNeighbors > 3) && board[i][j] == 1)
    				board[i][j] = -1;

    			if (liveNeighbors == 3 && board[i][j] == 0)
    				board[i][j] = 2;
    		}
    	}

    	for (int i = 0; i < rows; ++i) {
    		for (int j = 0; j < cols; ++j) {
    			if (board[i][j] > 0) 
    				board[i][j] = 1;
    			else board[i][j] = 0;
    		}
 		}
    }
}