package array;
/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?


Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.
*/
class Sort_Colors_75 {
	public void sortColors(int[] nums) {
		int l = 0, r = nums.length - 1, i = 0;

		int temp = 0;
		while (i <= r) {
			if (nums[i] == 0) {
				temp = nums[l];
				nums[l] = nums[i];
				nums[i] = temp;
				i++;
				l++;
			} else if (nums[i] == 2) {
				temp = nums[r];
				nums[r] = nums[i];
				nums[i] = temp;
				r--;
			} else {
				i++;
			}
		}
	}

	public void sortColors(int[] nums) {
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;

		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] == 0) count0++;
			if (nums[i] == 1) count1++;
			if (nums[i] == 2) count2++;
		}

		for (int i = 0; i < nums.lenght; ++i) {
			if (i < count0) nums[i] = 0;
			else if (i < count0 + count1) nums[i] = 1;
			else nums[i] = 2;
		}
	}

	public void sortColors(int[] nums) {
		int left = 0, right = nums.length - 1;

		for (int i = 0; i <= right; i++) {
			if (nums[i] == 0 && i > left)
				swap(nums, i--, left++);
			else if (nums[i] == 2 && i < right)
				swap(nums, i--, right--);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] aka = new int [] {2, 0, 2, 1, 1, 0};

		/*
		{0, 0, 2, 1, 1, 2}

		l            r
		i

		{0, 0, 2, 1, 1, 2}
		
		    l        r 
		    i           

		{0, 0, 2, 1, 1, 2}
		
		       l     r
		       i     

		{0, 0, 1, 1, 2, 2}
		
		       l  r  
		       i       
		*/
		new Sort_Colors_75().sortColors(new int [] {2, 0, 2, 1, 1, 0});
	}
}
