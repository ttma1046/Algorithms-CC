package binarysearch;
/*
81. Search in Rotated Sorted Array II Medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?

*/

/*
1) everytime check if targe == nums[mid], if so, we find it.
2) otherwise, we check if the first half is in order (i.e. nums[start]<=nums[mid]) 
  and if so, go to step 3), 
	3) check if target in the range of [start, mid-1] (i.e. nums[start] <= target < nums[mid]), if so, do search in the first half, i.e. end = mid - 1; otherwise, search in the second half end = mid + 1;
  otherwise, the second half is in order,  o to step 4)	
	4)  check if target in the range of [mid+1, end] (i.e. nums[mid] < target <= nums[end]), if so, do search in the second half, i.e. start = mid + 1; otherwise search in the first half end = mid - 1;
*/


class Search_in_Rotated_Sorted_Array_II_81 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return true;
            } else if (nums[start] == nums[mid] && nums[end] == nums[mid]) { 
            	++start; 
            	--end;
            } else if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

	public static void main(String[] args) {
		System.out.println(new Search_in_Rotated_Sorted_Array_II_81().search(new int[] { 2,5,6,0,0,1,2 }, 0));
		System.out.println(new Search_in_Rotated_Sorted_Array_II_81().search(new int[] { 2,5,6,0,0,1,2 }, 3));
	}
}