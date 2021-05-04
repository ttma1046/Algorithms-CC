package array;
import java.util.Arrays;

/*
Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.

Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.

 

Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1]. 
The difference between the maximum and minimum is 1-0 = 1.
Example 3:

Input: nums = [6,6,0,1,1,4,6]
Output: 2
Example 4:

Input: nums = [1,5,6,14,15]
Output: 1
 

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

class Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509 {
    public int minDifference(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;

        if (nums.length < 5) return 0;
        Arrays.sort(nums);

        int res = Integer.MAX_VALUE;

        for(int i = 0; i < 4; ++i) res = Math.min(res, nums[nums.length - 1 - i] - nums[3 - i]);

        for(int i = 0; i < 4; ++i) res = Math.min(res, nums[nums.length - 4 + i] - nums[i]);
        
        return res;

        return Math.min(Math.min(nums[nums.length - 1] - nums[3], nums[nums.length - 4] - nums[0]), Math.min(nums[nums.length - 2] - nums[2], nums[nums.length - 3] - nums[1]));
    }

    public static void main(String[] args) {
    	int[] res = new int[] {5, 3, 2, 4};
    	System.out.println(new Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509().minDifference(res));

    	res = new int[] {1, 5, 0, 10, 14};
    	System.out.println(new Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509().minDifference(res));

    	res = new int[] {6, 6, 0, 1, 1, 4, 6};
    	System.out.println(new Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509().minDifference(res));

    	res = new int[] {1, 5, 6, 14, 15};
		System.out.println(new Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509().minDifference(res));

    	res = new int[] {20, 75, 81, 82, 95};
		System.out.println(new Minimum_Difference_Between_Largest_and_Smallest_Value_in_three_Moves_1509().minDifference(res));
    }
}