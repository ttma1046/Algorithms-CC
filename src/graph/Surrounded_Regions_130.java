package graph;

/*
Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.


Example 1:


Input: board = [
					["X","X","X","X"],
					["X","O","O","X"],
					["X","X","O","X"],
					["X","O","X","X"]
				]

Output: [
			["X","X","X","X"],
			["X","X","X","X"],
			["X","X","X","X"],
			["X","O","X","X"]
		]

Explanation: Surrounded regions should not be on the border,
	which means that any 'O' on the border of the board are not flipped to 'X'.
	Any 'O' that is not on the border and it is not connected to an 'O' on the border
	will be flipped to 'X'.
	Two cells are connected if they are adjacent cells connected horizontally or vertically.

Example 2:

Input: board = [["X"]]
Output: [["X"]]

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
*/

class Surrounded_Regions_130 {
	public void solveII(char[][] board) {
		if (board == null || board.length == 0) return;

		int rows = board.length;
		int cols = board[0].length;

		//check first and last col
		for (int i = 0; i < rows; i++) {
			if (board[i][0] == 'O') dfs(i, 1, board);
			if (board[i][cols - 1] == 'O') dfs(i, cols - 2, board);
		}

		// check first row and last row
		for (int j = 0; j < cols; j++) {
			if (board[0][j] == 'O') dfs(1, j, board);
			if (board[rows - 1][j] == 'O') dfs(rows - 2, j, board);
		}

		// flip O to X, '*' to 'O',
		// skip the boulders
		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				if (board[i][j] == '*') board[i][j] = 'O';
				else if (board[i][j] == 'O') board[i][j] = 'X';
			}
		}
	}

	public void dfs(int i, int j, char[][]board) {
		if (i <= 0 || j <= 0 || i >= board.length - 1 || j >= board[0].length - 1 || board[i][j] == 'X') return;

		if (board[i][j] == '*')	return;
		if (board[i][j] == 'O') board[i][j] = '*';


		dfs(i + 1, j, board);
		dfs(i - 1, j, board);
		dfs(i, j + 1, board);
		dfs(i, j - 1, board);
	}

/*
[
 ["O","X","X","O","X"]
 ["X","O","O","X","O"]
 ["X","O","X","O","X"]
 ["O","X","O","O","O"]
 ["X","X","O","X","O"]
]

[
 ["O","X","X","O","X"]
 ["X","X","X","X","O"]
 ["X","X","X","X","X"]
 ["O","X","O","O","O"]
 ["X","X","O","X","O"]
]

[
 ["O","X","X","O","X"]
 ["X","X","X","X","O"]
 ["X","X","X","O","X"]
 ["O","X","O","O","O"]
 ["X","X","O","X","O"]
]
*/

	public void solveI(char[][] board) {
		int i = 0, j = 0;
		int rows = board.length;

		if (rows == 0 || board == null) return;

		int cols = board[0].length;

		if (cols == 0) return;

		for (i = 0; i < rows; i++) {
			check(board, i, 0, rows, cols);
			if (cols > 1) check(board, i, cols - 1, rows, cols);
		}

		for (j = 1; j + 1 < cols; j++) {
			check(board, 0, j, rows, cols);
			if (rows > 1) check(board, rows - 1, j, rows, cols);
		}

		for (i = 0; i < rows; i++)
			for (j = 0; j < cols; j++)
				if (board[i][j] == 'O') board[i][j] = 'X';

		for (i = 0; i < rows; i++)
			for (j = 0; j < cols; j++)
				if (board[i][j] == '1') board[i][j] = 'O';
	}

	void check(char[][] board, int i, int j, int totalRows, int totalCols) {
		if (board[i][j] == 'O') {
			board[i][j] = '1';
			if (i > 1)
				check(board, i - 1, j, totalRows, totalCols);
			if (j > 1)
				check(board, i, j - 1, totalRows, totalCols);
			if (i + 1 < totalRows)
				check(board, i + 1, j, totalRows, totalCols);
			if (j + 1 < totalCols)
				check(board, i, j + 1, totalRows, totalCols);
		}
	}

	public void solve(char[][] board) {
		boolean[][] visited = new boolean[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				dfs(i, j, visited, board);
			}
		}
	}

	private void dfs(int i, int j, boolean[][] visited, char[][] board) {
		if (!visited[i][j]) {
			if (board[i][j] == 'O') {
				if ((i != 0
				        && j != 0
				        && i != board.length - 1
				        && j != board[0].length - 1)
				        || !((i - 1 == 0 && board[i - 1][j] == 'O')
				             || (i + 1 == board.length - 1 && board[i + 1][j] == 'O')
				             || (j - 1 == 0 && board[i][j - 1] == 'O')
				             || (j + 1 == board[i].length - 1 && board[i][j + 1] == 'O'))
				   ) {

					board[i][j] = 'X';
					visited[i][j] = true;

					if (i + 1 < board.length) dfs(i + 1, j, visited, board);
					if (i - 1 >= 0) dfs(i - 1, j, visited, board);
					if (j + 1 < board[0].length) dfs(i, j + 1, visited, board);
					if (j - 1 >= 0) dfs(i, j - 1, visited, board);
				}
			}
		}
	}

	public static void main(String[] args) {
		Surrounded_Regions_130 obj = new Surrounded_Regions_130();

		char[][] input = new char[][] {
			{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'O', 'X', 'X'},
			{'X', 'O', 'X', 'X'}
		};

		obj.solve(input);

		for (char[] item : input) {
			for (char c : item) {
				System.out.print(c);
				System.out.print(',');
			}
			System.out.println();
		}
	}
}