package dp;
/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.


Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].


Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/
class Partition_Equal_Subset_Sum_416 {
    HashMap<Integer, Boolean> memo = new HashMap<>();
    public boolean canPartitionI(int[] nums) {
        if (nums.length == 0) {
            return true;
        }

        int totalSum = 0;

        for (int i : nums) {
            totalSum += i;
        }

        if (totalSum % 2 == 0) return false;

        int subsetSum = totalSum / 2;

        int length = nums.length;

        return dfs(nums, subsetSum, length - 1);
    }

    private dfs(int[] nums, int subsetSum, int n) {
        if (subsetSum == 0) {
            return true;
        }

        if (n == 0 || subsetSum < 0) {
            return false;
        }

        if (memo.containsKey(subsetSum)) {
            return memo.get(subsetSum);
        }

        boolean result = dfs(nums, subsetSum - nums[n - 1], n - 1) || dfs(nums, subsetSum, n - 1);

        memo.put(subsetSum, result);

        return result;
    }

    public boolean canPartitionII(int[] nums) {
        int totalSum = 0;

        for (int i : nums) {
            totalSum += i;
        }

        if (totalSum % 2 != 0) return false;

        int subsetSum = totalSum / 2;

        int numsLength = nums.length;

        boolean [][] dp = new int[numsLength + 1][subsetSum + 1];

        dp[0][0] = true;

        for (int i = 1; i < numsLength + 1; i++) {
            int curr = nums[i - 1];

            for (int j = 0; j <= subsetSum; j++) {
                if (j < curr) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
                }
            }

        }

        return dp[numsLength][subsetSum];
    }

    public boolean canPartition(int[] nums) {
        if (nums.length == 0)
            return false;
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd,it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        int n = nums.length;
        boolean dp[] = new boolean[subSetSum + 1];
        dp[0] = true;
        for (int curr : nums) {
            for (int j = subSetSum; j >= curr; j--) {
                dp[j] |= dp[j - curr];
            }
        }
        return dp[subSetSum];
    }
}