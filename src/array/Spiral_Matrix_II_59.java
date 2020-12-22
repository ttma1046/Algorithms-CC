package array;
/*

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 20

*/
class Spiral_Matrix_II_59 {
	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		int curr = 1;
		int direction = 0;
		int index = 0;

		int[] directionRow = new int[] {0, 1, 0, -1};
		int[] directionColumn = new int[] {1, 0, -1, 0};
		int row = 0;
		int col = 0;

		while (curr <= n * n) {
			res[row][col] = curr++;

			int r = row + directionRow[direction];
			int c = col + directionColumn[direction];
			System.out.println("r:" + r);
			System.out.println("c:" + c);

			if (r < 0 || c < 0 || r >= n || c >= n || res[r][c] != 0) direction = (direction + 1) % 4;
			System.out.println("direction:" + direction);

			row += directionRow[direction];
			col += directionColumn[direction];

			System.out.println("row:" + row);
			System.out.println("col:" + col);

		}

		return res;

		/*
		while (cnt <= n * n) {
			res[row][col] = cnt++;
			int r = Math.floorMod(row + directionRow[direction], n);
			int c = Math.floorMod(col + directionColumn[direction], n);

			// change direction if next cell is non zero
			if (result[r][c] != 0) d = (d + 1) % 4;

			row += directionRow[direction];
			col += directionColumn[direction];
		}

		return result;
		*/

		/*
		int[][] result = new int[n][n];
		int cnt = 1;
		int[] directionRow = new int[] {0, 1, 0, -1};
		int[] directionColumn = new int[] {1, 0, -1, 0};
		int direction = 0;
		int row = 0;
		int col = 0;
		while (cnt <= n * n) {
			result[row][col] = cnt++;
			int r = Math.floorMod(row + directionRow[direction], n);
			int c = Math.floorMod(col + directionColumn[direction], n);

			// change direction if next cell is non zero
			if (result[r][c] != 0) direction = (direction + 1) % 4;

			row += directionRow[direction];
			col += directionColumn[direction];
		}
		return result;
		*/
	}

	public static void main(String[] args) {
		int [][] result = new Spiral_Matrix_II_59().generateMatrix(3);

		for (int[] t: result) {
			for (int w: t) {
				System.out.println(w);
			}
		}
	}
}