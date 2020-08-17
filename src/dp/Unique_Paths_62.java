package dp;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

 

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
*/


class Unique_Paths_62 {
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[n][m];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (i == n - 1 && j == m - 1) {
					paths[i][j] = 1;
				} else {
					paths[i][j] = (i + 1 > n - 1 ? 0 : paths[i + 1][j]) + (j + 1 > m - 1 ? 0 : paths[i][j + 1]);
				}
			}
		}

		return paths[0][0];
    }

    public static void main(String[] args) {
    	System.out.println(new Unique_Paths_62().uniquePaths(7, 3));

    	System.out.println(new Unique_Paths_62().uniquePaths(3, 2));

    }
}