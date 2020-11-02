package dp;

import java.util.Arrays;

class TripleStep_8point1 {
	int countWays(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
		}
	}

	int countWaysII(int n) {
		int[] memo = new int[n + 1];

		Arrays.fill(memo, -1);

		return countWaysII(n, memo);
	}

	int countWaysII(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		}
		{
			memo[n] = countWays(n - 1) + countWays(n - 2) + countWays(n - 3);

			return memo[n];
		}
	}

	int countWaysIII(int n) {
		int a = 1, b = 2, c = 4;
		
		if (n < 0) {
			return 0;
		}
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}

		if (n == 3) {
			return c;
		}

		for (int i = 4; i <= n; i++) {
			b = a + b;
			
			c = b + c;
			
			a = b - a;

			b = c - b;
		}

		return c;
	}

	public static void main(String[] args) {
		System.out.println(new TripleStep_8point1().countWaysIII(4));
	}
}


1 2 3 4 
1 2 4 7