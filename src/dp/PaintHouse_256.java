package dp;

import java.util.ArrayList;

/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
*/
class PaintHouse_256 {
	public int minCostMy(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length == 0) {
			return 0;
		}
		int len = costs.length, red = costs[0][0], blue = costs[0][1], yellow = costs[0][2];

		for (int i = 1; i < len; ++i) {
			int old_red = red, old_blue = blue;
			red = costs[i][0] + Math.min(blue, yellow);
			blue = costs[i][1] + Math.min(old_red, yellow);
			yellow = costs[i][2] + Math.min(old_red, old_blue);
		}

		return Math.min(red, Math.min(blue, yellow));
	}


	public int minCostMy(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length == 0) {
			return 0;
		}

		int[] previousRow = costs[costs.length - 1];

		for (int i = costs.length - 2; i >= 0; i--) {
			int[] currentRow = costs[i].clone();

			currentRow[0] += Math.min(previousRow[1], previousRow[2]);
			currentRow[1] += Math.min(previousRow[0], previousRow[2]);
			currentRow[2] += Math.min(previousRow[0], previousRow[1]);

			previousRow = currentRow;
		}

		return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
	}

	private int[][] costs;
	private int[][] memo;

	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length == 0) {
			return 0;
		}

		this.costs = costs;
		this.memo = new int[costs.length][costs[0].length];

		return Math.min(Math.min(paintCost(0, 1), paintCost(0, 2)), paintCost(0, 0));
	}

	private int paintCost(int index, int color) {
		if (memo[index][color] > 0) {
			return memo[index][color];
		}

		int totalCost = costs[index][color];
		if (index == costs.length - 1) {

		} else if (color == 0) {
			totalCost += Math.min(paintCost(index + 1, 1), paintCost(index + 1, 2));
		} else if (color == 1) {
			totalCost += Math.min(paintCost(index + 1, 0), paintCost(index + 1, 2));
		} else {
			totalCost += Math.min(paintCost(index + 1, 0), paintCost(index + 1, 1));
		}

		memo[index][color] = totalCost;

		return memo[index][color];
	}

	public int minCostIV(int[][] costs) {

		if (costs.length == 0) return 0;

		int[] previousRow = costs[costs.length - 1];

		for (int n = costs.length - 2; n >= 0; n--) {

			int[] currentRow = costs[n].clone();
			// Total cost of painting the nth house red.
			currentRow[0] += Math.min(previousRow[1], previousRow[2]);
			// Total cost of painting the nth house green.
			currentRow[1] += Math.min(previousRow[0], previousRow[2]);
			// Total cost of painting the nth house blue.
			currentRow[2] += Math.min(previousRow[0], previousRow[1]);
			previousRow = currentRow;
		}

		return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
	}

	public static void main(String[] args) {
		System.out.println(new PaintHouse_256().minCost(new int[][] {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
	}
}