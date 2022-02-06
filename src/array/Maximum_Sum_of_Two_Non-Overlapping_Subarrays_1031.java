package array;
/*
Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.

The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.

Example 2:

Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.

Example 3:

Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.

Constraints:

1 <= firstLen, secondLen <= 1000
2 <= firstLen + secondLen <= 1000
firstLen + secondLen <= nums.length <= 1000
0 <= nums[i] <= 1000
*/
class Maximum_Sum_of_Two_Non_Overlapping_Subarrays_1031 {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int res = 0;
        int firstLensum = 0;
        int secondLensum = 0;

        int firstLenmax = 0;
        int secondLenmax = 0;

        for (int i = 0; i < nums.length; ++i) {
            secondLensum += nums[i];
            if (i - secondLen >= 0) secondLensum -= nums[i - secondLen];

            if (i - secondLen >= 0) firstLensum += nums[i - secondLen];
            if (i - secondLen - firstLen >= 0) firstLensum -= nums[i - firstLen - secondLen];

            firstLenmax = Math.max(firstLenmax, firstLensum);
            res = Math.max(res, firstLenmax + secondLensum);
        }

        firstLensum = firstLenmax = secondLensum = secondLenmax = 0;

        for (int i = 0; i < nums.length; ++i) {
            firstLensum += nums[i];
            if (i - firstLen >= 0) firstLensum -= nums[i - firstLen];

            if (i - firstLen >= 0) secondLensum += nums[i - firstLen];
            if (i - firstLen - secondLen >= 0) secondLensum -= nums[i - firstLen - secondLen];

            secondLenmax = Math.max(secondLenmax, secondLensum);
            res = Math.max(res, secondLenmax + firstLensum);
        }

        return res;
    }

    public static void main(String[] args) {
        Maximum_Sum_of_Two_Non_Overlapping_Subarrays_1031 obj = new Maximum_Sum_of_Two_Non_Overlapping_Subarrays_1031();
    }
}