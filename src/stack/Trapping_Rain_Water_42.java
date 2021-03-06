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

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxleft) { 
                    maxleft = height[left]; 
                } else { 
                    res += maxleft - height[left]; 
                }

                left++;
            } else {
                if (height[right] >= maxright) { 
                    maxright = height[right];
                } else {
                    res += maxright - height[right];
                }

                right--;
            }
        }
        
        return res;
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


    public int trapStackII(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> stack = new Stack<>();

        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if (stack.empty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Trapping_Rain_Water_42().trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Trapping_Rain_Water_42().trap(new int[] {4, 2, 0, 3, 2, 5}));
    }
}