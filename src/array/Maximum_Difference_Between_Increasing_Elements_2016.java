
package array;
/*
Given a 0-indexed integer array nums of size n, 

find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), 

such that 0 <= i < j < n and nums[i] < nums[j].

Return the maximum difference. If no such i and j exists, return -1.

 

Example 1:

Input: nums = [7,1,5,4]
Output: 4
Explanation:
The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
Example 2:

Input: nums = [9,4,3,2]
Output: -1
Explanation:
There is no i and j such that i < j and nums[i] < nums[j].
Example 3:

Input: nums = [1,5,2,10]
Output: 9
Explanation:
The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
 

Constraints:

n == nums.length
2 <= n <= 1000
1 <= nums[i] <= 109
*/
class Maximum_Difference_Between_Node_and_Ancestor_1026 {
    public int maximumDifference(int[] nums) {
        int max = -1;
        int length = nums.length;
        for (int i = 0; i < length - 1; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (nums[j] > nums[i]) {
                    max = Math.max(max, nums[j] - nums[i]);
                }
            }
        }
        
        return max;
    }

    public int maximumDifference(int[] nums) {
    	int max = -1;
    	int min = Integer.MAX_VALUE;
    	int length = nums.length;
    	for (int i = 0; i < length; ++i) {
    		if (nums[i] < min) min = nums[i];


    		if (max < (nums[i] - min)) max = nums[i] - min;
    	}
        
        if (max == 0) return -1;

    	return max;
    }
}