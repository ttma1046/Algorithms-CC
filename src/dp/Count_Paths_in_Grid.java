package dp;

class Count_Paths_in_Grid {

	int countPaths(boolean[][] grid, int row, int col) {
		if (!validSquare(grid, row, col)) return 0;
		if (isAtEnd(grid, row, col)) return 1;
		return countPaths(grid, row + 1, col) + countPaths(grid, row, col + 1);
	}

	// O(2^(n^2))

	int countPathsII(boolean[][] grid, int row, int col, int[][] paths) {
		if (!validSquare(grid, row, col)) return 0;
		if (isAtEnd(grid, row, col)) return 1;

		if (paths[row][col] == 0) {
			paths[row][col] = countPathsII(grid, row + 1, col, paths) + countPathsII(grid, row, col + 1, paths);
		}

		return paths[row][col];
	}


	int countPathsIII(boolean[][] grid, int row, int col) {
		int[][] result = countPathsIII(grid);

		return result[row][col];
	}

	// Time: O(n^2)
	int[][] countPathsIII(boolean[][] grid) {
		int[][] paths = new int[8][8];

		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				if (i == 7 && j == 7) {
					paths[i][j] = 1;
				} else if (!validSquare(grid, i, j)) {
					paths[i][j] = 0;
				} else {
					paths[i][j] = (i + 1 > 7 ? 0 : paths[i + 1][j]) + (j + 1 > 7 ? 0 : paths[i][j + 1]);
				}
			}
		}

		return paths;
	}

	private boolean validSquare(boolean[][] grid, int row, int col) {
		return row < grid.length && col < grid[0].length && grid[row][col];
	}

	private boolean isAtEnd(boolean[][] grid, int row, int col) {
		return row == grid.length - 1 && col == grid[0].length - 1;
	}

	public static void main(String[] args) {
		boolean[][] grid = new boolean[][] {
			{ true, true, true, true, true, true, true, true },
			{ true, true, false, true, true, true, false, true },
			{ true, true, true, true, false, true, true, true },
			{ false, true, false, true, true, false, true, true },
			{ true, true, false, true, true, true, true, true },
			{ true, true, true, false, false, true, false, true },
			{ true, false, true, true, true, false, true, true },
			{ true, true, true, true, true, true, true, true }
		};

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(new Count_Paths_in_Grid().countPaths(grid, i, j));
				System.out.print(" ");
			}
			System.out.println();
		}

		int[][] paths = new int[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(new Count_Paths_in_Grid().countPathsII(grid, i, j, paths));
				System.out.print(" ");
			}
			System.out.println();
		}

		int[][] result = new Count_Paths_in_Grid().countPathsIII(grid);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(result[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	void pushZerosToEnd(int arr[]) {
		int count = 0;  // Count of non-zero elements
		int length = arr.length;

		// Traverse the array. If element encountered is non-
		// zero, then replace the element at index 'count'
		// with this element
		for (int i = 0; i < length; i++)  {
			if (arr[i] != 0) {
				arr[count++] = arr[i]; // here count is
				// incremented
			}
		}

		// Now all non-zero elements have been shifted to
		// front and  'count' is set as index of first 0.
		// Make all elements 0 from count to end.
		while (count < length) {
			arr[count++] = 0;
		}
	}

	void test() {
		int length = array.length;
		int countZero = 0;
		while (array[countZero] == 0) {
			countZero++;
		}

		for (int j = 0; j < length; j++) {
			if (j < length - countZero) {
				array[j] = array[j + countZero];
			} else {
				array[j] = 0;
			}
		}
	}
}