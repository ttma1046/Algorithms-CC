package binarysearch;
/*
Suppose an array of length n sorted in ascending order is 

rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

* [4,5,6,7,0,1,2] if it was rotated 4 times.
* [0,1,2,4,5,6,7] if it was rotated 7 times.

Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 

1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 

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
            int mid = low + (high - low) / 2;
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