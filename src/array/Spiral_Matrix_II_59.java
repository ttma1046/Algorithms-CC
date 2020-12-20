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
		int[][] res = new int[3][3];
		int curr = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int[i][j] = curr++;
			}
		}


	}
}