package hashtable;
import java.util.HashMap;
/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[['5','3','.",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.

*/

class Valid_Sudoku_36 {


	public boolean isValidSudoku(char[][] board) {

		int l = board.length;
		HashMap<Integer, Integer>[] rows = new HashMap()[l];
		HashMap<Integer, Integer>[] columns = new HashMap()[l];
		HashMap<Integer, Integer>[] boxes = new HashMap()[l];

		for (int i = 0; i < l; i++) {
			rows[i] = new HashMap<>();
			columns[i] = new HashMap<>();
			boxes[i] = new HashMap<>();
		}


		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int n = Integer.valueOf(board[i][j]);
					int box_index = i / 3 * 3 + j / 3;
					if (rows[i].containsKey(n) || columns[j].containsKey(n) || boxes[box_index].containsKey(n)) return false;

					rows[i].put(n, 1);
					columns[j].put(n, 1);
					boxes[box_index].put(n, 1);
				}
			}
		}

		return true;
	}


	public boolean isValidSudoku(char[][] board) {
		HashMap<Integer, Integer>[] rows = new HashMap[9];
		HashMap<Integer, Integer>[] columns = new HashMap[9];
		HashMap<Integer, Integer>[] boxes = new HashMap[9];

		int l = board.length;

		for (int i = 0; i < l; i++) {
			rows[i] = new HashMap<>();
			columns[i] = new HashMap<>();
			boxes[i] = new HashMap<>();
		}

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				char num = board[i][j];

				if (num != '.') {
					int n = Integer.valueOf(num);
					int box_index = (i / 3 ) * 3 + j / 3;

					// check if this value has been already seen before
					if (rows[i].containsKey(n) || columns[j].containsKey(n) || boxes[box_index].containsKey(n)) return false;

					// keep the current cell value
					rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
					columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
					boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		char[][] kk = new char[][] {
			{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
			, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
			, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
			, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
			, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
			, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
			, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
			, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
			, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};

		System.out.println(new Valid_Sudoku_36().isValidSudoku(kk));

		kk = new char[][] {
			{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
			, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
			, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
			, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
			, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
			, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
			, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
			, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
			, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};

		System.out.println(new Valid_Sudoku_36().isValidSudoku(kk));
	}


	public boolean isValidSudoku(char[][] board) {
		HashMap<Integer, Integer>[] rows = new HashMap[9];
		HashMap<Integer, Integer>[] columns = new HashMap[9];
		HashMap<Integer, Integer>[] boxes = new HashMap[9];

		int l = board.length;

		for (int i = 0; i < l; i++) {
			rows[i] = new HashMap<>();
			columns[i] = new HashMap<>();
			boxes[i] = new HashMap<>();
		}

		for (int i = 0; i < l; ++i) {
			for (int j = 0; j < l; ++j) {
				if (board[i][j] != '.') {
					int number = Integer.valueOf(board[i][j]);
					int boxIndex = i / 3 * 3 + j / 3;

					if (rows[i].containsKey(number) || columns[j].containsKey(number) || boxes[box_index].containsKey(number))  return false;

					rows[i].put(number, rows[i].getOrDefault(number, 0) + 1);
					columns[j].put(number, columns[j].getOrDefault(number, 0) + 1);
					boxes[box_index].put(number, boxes[box_index].getOrDefault(number, 0) + 1);
				}
			}
		}

		return true;
	}
}