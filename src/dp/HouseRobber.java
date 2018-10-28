package dp;

import java.io.Console;

public class HouseRobber {
    public static int rob(int[] nums) {
        int length = nums.length;

        if (length <= 0) {
            return 0;
        }

        if (length == 1) {
            return nums[0];
        }

        int[] memo = new int[length + 1];

        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 2; i <= length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i - 1]);
        }

        return memo[length];
    }

    public static int robII(int[] nums) {
        if (nums.length == 0) return 0;
        int previousMax1 = 0;
        int previousMax2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int tmp = previousMax1;
            previousMax1 = Math.max(previousMax2 + nums[i], tmp);
            previousMax2 = tmp;
        }
        return previousMax1;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    }
}
