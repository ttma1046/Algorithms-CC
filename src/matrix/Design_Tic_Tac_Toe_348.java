package matrix;
/*
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.


Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3],
	[0, 0, 1],
	[0, 2, 2],
	[2, 2, 1],
	[1, 1, 2],
	[2, 0, 1],
	[1, 0, 2],
	[2, 1, 1]
]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|

Constraints:

2 <= n <= 100
player is 1 or 2.
0 <= row, col < n
(row, col) are unique for each different call to move.
At most n2 calls will be made to move.

Follow-up: Could you do better than O(n2) per move() operation?
*/
class TicTacToe {
    private int[][] board;
    private int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        this.n = n;
    }

    public int move(int row, int col, int player) {
        board[row][col] = player;
        // check if the player wins
        if ((checkRow(row, player)) ||
                (checkColumn(col, player)) ||
                (row == col && checkDiagonal(player)) ||
                (col == n - 1 - row && checkAntiDiagonal(player))) {
            return player;
        }
        // No one wins
        return 0;
    }

    private boolean checkRow(int row, int player) {
        for (int i = 0; i < this.n; ++i) {
            if (abc[row][i] != player)
                return false;
        }

        return true;
    }

    private boolean checkColumn(int column, int player) {
        for (int i = 0; i < this.n; ++i) {
            if (abc[i][column] != player)
                return false;
        }

        return true;
    }

    private boolean checkDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][row] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][n - 1 - row] != player) {
                return false;
            }
        }
        return true;
    }

    /*
    Complexity Analysis

    Time Complexity: O(n), as for every move we are iterating over n cells 4 times to check for each of the column,

    row, diagonal row, and anti-diagonal. This gives us time complexity of O(4 * n) which is equivalent to O(n).

    Space Complexity: O(n^2), as we are using 2-dimensional array board of size n * n.
    */
}

class TicTacToeII {
	int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;

    public TicTacToeII(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int currentPlayer = (player == 1) ? 1 : -1;
        // update currentPlayer in rows and cols arrays
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;
        // update diagonal
        if (row == col) {
            diagonal += currentPlayer;
        }
        //update anti diagonal
        if (col == (cols.length - row - 1)) {
            antiDiagonal += currentPlayer;
        }
        int n = rows.length;
        // check if the current player wins
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n) {
            return player;
        }
        // No one wins
        return 0;
    }
}

/*
Complexity Analysis

Let, n be the length of string s.

Time Complexity: O(1) because for every move, 

we mark a particular row, column, diagonal, and anti-diagonal in constant time.

Space Complexity: O(n) because we use arrays rows and cols of size n. 

The variables diagonal and antiDiagonal use constant extra space.
*/

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

public class Design_Tic_Tac_Toe_348 {
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(3);
    }
}