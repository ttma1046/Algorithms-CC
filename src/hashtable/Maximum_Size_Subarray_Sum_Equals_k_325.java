package hashtable;

import java.util.Map;
import java.util.HashMap;
/*
Given an integer array nums and an integer k,

return the maximum length of a subarray that sums to k.

If there is not one, return 0 instead.

Example 1:

Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:

Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Constraints:

1 <= nums.length <= 2 * 105
-104 <= nums[i] <= 104
-109 <= k <= 109
*/
class Maximum_Size_Subarray_Sum_Equals_k_325 {
    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0;
        int prefix = 0; // set initial values for cumulative sum and max length sum to k
        	
        Map<Integer, Integer> sumToIndexMap = new HashMap<Integer, Integer>(); // key: cumulative sum until index i, value: i

        for (int i = 0; i < nums.length; ++i) {
        	prefix += nums[i]; // update cumulative sum

        	// two cases where we can update maxLen
        	if (prefix == k)
        		res = i + 1; // case 1: cumulative sum is k, update maxLen for sure
        	else if (sumToIndexMap.containsKey(prefix - k))
        		res = Math.max(res, i - sumToIndexMap.get(prefix - k)); // case 2: cumulative sum is more than k, but we can truncate a prefix of the array

        	// store cumulative sum in map, only if it is not seen
        	// because only the eariler (thus shorter) subarray is valuable,
        	// when we want to get the maxLen after truncation
        	if (!sumToIndexMap.containsKey(prefix))
        		sumToIndexMap.put(prefix, i);
        }

        return res;
    }
    
    // Time complexity: O(n). 
    // Space complexity: O(n). 
    public static void main(String[] args) {
        int[] nums = new int[] {1, -1, 5, -2, 3};
        int k = 3;

        Maximum_Size_Subarray_Sum_Equals_k_325 obj = new Maximum_Size_Subarray_Sum_Equals_k_325();
        System.out.println(obj.maxSubArrayLen(nums, k));

        nums = new int[] {-2, -1, 2, 1};
        k = 1;
        System.out.println(obj.maxSubArrayLen(nums, k));
    }
}
