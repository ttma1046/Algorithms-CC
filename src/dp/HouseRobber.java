package dp;

import java.io.Console;

public class HouseRobber {
    public static int rob(int[] nums) {
        int length = nums.length;
        int[] memo = new int[length];
        if (length <= 0) {
            return 0;
        }

        if (length == 1) {
            return nums[0];
        }

        memo[0] = nums[0];
        memo[1] = Math.max(memo[0], nums[1]);
        for (int i = 2; i < length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i]);
        }

        return memo[length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    }
}
