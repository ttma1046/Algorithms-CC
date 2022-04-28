package monotonicstack;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

/*
The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.


Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.


Follow up: Could you find an O(nums1.length + nums2.length) solution?
*/
class Next_Greater_Element_I_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = nums2.length - 1; i >= 0; --i) {
            while(stack.size() > 0 && stack.peek() <= nums2[i]) stack.pop();
            map.put(nums2[i], stack.size() > 0 ? stack.peek() : -1);
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) res[i] = map.get(nums1[i]);        

        return res;
    }

    public int[] nextGreaterElementII(int[] nums1, int[] nums2) {
       Map<Integer,Integer> map = new HashMap<Integer, Integer>();
       Stack<Integer> stack = new Stack<Integer>();
        
        int[] ans = new int[nums1.length];
        
        for(int num: nums2) {
            while(stack.size() > 0 && num > stack.peek()) map.put(stack.pop(), num);
            stack.push(num);
        }
        
        for(int i = 0; i < nums1.length; ++i) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {

    }

    /*
    public int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; --i) {
            while(stack.size() > 0 && stack.peek() <= nums[i]) stack.pop();
            res[i] = stack.isEmpty() ? -1： stack.peek();
            stack.push(nums[i]);
        }

        return res;
    }
    */
}