package slidingwindow;
/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

Example 1:

Input: nums = [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.

Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 4

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
*/

class Max_Consecutive_Ones_II_487 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int max = 0;
        int numZeroes = 0;

        for (int right = 0; right < nums.length; ++right) {
            if (nums[right] == 0) numZeroes++;

            while (numZeroes == 2) {
                if (nums[left] == 0) numZeroes--;

                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0, K = 1;

        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) K--;
            if (K < 0) {
                if (nums[left] == 0) K++;
                left++;
            }
        }

        return right - left;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutive = 0, zeroLeft = 0, zeroRight = 0;
        for (int i = 0; i < nums.length; i++) {
            zeroRight++;
            if (nums[i] == 0) {
                zeroLeft = zeroRight;
                zeroRight = 0;
            }
            maxConsecutive = Math.max(maxConsecutive, zeroLeft + zeroRight);
        }
        return maxConsecutive;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0, index = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                count++;
            } else {
                count = i - index;
                index = i;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}