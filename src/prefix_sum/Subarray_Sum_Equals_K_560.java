package prefix_sum;

import java.util.HashMap;

/*
Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

class Subarray_Sum_Equals_K_560 {
    public int subarraySumIII(int[] nums, int k) {
        if (nums == null || nums.length == 0) 
            return 0;

        int result = 0;

        for(int i = 0; i < nums.length; i++) {
            int sum = nums[i];

            if (sum == k) 
                result++;

            for (int j = i + 1; j < nums.length; j++) {
                if (sum + nums[j] == k) 
                    result++;

                sum += nums[j];
            }
        }

        return result;
    }

    public int subarraySumII(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int result = 0;
        int currSum = 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1); // super-duper

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            if (map.containsKey(currSum - k)) result += map.get(currSum - k);

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return result;
    }

    public int subarraySum(int[] nums, int k) {
        int result = 0, currSum = 0;
        HashMap<Integer, Integer> h = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            // current prefix sum
            currSum += num[i];

            // situation 1:
            // continuous subarray starts
            // from the beginning of the array
            if (currSum == k) 
                result++;

            // situation 2:
            // number of times the curr_sum − k has occured already,
            // determines the number of times a subarray with sum k
            // has occured upto the current index
            result += h.getOrDefault(currSum - k, 0);

            // add the current sum
            h.put(currSum, h.getOrDefault(currSum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Subarray_Sum_Equals_K_560().subarraySumII(new int[] { 28, 54, 7, -70, 22, 65, -6 }, 100));

        System.out.println(new Subarray_Sum_Equals_K_560().subarraySumII(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));
    }
}