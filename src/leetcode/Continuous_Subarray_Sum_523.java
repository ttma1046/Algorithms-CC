package leetcode;
import java.util.HashMap;
import java.util.Map;

/*
Given a list of non-negative numbers and a target integer k,

write a function to check if the array has a continuous subarray of size
at least 2 that sums up to a multiple of k,
that is, sums up to n*k where n is also an integer.

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


Constraints:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/
class Continuous_Subarray_Sum_523 {
    public boolean checkSubarraySumI(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            int result = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                result += nums[j];

                if (result == k || (k != 0 && result % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6)); // true
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6)); // true
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {5, 0, 0}, 0)); // true
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {23, 2, 4, 6, 7}, -6)); // true
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {0, 0}, -1)); // true
        
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {1, 0}, 2)); // false
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {0, 1, 0}, 0)); // false
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {0}, 0)); // false
        System.out.println(new Continuous_Subarray_Sum_523().checkSubarraySum(new int[] {0, 0}, 0)); // true
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> myHash = new HashMap<>();
        myHash.put(0, -1);
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (k != 0) sum %= k;

            if (myHash.containsKey(sum)) {
                if (i - myHash.get(sum) > 1) return true;
            } else {
                myHash.put(sum, i);
            }
        }

        return false;
    }
}