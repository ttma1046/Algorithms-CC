 package greedy;
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Note:
You can assume that you can always reach the last index.
*/

class Jump_Game_II_45 {
    pulbic int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < nums.length; i++) 
            for (int j = 0; j < i; j++) 
                if (dp[j] != Integer.MAX_VALUE && j + nums[j] >= i && dp[j] + 1 < dp[i]) dp[i] = dp[j] + 1;            
        
        return dp[nums.length - 1];
    }

    public int jump(int[] nums) {
        int n = nums.length;

        int dp[] = new int[n + 1];

        Arrays.fill(dp, -1);

        int res = soln(nums, n, dp);

        if (res != Integer.MAX_VALUE) return res;
        else return -1;
    }

    public static int soln(int arr[], int n, int dp[]) {
        if (n == 1) return 0;

        if (dp[n] != -1) return dp[n];

        dp[n] = Integer.MAX_VALUE;

        for(int i = 0; i <= n - 2; i++) {
            if (i + arr[i] >= n - 1) {
                int subRes = soln(arr, i + 1, dp);

                if (subRes != Integer.MAX_VALUE) dp[n] = Math.min(dp[n], subRes + 1);
            }
        }

        return dp[n];
    }

    public int jump(int[] nums) {
        int jumps = 0;
        int farest = 0;
        int currentJumpEnd = 0;

        for (int i = 0; i < nums.length - 1; ++i) {
            if (i + nums[i] > farest) farest = i + nums[i];

            // fastest = Math.max(fastest, i + nums[i]);

            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        Jump_Game_II_45 obj = new Jump_Game_II_45();
        obj.jump(new int[] {2, 3, 1, 1, 4});
    }
}