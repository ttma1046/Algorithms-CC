package dp;
import java.util.Arrays;
/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:

Input: nums = [1], target = 1
Output: 1

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/

class Target_Sum_494 {
    public int findTargetSumWays(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];
        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;

        for (int i = 0; i < nums.length; ++i) {
            for (int sum = -total; sum <= total; ++sum) {
                if (dp[i - 1][sum + total] > 0)
                dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
            }
        }

        return Math.abs(target) > total ? 0 : dp[nums.length - 1][target + total];
    }

    public static void main(String[] args) {
        Target_Sum_494 obj = new Target_Sum_494();

        int[] nums = new int[] {1, 1, 1, 1, 1};
        int target = 3;
        obj.findTargetSumWays(nums, target);
    }
}