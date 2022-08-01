package twopointers;

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
*/

/*
Initialize the minimum difference diff with a large value.
    Sort the input array nums.

    Iterate through the array:
        For the current position i, set lo to i + 1, and hi to the last index.
    While the lo pointer is smaller than hi:
        Set sum to nums[i] + nums[lo] + nums[hi].
    If the absolute difference between sum and target is smaller than the absolute value of diff:
    Set diff to target - sum.
    If sum is less than target, increment lo.
    Else, decrement hi.
    If diff is zero, break from the loop.
    Return the value of the closest triplet, which is target - diff.
*/
class ThreeSum_Closest_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int diff = Integer.MAX_VALUE;

        int n = nums.length;

        for(int i = 0; i < n && diff != 0; ++i) {
            int low = i + 1, high = n - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];

                if (Math.abs(target - sum) < Math.abs(diff)) 
                    diff = target - sum;

                if (sum < target) 
                    ++low;
                else 
                    --high;
            }
        }

        return target - diff;
    }

    /*
    Time Complexity: O(n^2). We have outer and inner loops, each going through n elements.

    Sorting the array takes O(nlogn), so overall complexity is O(nlogn+n^2). This is asymptotically equivalent to O(n^2).

    Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
    */


    /*

    Initialize the minimum difference diff with a large value.
      Sort the input array nums.
        Iterate through the array (outer loop):
        For the current position i, iterate through the array starting from j = i + 1 (inner loop):
        Binary-search for complement (target - nums[i] - nums[j]) in the rest of the array.
        For the next higher value, check its absolute difference with complement against diff.
        For the previous lower value, check its absolute difference with complement against diff.
        Update diff based on the smallest absolute difference.
        If diff is zero, break from the loop.

        Return the value of the closest triplet, which is target - diff.
    */

    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;

        int diff = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; ++j) {
                int complement = target - nums[i] - nums[j];

                int idx = Arrays.binarySearch(nums, j + 1, n - 1, complement);

                int hi = idx >= 0 ? idx : -idx, lo = hi - 1;
                if (hi < n && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }

        return target - diff;
    }

    public static void main(String[] args) {
        TreeSum_Closest_16 kk = new ThreeSum_Closest_16();

        kk.threeSumClosest(new int[] { -1, 2, 1, -4}, 1);
    }
}