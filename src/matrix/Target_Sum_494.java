public class Solution {
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }


    /*
    Complexity Analysis

    Time complexity: O(2^n). Size of recursion tree will be 2^n
     . n refers to the size of nums array.

    Space complexity: O(n). The depth of the recursion tree can go up to n.
    */

    int total;

    public int findTargetSumWays(int[] sums, int target) {
        total = Arrays.stream(nums).sum();
        int[][] memo = new int[nums.length][2 * total + 1];

        for (int[] row : memo)
            Arrays.fill(row, Integer.MIN_VALUE);

        return calculate(nums, 0, 0, target, memo);
    }

    public int calculate(int[] nums, int start, int sum, int target, int[][] memo) {
        if (start == nums.length) {
            if (sum == target)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + total] != Integer.MIN_VALUE)
                return memo[i][sum + total];

            int add = calculate(nums, start + 1, sum + nums[start], target, memo);
            int sub = calculate(nums, start + 1, sum - nums[start], target, memo);
            memo[i][sum + total] = add + sub;
            return memo[i][sum + total];
        }
    }

    /*
    Complexity Analysis

    Time complexity: O(t * n). The memo array of size O(t * n) has been filled just once. Here, t refers to the sum of the nums array and n refers to the length of the nums array.

    Space complexity: O(t * n). The depth of recursion tree can go up to n. The memo array contains t * n elements.
    */

    public int findTargetSumWays(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];

        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; i++) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }


        return Math.abs(target) > total ? 0 : dp[nums.length - 1][target + total];
    }

    /*
    Time complexity: O(t * n). The dp array of size O(t * n) has been filled just once.
    Here, t refers to the sum of the nums array and n refers to the length of the nums array.

    Space complexity: O(t * n). dp array of size t * n is used.
    */

    public int findTargetSumWays(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[] dp = new int[2 * total + 1];
        dp[nums[0] + total] = 1;
        dp[-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2 * total + 1];
            for (int sum = -total; sum <= total; sum++) {
                if (dp[sum + total] > 0) {
                    next[sum + nums[i] + total] += dp[sum + total];
                    next[sum - nums[i] + total] += dp[sum + total];
                }
            }
            dp = next;
        }

        return Math.abs(S) > total ? 0 : dp[S + total];
    }
}