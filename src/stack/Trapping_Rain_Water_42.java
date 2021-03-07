package stack;

import java.util.Stack;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

     5
4000004
3000303
2220222
1110111

01234


Constraints:

n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105
*/

class Trapping_Rain_Water_42 {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int ans = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                } else {
                    ans += maxLeft - height[left];
                }

                ++left;
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right];
                } else {
                    ans += maxRight - height[right];
                }

                --right;
            }
        }

        return ans;
    }

    /*
    Complexity analysis

    Time complexity: O(n). Single iteration of O(n).
    Space complexity: O(1) extra space.
    Only constant space required for left, right, left_max and right_max.
    */

    // monotonic stack：decrement
    public int trapStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int right = 0; right < height.length; ++right) {
            while (!stack.isEmpty() && height[right] > height[stack.peek()]) {
                int bottom = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int left = stack.peek();

                int width = right - left - 1;
                int high = Math.min(height[left], height[right]) - height[bottom];


                ans += high * width;
            }

            stack.push(right);
        }

        return ans;
    }

    /*
    Time complexity: O(n).
    Single iteration of O(n) in which each bar can be touched at most twice
    (due to insertion and deletion from stack)
    and insertion and deletion from stack takes O(1) time.

    Space complexity: O(n). Stack can take upto O(n) space in case of stairs-like or flat structure.
    */

    public int trapDP(int[] height) {
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];

        left_max[0] = height[0];

        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }

        right_max[size - 1] = height[size - 1];

        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    public int trapShort(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        int maxleft = 0, maxright = 0;

        while (left <= right) {
            maxleft = Math.max(maxleft, height[left]);
            maxright = Math.max(maxright, height[right]);
            if (maxleft < maxright) {
                max += (maxleft - height[left]);   // maxleft is smaller than maxright, so the (maxleft-height[a]) water can be stored
                left++;
            } else {
                max += (maxright - height[right]);
                right--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Trapping_Rain_Water_42().trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Trapping_Rain_Water_42().trap(new int[] {4, 2, 0, 3, 2, 5}));
    }
}