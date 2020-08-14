package dp;

class Climbing_Stairs_70 {
    /*
        You are climbing a stair case. It takes n steps to reach to the top.

        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

        Example 1:

        Input: 2
        Output: 2
        Explanation: There are two ways to climb to the top.
        1. 1 step + 1 step
        2. 2 steps
        Example 2:

        Input: 3
        Output: 3
        Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step
        2. 1 step + 2 steps
        3. 2 steps + 1 step


        Constraints:

        1 <= n <= 45
    */
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }

        int first = 1;
        int second = 1;

        for (int i = 2; i < n + 1; i++) {
            second = second - first;
            first = first + second;
            second = 2 * first - second;
        }

        return second;
    }

    public int climbStairsI(int n) {
        return climb_StairsI(0, n);
    }

    public int climb_StairsI(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_StairsI(i + 1, n) + climb_StairsI(i + 2, n);
    }

    public int climbStairsII(int n) {
        int[] memo = new int[n];
        return climb_StairsII(0, n, memo);
    }

    public int climb_StairsII(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }

        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_StairsI(i + 1, n) + climb_StairsI(i + 2, n);
        return memo[i];
    }


    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = first;
            first = second;
            second = temp + first;
        }

        return second;
    }
}