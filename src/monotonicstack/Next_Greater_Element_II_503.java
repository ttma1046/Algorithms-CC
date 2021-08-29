package dp;
import java.util.Stack;
import java.util.Arrays;


/*
Given a circular array (the next element of the last element
is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/

class Next_Greater_Element_II_503 {
    public int[] nextGreaterElementsI(int[] nums) {
        int n = nums.length, res[] = new int[n];

        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {

                int index = stack.pop();

                res[index] = nums[i % n];
            }

            stack.push(i % n);
        }

        return res;
    }

    public int[] nextGreaterElementsIII(int[] nums) {
        int n = nums.length, top = 0;
        int[] stack = new int[n];
        int[] res = new int[n];


        for (int i = 0; i < n; i++) {
            res[i] = -1;
            for (; top > 0 && nums[i] > nums[stack[top - 1]]; )
                res[stack[--top]] = nums[i];
            stack[top++] = i;
        }

        for (int i = 0; i < n; i++) {
            for (; top > 0 && nums[i] > nums[stack[top - 1]]; )
                res[stack[--top]] = nums[i];
        }

        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];

        Arrays.fill(next, -1);

        Stack<Integer> stack = new Stack<>(); // index stack

        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];

            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int index = stack.pop();
                next[index] = num;
            }

            if (i < n) stack.push(i);
        }
        return next;
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 2 * n - 1; i >= 0; --i) {
            while (stack.size() > 0 && nums[stack.peek()] <= nums[i % n]) {
                stack.pop();
            }

            res[i % n] = stack.size() > 0 ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, res[] = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (stack.size() > 0 && nums[i % n] >= stack.peek()) stack.pop();

            res[i % n] = stack.size() > 0 ? -1 : stack.peek();

            stack.push(nums[i % n]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] result = new Next_Greater_Element_II_503().nextGreaterElements(new int[] {1, 2, 1});

        for (int x : result) {
            System.out.println(x);
        }
    }
}