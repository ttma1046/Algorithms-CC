package dp;

public class HouseRobberII {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;

        if (length == 1) {
            return nums[0];
        }

        if (length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        int[] memo = new int[length - 1];

        memo[0] = nums[0];
        memo[1] = nums[0] > nums[1] ? nums[0] : nums[1];

        for (int i = 2; i < length - 1; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i]);
        }

        int[] memo2 = new int[length - 1];
        memo2[0] = nums[1];
        memo2[1] = nums[1] > nums[2] ? nums[1] : nums[2];

        for (int i = 2; i < length - 1; i++) {
            memo2[i] = Math.max(memo2[i - 1], memo2[i - 2] + nums[i + 1]);
        }

        return Math.max(memo[length - 2], memo2[length - 2]);
    }

    public static int robII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];

        int pre2 = 0, pre1 = nums[0];
        int tmp = pre1;
        for (int i = 1; i < nums.length - 1; i++) {
            tmp = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = tmp;
        }

        pre2 = 0;
        pre1 = 0;
        int tmp2 = 0;
        for (int i = 1; i < nums.length; i++) {
            tmp2 = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = tmp2;
        }
        return Math.max(tmp, tmp2);
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    }

}
