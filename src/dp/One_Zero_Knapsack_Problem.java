package greedy;
class One_Zero_Knapsack_Problem {
	private int Knapsack(int maxWeight, int [] weightArray, int [] valueArray, int length) {
		// Base Case
		if (length == 0 || maxWeight == 0)
			return 0;

		// If weight of the nth item is
		// more than Knapsack capacity W,
		// then this item cannot be included
		// in the optimal solution
		if (weightArray[length - 1] > maxWeight) {
			return Knapsack(maxWeight, weightArray, valueArray, length - 1);
		}

		// Return the maximum of two cases:
		// (1) nth item included
		// (2) not included
		else {
			return max(
			           valueArray[length - 1] + Knapsack(maxWeight - weightArray[length - 1], weightArray, valueArray, length - 1),
			           Knapsack(maxWeight, weightArray, valueArray, length - 1));
		}
	}

	int max(int a, int b) {
		return a > b ? a : b;
	}

	public static void main(String args[]) {
		int val[] = new int[] { 60, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int W = 50;
		int n = val.length;
		System.out.println(new Knapsack_Problem().Knapsack(W, wt, val, n));
	}

	static int knapSack(int maxWeight, int [] weightArray, int [] valueArray, int length) {
		int i, w;
		int K[][] = new int[length + 1][maxWeight + 1];

		// Build table K[][] in bottom up manner
		for (i = 0; i <= length; i++) {
			for (w = 0; w <= maxWeight; w++) {
				if (i == 0 || w == 0)
					K[i][w] = 0;
				else if (weightArray[i - 1] <= w)
					K[i][w] = max(
					              valueArray[i - 1] + K[i - 1][w - weightArray[i - 1]],
					              K[i - 1][w]);
				else
					K[i][w] = K[i - 1][w];
			}
		}

		return K[length][maxWeight];
	}

}