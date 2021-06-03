package twopointers;

import java.util.Arrays;

/*
Given an array of n integers nums and an integer target, 

find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example 1:

Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]

Example 2:

Input: nums = [], target = 0
Output: 0

Example 3:

Input: nums = [0], target = 0
Output: 0

Constraints:

n == nums.length
0 <= n <= 3500
-100 <= nums[i] <= 100
-100 <= target <= 100
*/
class ThreeSum_Smaller_259 {
    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int newTarget = target - nums[i];
            int low = i + 1;
            int high = n - 1;
            while (low < high) {
                if (nums[low] + nums[high] < newTarget) {
                    res += high - low;
                    low++;
                } else {
                    high--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ThreeSum_Smaller_259 obj = new ThreeSum_Smaller_259();

        int[] input = { -1, 2, 1, -4 };
        int target = 1;
        System.out.println(obj.threeSumSmaller(input, target));
    }
}