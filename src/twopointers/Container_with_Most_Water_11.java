package twopointers;
/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).

n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).

Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1
Example 3:

Input: height = [4,3,2,1,4]
Output: 16
Example 4:

Input: height = [1,2,1]
Output: 2


Constraints:

2 <= height.length <= 3 * 104
0 <= height[i] <= 3 * 104
*/

class Container_with_Most_Water_11 {
	/*
	Time complexity : O(n^2). Calculating area for all n(n-1)/2 height pairs.
	Space complexity : O(1). Constant extra space is used.
	*/

	public int maxArea(int[] height) {
		int ans = 0;

		for (int i = 0; i < height.length; i++)
			for (int j = i + 1; j < height.length; j++)
				ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));

		return ans;
	}

	/*
	Time complexity : O(n)O(n). Single pass.

	Space complexity : O(1)O(1). Constant space is used.
	*/
	public int maxAreaII(int[] height) {
		int i = 0, j = height.length - 1, maxArea = 0;
		while (i < j) {
			maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
			if (height[i] > height[j])
				j--;
			else
				i++;
		}

		return maxArea;
	}


	public int maxArea(int[] height) {
		int start = 0;
		int end = height.length - 1;
		int area = 0;

		while (start < end) {
			if (height[start] > height[end]) {
				area = Math.max(height[end] * (end - start) , area);
				end--;
			} else {
				area = Math.max(height[start] * (end - start) , area);
				start++;
			}
		}
		return area;
	}

	public static void main(String[] args) {
		System.out.println(new Container_with_Most_Water_11().maxAreaII(new int[] {1,8,6,2,5,4,8,3,7}));
	}
}