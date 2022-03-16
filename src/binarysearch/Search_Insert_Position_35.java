package binarysearch;
/*
Given a sorted array of distinct integers and a target value,

return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
*/
class Search_Insert_Position_35 {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || nums == null) return -1;

        int start = 0, end = nums.length - 1, mid = -1;

        while(start <= end) {
            mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        return start;
    }

    public static void main(String[] args) {
        Search_Insert_Position_35 obj = new Search_Insert_Position_35();
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        if(target <= nums[start])
            return 0;

        if(target > nums[end])
            return end + 1;

        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] < target)
                start = mid + 1;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                return mid;
        }

        return start;
    }
}