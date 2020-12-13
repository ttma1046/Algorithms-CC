package leetcode;

class Kth_Smallest_Element_in_a_Sorted_Matrix_378 {
	public static void main(String[] args) {
		int [][] matrix = new int[][] {
			{1,  3,  7},
			{5, 10, 12},
			{6, 12, 15}
		};
		int k = 8;

		System.out.println(new Kth_Smallest_Element_in_a_Sorted_Matrix_378().kthSmallest(matrix, k));
	}

	public int kthSmallest(int[][] matrix, int k) {

		int n = matrix.length;
		int start = matrix[0][0], end = matrix[n - 1][n - 1];
		while (start < end) {

			int mid = start + (end - start) / 2;
			System.out.println("mid:" + mid);
			// first number is the smallest and the second number is the largest
			int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};

			int count = countLessEqual(matrix, mid, smallLargePair);
			System.out.println("count:" + count);

			System.out.println("smallLargePair[0]:" + smallLargePair[0]);
			System.out.println("smallLargePair[1]:" + smallLargePair[1]);

			if (count == k) return smallLargePair[0];

			if (count < k) start = smallLargePair[1]; // search higher
			else end = smallLargePair[0]; // search lower

			System.out.println("start:" + start);
			System.out.println("end:" + end);

		}
		return start;
	}

	private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
		int count = 0;
		int n = matrix.length, row = n - 1, col = 0;

		while (row >= 0 && col < n) {
			if (matrix[row][col] > mid) {

				// as matrix[row][col] is bigger than the mid, let's keep track of the
				// smallest number greater than the mid
				smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
				row--;
				System.out.println("row--:" + row);
			} else {
				// as matrix[row][col] is less than or equal to the mid, let's keep track of the
				// biggest number less than or equal to the mid
				smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
				count += row + 1;
				System.out.println("count+=:" + count);

				col++;
			}
		}

		return count;
	}
}