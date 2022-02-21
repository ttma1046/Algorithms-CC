
package array;
/*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:

Input: nums = [1,2,3,1]
Output: true

Example 2:

Input: nums = [1,2,3,4]
Output: false

Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
*/
class Contains_Duplicate_217 {
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; ++i)
            for (int j = 0; j < i; ++j)
                if (nums[j] == nums[i]) return true;
        return false;
    }


    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i)
            if (nums[i] == nums[i + 1]) return true;

        return false;
    }

    /*
    Time complexity: O(nlogn). Sorting is O(nlogn) and the sweeping is O(n).
    The entire algorithm is dominated by the sorting step, which is O(nlogn).

    Space complexity: O(1).
    Space depends on the sorting implementation which, usually, costs O(1) auxiliary space if heapsort is used.
    */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x : nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }
}