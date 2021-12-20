package slidingwindow;

/*
Given an array of integers nums and an integer k, 

return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:

Input: nums = [1,2,3], k = 0
Output: 0

Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
*/

class Subarray_Product_Less_Than_K_713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];

            while (prod >= k) {
            	prod /= nums[left];
            	left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
    	Subarray_Product_Less_Than_K_713 obj = new Subarray_Product_Less_Than_K_713();
    }
}