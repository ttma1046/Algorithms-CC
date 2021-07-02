package binarysearch;

/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
*/

class Find_First_and_Last_Position_of_Element_in_Sorted_34 {
    public int[] searchRangeIII(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] ret = new int[] { -1, -1 };
        // Search for the left one
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }

        if (nums[i] != target) {
            return ret;
        } else {
            ret[0] = i;
        }

        // Search for the right one
        j = nums.length - 1; // We don't have to set i to 0 the second time.
        while (i < j) {
            int mid = i + (j - i) / 2 + 1;	// Make mid biased to the right
            if (nums[mid] > target) {
                j = mid - 1;
            } else {
                i = mid;				// So that this won't make the search range stuck.
            }
        }

        ret[1] = j;
        return ret;
    }

    /*
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    public int[] searchRangeMyII(int[] nums, int target) {
        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) return new int[] { -1, -1 };

        return new int[] { leftIdx, extremeInsertionIndex(nums, target, false) - 1 } ;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }
    */

    public int[] searchRangeMyII(int[] nums, int target) {
        int leftIdx = extremeInsertionIndex(nums, target, true);

        if (leftIdx == nums.length || nums[leftIdx] != target) return new int[] { -1, -1 };

        return new int[] { leftIdx, extremeInsertionIndex(nums, target, false) - 1 };
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length;
        int mid = -1;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (nums[mid] > target || (left && nums[mid] == target)) high = mid;
            else low = mid + 1;
        }

        return low;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                if (res[0] == -1) res[0] = i;
                else res[1] = i;
        }

        if (res[0] != -1 && res[1] == -1) res[1] = res[0];

        return res;
    }

    public int[] searchRangeIV(int[] nums, int target) {
        int firstOccurrence = this.findBound(nums, target, true);
        if (firstOccurrence == -1) return new int[] {-1, -1};
        int lastOccurrence = this.findBound(nums, target, false);
        return new int[] {firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {
            int mid = begin + (end - begin) / 2;

            if (nums[mid] == target) {

                if (isFirst) {
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) return mid;
                    // Search on the left side for the bound.
                    end = mid - 1;
                } else {
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) return mid;
                    // Search on the right side for the bound.
                    begin = mid + 1;
                }

            } else if (nums[mid] > target) end = mid - 1;
            else begin = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        Find_First_and_Last_Position_of_Element_in_Sorted_34 obj = new Find_First_and_Last_Position_of_Element_in_Sorted_34();

        int[] input = new int[] {5, 7, 7, 8, 8, 10};

        int[] result = obj.searchRange(input, 8);
        for (int i : result) System.out.println(i);

        /*
        result = obj.searchRangeII(input, 8);
        for (int i : result) System.out.println(i);
        */
        
        result = obj.searchRange(input, 6);
        for (int i : result) System.out.println(i);

        result = obj.searchRangeMy(input, 8);
        for (int i : result) System.out.println(i);

        result = obj.searchRangeMyII(input, 8);
        for (int i : result) System.out.println(i);

        input = new int[0];
        result = obj.searchRange(input, 0);
        for (int i : result) System.out.println(i);
    }

    public int[] searchRangeMy(int[] nums, int target) {
        int leftIndex = findIndex(nums, target, true);
        if (leftIndex == -1) return new int[] { -1, -1 };
        int rightIndex = findIndex(nums, target, false);
        return new int[] { leftIndex, rightIndex };
    }

    public int findIndex(int[] nums, int target, boolean isFirst) {
        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                if (isFirst) {
                    if (mid == low || nums[mid - 1] != target) return mid;
                    high = mid - 1;
                } else {
                    if (mid == high || nums[mid + 1] != target) return mid;
                    low = mid + 1;
                }
            } else if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }

    /*
    Time Complexity: O(logN) considering there are N elements in the array.
    This is because binary search takes logarithmic time to scan an array of N elements.
    Why? Because at each step we discard half of the array we are scanning and hence, we're done after a logarithmic number of steps.
    We simply perform binary search twice in this case.

    Space Complexity: O(1) since we only use space for a few variables and our result array, all of which require constant space.
    */
}