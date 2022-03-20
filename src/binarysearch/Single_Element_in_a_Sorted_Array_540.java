package binarysearch;
/*
You are given a sorted array consisting of only integers where every element appears exactly twice, 

except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
*/
class Single_Element_in_a_Sorted_Array_540 {
    public int singleNonDuplicate(int[] nums) {
        for(int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) return nums[i];
        }

        return nums[nums.length - 1];
    }

    /*
    Time complexity : O(n). For linear search, we are looking at every element in the array once.

    Space complexity : O(1). We are only using constant extra space.
    */

    /*
    0 0 1 1 2
    0 1 1 2 2

    0 0 1 1 2 2 3
    0 1 1 2 2 3 3
    */
    public int singleNonDuplicateII(int[] nums) {
        int start = 0, end = nums.length - 1;
        int mid = -1;

        while(start < end) {
            mid = start + (end - start) / 2;

            boolean highHalvesEven = (high - mid) % 2 == 0;

            if (nums[mid] == nums[mid + 1]) {
                if (highHalvesEven) 
                    start = mid + 2;
                else end = mid - 1;
            } else if (nums[mid] == nums[mid - 1]) {
                if (highHalvesEven) 
                    end = mid - 2;
                else start = mid + 1;
            } else return nums[mid];
        }

        return nums[start];
    }

    public int singleNonDuplicateIII(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1) mid--;

            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }
    // Time complexity : O(logN)
    // Space complexity : O(1)

    public static void main(String[] args) {
        Single_Element_in_a_Sorted_Array_540 obj = new Single_Element_in_a_Sorted_Array_540();
    }
}