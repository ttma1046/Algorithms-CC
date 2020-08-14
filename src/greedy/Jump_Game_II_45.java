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
*/
You can assume that you can always reach the last index.

class Jump_Game_II {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int lastPos = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }

        return lastPos == 0;
    }


    public int jump(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int minSteps = 0;

        for(int i = 0; i < nums.length; i++) {
            currentSteps++;
            minSteps = Math.min(minSteps, currentSteps);
        }
        return minSteps
    }
}