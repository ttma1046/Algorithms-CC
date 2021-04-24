package leetcode;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
*/

class Word_Search_79 {
	public boolean exist(char[][] board, String word) {
		int rows = board.length;
		int columns = board[0].length;
		char[] words = word.toCharArray();
		
		boolean[][] visited = new boolean[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (recur(i, j, 0, board, words, visited, rows, columns)) return true;
			}
		}

		return false;
	}

	private boolean recur(int i, int j, int k, char[][] board, char[] words, boolean[][] visited, int rows, int columns) {
		if (i >= 0 && i < rows && j >= 0 && j < columns && !visited[i][j] && board[i][j] == words[k]) {
			if (k == words.length - 1) return true;
			visited[i][j] = true;
			
			if (
				recur(i, j + 1, k + 1, board, words, visited, rows, columns) || 
				recur(i, j - 1, k + 1, board, words, visited, rows, columns) ||
				recur(i + 1, j, k + 1, board, words, visited, rows, columns) ||
				recur(i - 1, j, k + 1, board, words, visited, rows, columns)
			) return true;
			
			visited[i][j] = false;
		}
        
		return false;
	}

	public static void main(String[] args) {
		char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "ABCCED";

		System.out.println(new Word_Search_79().exist(board, word));


		board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		word = "SEE";

		System.out.println(new Word_Search_79().exist(board, word));

		word = "ABCB";
		System.out.println(new Word_Search_79().exist(board, word));

	}
}

