/*
Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.

The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

 

Example 1:

Input: nums = [2,5,6,9,10]
Output: 2
Explanation:
The smallest number in nums is 2.
The largest number in nums is 10.
The greatest common divisor of 2 and 10 is 2.
Example 2:

Input: nums = [7,5,6,8,3]
Output: 1
Explanation:
The smallest number in nums is 3.
The largest number in nums is 8.
The greatest common divisor of 3 and 8 is 1.
Example 3:

Input: nums = [3,3]
Output: 3
Explanation:
The smallest number in nums is 3.
The largest number in nums is 3.
The greatest common divisor of 3 and 3 is 3.
 

Constraints:

2 <= nums.length <= 1000
1 <= nums[i] <= 1000
*/
class Solution {
    public int findGCD(int[] nums) {
        int min = 1000;
        
        int max = 1;
        
        for (int num : nums){
            min = Math.min(num, min);
            max = Math.max(num , max);
        }
        
        return gcd(min, max);
    }
    
    private int gcd (int a, int b){
        if (b % a == 0)
            return a;
        
        int result = 1;
        
        for (int i = 2; i <= a; ++i ){
            if (a % i == 0 && b % i == 0)
                result = i;
        }
        
        return result;
    }
}