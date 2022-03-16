package array;
/*
Given an integer array nums and an integer k,

return true if there are two distinct indices i and j in the array

such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105
*/
class Contains_Duplicate_II_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; ++i)
            for (int j = Math.max(i - k, 0); j < i; ++j)
                if (nums[i] == nums[j]) reture true;

        return false;
    }

    /*
    Time complexity : O(nmin(k,n)). It costs O(min(k,n)) time for each linear search.
    Apparently we do at most n comparisons in one search even if k can be larger than nn.

    Space complexity : O(1).
    */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;

            set.add(nums[i]);
            if (set.size() > k) set.remove(nums[i - k]);
        }

        return false;
    }
}