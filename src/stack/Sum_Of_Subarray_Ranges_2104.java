package stack;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;
/*
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:

Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:

Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.

Constraints:

1 <= nums.length <= 1000
-109 <= nums[i] <= 109

Follow-up: Could you find a solution with O(n) time complexity?
*/
class Pair {
    int val, index;
    Pair(int val, int index) {
        this.val = val;
        this.index = index;
    }
}

class Sum_Of_Subarray_Ranges_2104 {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;

        int[] greaterLeft = new int[n];
        int[] greaterRight = new int[n];
        int[] lesserLeft = new int[n];
        int[] lesserRight = new int[n];

        Deque<Pair> stack = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            while(stack.size() > 0 && nums[i] >= stack.peek().val)
                stack.pop();

            lesserLeft[i] = stack.size() == 0 ? i + 1 : i - stack.peek().index;
            stack.push(new Pair(nums[i], i));
        }

        stack.clear();

        for (int i = n - 1; i >= 0; --i) {
            while(stack.size() > 0 && nums[i] > stack.peek().val)
                stack.pop();

            lesserRight[i] = stack.size() == 0 ? n - i : stack.peek().index - i;
            stack.push(new Pair(nums[i], i));
        }

        stack.clear();

        for (int i = 0; i < n; ++i) {
            while(stack.size() > 0 && nums[i] <= stack.peek().val)
                stack.pop();

            greaterLeft[i] = stack.size() == 0 ? i + 1 : i - stack.peek().index;
            stack.push(new Pair(nums[i], i));
        }

        stack.clear();

        for (int i = n - 1; i >= 0; --i) {
            while(stack.size() > 0 && nums[i] < stack.peek().val)
                stack.pop();

            greaterRight[i] = stack.size() == 0 ? n - i : stack.peek().index - i;
            stack.push(new Pair(nums[i], i));
        }

        long res = 0;

        for (int i = 0; i < n; ++i)
            res += (long)lesserLeft[i] * lesserRight[i] * nums[i] - (long)greaterLeft[i] * greaterRight[i] * nums[i];
        
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3 };

        Sum_Of_Subarray_Ranges_2104 obj = new Sum_Of_Subarray_Ranges_2104();

        System.out.println(obj.subArrayRanges(array));
    }
}