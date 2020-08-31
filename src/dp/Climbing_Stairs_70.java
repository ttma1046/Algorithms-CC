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

        Input: 4
        Output: 5
        Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step + 1 step
        2. 1 step + 2 steps + 1 step
        3. 2 steps + 1 step + 1 step
        4. 2 steps + 2steps
        5. 1 step + 1 step + 2 steps

        Constraints:

        1 <= n <= 45
    */
    public static void main(String[] args) {
        System.out.println(new Climbing_Stairs_70().climbStairs(0));

        System.out.println(new Climbing_Stairs_70().climbStairs(1));

        System.out.println(new Climbing_Stairs_70().climbStairs(2));

        System.out.println(new Climbing_Stairs_70().climbStairs(3));

        System.out.println(new Climbing_Stairs_70().climbStairs(4));

        System.out.println(new Climbing_Stairs_70().climbStairs(5));
    }

    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }   

        int[] memo = new int[n + 1];

        return climbStairsRec(n, memo);
    }

    private int climbStairsRec(int n, int[] memo) {
        if (n == 1 || n == 0) {
            return 1;
        }

        if (memo[n] > 0) {
            return memo[n];
        }

        memo[n] = climbStairsRec(n - 1, memo) + climbStairsRec(n - 2, memo);

        return memo[n];
    }

    private int climbStairsDP(int n) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n - 1];
    }


    private int climbStairsIter(int n) {
        if (n == 1) {
            return 1;
        }

        int first =  1;
        int second = 2;

        for (int i = 3; i < n; i++) {
            int temp = first;
            first = second;
            second = first + temp;
        }

        return second;
    }
}