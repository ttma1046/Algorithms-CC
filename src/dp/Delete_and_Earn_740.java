package dp;

/*
You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.



Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104
*/
class Delete_and_Earn_740 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        else if (nums.length == 1)
            return nums[0];

        int len = nums.length;
        int max = nums[0];
        for (int i = 0; i < len; ++i)
            max = Math.max(max, nums[i]);

        int[] all = new int[max + 1];
        for (int item : nums)
            all[item]++;

        int[] dp = new int[max + 1];
        dp[1] = all[1] * 1;
        dp[2] = Math.max(dp[1], all[2] * 2);

        for (int i = 2; i <= max; ++i)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * all[i]);

        return dp[max];
    }

    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10002];

        for(int i = 0; i < nums.length; i++)
            sum[nums[i]] += nums[i];

        for(int i = 2; i < sum.length; i++)
            sum[i] = Math.max(sum[i - 1], sum[i - 2] + sum[i]);

        return sum[10001];
    }

    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int i = 0; i < nums.length; i+)
            values[nums[i]] += num[i];

        int take = 0, skip = 0;
        for (int i = 0; i < n; i++) {
        	int temp = take;
            take = skip + values[i];
            skip = Math.max(skip, temp);
        }

        return Math.max(take, skip);
    }
}