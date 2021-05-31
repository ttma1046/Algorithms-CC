package greedy;
/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5

*/


class Jump_Game_55 {
    public boolean canJumpBackTracking(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    private boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);

        for (int i = furthestJump; i > position; i--) {
            if (canJumpFromPosition(i, nums)) {
                return true;
            }
        }

        return false;
    }

    enum Index {
        Good,
        Bad,
        Unknown
    }

    Index[] memo;

    public boolean canJumpwithMemo(int[] nums) {
        memo = new Index[nums.length];

        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.Unknown;
        }

        memo[nums.length - 1] = Index.Good;

        return canJumpFromPositionwithMemo(0, nums);
    }

    private boolean canJumpFromPositionwithMemo(int position, int[] nums) {
        if (memo[position] != Index.Unknown) {
            return memo[position] == Index.Good ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);

        for (int i = position + 1; i <= furthestJump; i++) {
            if (canJumpFromPositionwithMemo(i, nums)) {
                memo[position] = Index.Good;
                return true;
            }
        }

        memo[position] = Index.Bad;
        return false;
    }

    public boolean canJumpDpBottomTop(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.Unknown;
        }
        memo[memo.length - 1] = Index.Good;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.Good) {
                    memo[i] = Index.Good;
                    break;
                }
            }
        }

        return memo[0] == Index.Good;
    }

    public static void main(String[] args) {
        System.out.println(new Jump_Game_55().canJumpEasy(new int[] {3, 2, 1, 0, 4}));
        System.out.println(new Jump_Game_55().canJumpEasy(new int[] {2, 3, 1, 1, 4}));
    }

    private boolean canJumpGreedy(int nums[]) {
        if (nums == null || nums.length <= 0) {
            return false;
        }

        int lastPos = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }

        return lastPos == 0;
    }

    private boolean canJumpFromBottomtoTop(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return false;
        }

        memo = new Index[nums.length];

        for (int i = 0; i < nums.length; i++) {
            memo[i] = Index.Unknown;
        }

        memo[nums.length - 1] = Index.Good;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.Good) {
                    memo[i] = Index.Good;
                    break;
                }
            }
        }

        return memo[0] == Index.Good;
    }

    public boolean canJumpEasy(int[] nums)  {
        int i = 0;
        for (int reach = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(i + nums[i], reach);
        }

        return i == nums.length;
    }

}