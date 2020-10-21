package leetcode;

import java.util.List;

/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

class Spiral_Matrix_54 {
	public List<Integer> spiralOrder(int[][] matrix) {
		List answer = new ArrayList();
		if (matrix.length == 0) return ans;
		int totalRows = matrix.length, totalColumns = matrix[0].length;

		boolean[][] visited = new boolean[totalRows][totalColumns];

		int[] directionRow = {0, 1, 0, -1};
		int[] directionColumn = {1, 0, -1, 0};
		int row = 0, column = 0, directionIndex = 0;

		for (int i = 0; i < totalRows * totalColumns; i++) {

			ans.add(matrix[row][column]);

			visited[row][column] = true;


			int nextRow = row + directionRow[directionIndex];
			int nextColumn = column + directionColumn[directionIndex];

			if (0 <= nextRow && nextRow < totalRows && 0 <= nextColumn && nextColumn < totalColumns && !visited[nextRow][nextColumn]) {
				row = nextRow;
				column = nextColumn;
			} else {
				directionIndex = (directionIndex + 1) % 4;
				row += directionRow[directionIndex];
				column += directionColumn[directionIndex];
			}
		}

		return answer;
	}

	public List < Integer > spiralOrder(int[][] matrix) {
		List answer = new ArrayList();
		if (matrix.length == 0) {
			return answer;
		}
		
		int rowStart = 0, rowEnd = matrix.length - 1;
		int columnStart = 0, columnEnd = matrix[0].length - 1;
		while (rowStart <= rowEnd && columnStart <= columnEnd) {
			for (int c = columnStart; c <= columnEnd; c++) { 
				answer.add(matrix[rowStart][c]); 
			}
			for (int r = rowStart + 1; r <= rowEnd; r++) { 
				answer.add(matrix[r][columnEnd]); 
			}

			if (rowStart < rowEnd && columnStart < columnEnd) {
				for (int c = columnEnd - 1; c > columnStart; c--) {
					answer.add(matrix[rowEnd][c]);
				}

				for (int r = rowEnd; r > rowStart; r--) { 
					answer.add(matrix[r][columnStart]);
				}
			}
			rowStart++;
			rowEnd--;
			columnStart++;
			columnEnd--;
		}
		return answer;
	}

	/*
	This is a very simple and easy to understand solution. 
	I traverse right and increment rowBegin, then traverse down and decrement colEnd, then I traverse left and decrement rowEnd, and finally I traverse up and increment colBegin.

	The only tricky part is that when I traverse left or up I have to check whether the row or col still exists to prevent duplicates. 
	
	If anyone can do the same thing without that check, please let me know!

	Any comments greatly appreciated.

	*/
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();

		if (matrix.length == 0) {
			return res;
		}

		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int colBegin = 0;
		int colEnd = matrix[0].length - 1;

		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// Traverse Right
			for (int j = colBegin; j <= colEnd; j++) {
				res.add(matrix[rowBegin][j]);
			}
			rowBegin++;

			// Traverse Down
			for (int j = rowBegin; j <= rowEnd; j++) {
				res.add(matrix[j][colEnd]);
			}
			colEnd--;

			if (rowBegin <= rowEnd) {
				// Traverse Left
				for (int j = colEnd; j >= colBegin; j --) {
					res.add(matrix[rowEnd][j]);
				}
			}
			rowEnd--;

			if (colBegin <= colEnd) {
				// Traver Up
				for (int j = rowEnd; j >= rowBegin; j --) {
					res.add(matrix[j][colBegin]);
				}
			}
			colBegin++;
		}

		return res;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0) return res;

		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;

		while (true) {
			for (int i = left; i <= right; i++) res.add(matrix[top][i]);
			top++;
			if (left > right || top > bottom) break;

			for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
			right--;
			if (left > right || top > bottom) break;

			for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
			bottom--;
			if (left > right || top > bottom) break;

			for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
			left++;
			if (left > right || top > bottom) break;
		}

		return res;
	}
}