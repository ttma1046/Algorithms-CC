package binarysearch;
/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [1,3,5]
Output: 1

Example 2:

Input: nums = [2,2,2,0,1]
Output: 0

Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.

Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
*/
class Find_Minimum_in_Rotated_Sorted_Array_II_154 {
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int low = 0, high = nums.length - 1;

        while(low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[high]) high = mid;
            else if (nums[mid] > nums[high]) low = mid + 1;
            else high -= 1;
        }

        return nums[low];
    }

    public int findMin(int[] nums) {
        if(nums.length == 1)return nums[0];
        if(nums.length == 2)return Math.min(nums[0], nums[1]);
        if(nums[nums.length - 1] > nums[0]) return nums[0];
        int low = 0;
        int high = nums.length - 1;
        int middle = 0;
        while (low < high) {
            middle = (low + high) >>> 1;
            if(nums[middle] > nums[high]) low = middle + 1;
            else if(nums[middle] < nums[high]) high = middle;
            else {  // nums[middle] == nums[high]
                if(nums[low] == nums[middle]) {
                    low++;
                    high--;
                } else high = middle; // nums[low] < nums[middle] || nums[low] > nums[middle]
            }
        }

        return nums[high];
    }

    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[low]) {
                high = mid;
                low++;
            } else { // nums[low] <= nums[mid] <= nums[high]
                high--;
            }
        }

        return nums[low];
    }

    public int findMin(int[] nums) {
        int min = nums[0];
        for (int val : nums) if (val < min) min = val;
        return min;
    }

    public static void main(String[] args) {
        Find_Minimum_in_Rotated_Sorted_Array_II_154 obj = new Find_Minimum_in_Rotated_Sorted_Array_II_154();
        int[] input = new int[] { 2, 2, 2, 0, 1 };
        System.out.println(obj.findMin(input));
    }
}