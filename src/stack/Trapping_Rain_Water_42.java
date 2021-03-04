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
    	Stack<Integer> stack = new Stack<>();
    	int ans = 0;
    	for (int right = 0; right < height.length; ++right) {
    		while(!stack.isEmpty() && height[right] > height[stack.peek()]) {
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

    public static void main(String[] args) {
    	System.out.println(new Trapping_Rain_Water_42().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    	System.out.println(new Trapping_Rain_Water_42().trap(new int[] {4,2,0,3,2,5}));
    }
}