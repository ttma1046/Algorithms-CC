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
class ThreeSum_Closest_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length;

        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < n && diff != 0; i++) {
            int low = i + 1, high = n - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (Math.abs(sum - target) < Math.abs(diff)) diff = target - sum;

                if (sum < target)
                    ++low;
                else
                    --high;
            }
        }

        return target - diff;
    }

    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            for (int j = i + 1; j < sz - 1; ++j) {
                int complement = target - nums[i] - nums[j];
                var idx = Arrays.binarySearch(nums, j + 1, sz - 1, complement);
                int hi = idx >= 0 ? idx : ~idx, lo = hi - 1;
                if (hi < sz && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }
        return target - diff;
    }

    /*
    Time Complexity: O(n^2). We have outer and inner loops, each going through n elements.

    Sorting the array takes O(nlogn), so overall complexity is O(nlogn+n^2). This is asymptotically equivalent to O(n^2).

    Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
    */

    public static void main(String[] args) {
        TreeSum_Closest_16 kk = new ThreeSum_Closest_16();

        kk.threeSumClosest(new int[] { -1, 2, 1, -4}, 1);
    }
}