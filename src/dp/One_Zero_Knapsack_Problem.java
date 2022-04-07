package dp;

/*
N items
total weigth = V

weight array = v[N]

value array = w[N]
*/
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
				else if (w >= weightArray[i - 1])
					K[i][w] = Math.max(
					              K[i - 1][w - weightArray[i - 1]] + valueArray[i - 1],
					              K[i - 1][w]);
				else
					K[i][w] = K[i - 1][w];
			}
		}

		return K[length][maxWeight];
	}

	knapSack() {
		if (j < weight[i]) {
			K[i][j] = K[i - 1][j];
		} else {
			K[i][j] = Math.max(value[i] + K[i - 1][j - weight[i]], K[i - 1][j]);
		}
	}

	public void Knapsack(int[] v, int[] w, int length, int maxWeight) {
		int[][] f = new int[length][maxWeight];
		for (int i = 1; i <= length; i++)
			for (int j = 0; j <= maxWeight; j++)
				f[i][j] = f[i - 1][j];
				if (j >= v[i]) f[i][j] = max(f[i][j], f[i - 1][j - v[i]] + w[i])

		return f[length - 1][maxWeight - 1];

		return 0;
	}


	public void Knapsack(int[] v, int[] w, int length, int maxWeight) {
		int[] f = new int[maxWeight];

		for (int i = 1; i <= length; i++)
			for (int j = maxWeight; j >= v[i]; j--)
				f[j] = Math.max(f[j], f[j - v[i]] + w[i]);

		return f[maxWeight];
	}
}