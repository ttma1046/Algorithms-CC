package leetcode;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
*/
class Next_Permutation_31 {
	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i + 1] <= nums[i])
			i--;
		
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i])
				j--;

			swap(nums, i, j);
		}
		
		reverse(nums, i + 1, nums.length - 1);
	}

	public void nextPermutation(int[] nums) {
		int i = 0;
		for (i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				int j = 0;
				for (j = nums.length - 1; nums[j] <= nums[i]; j--) {

				}

				swap(nums, i, j);
				break;
			}
		}

		reverse(nums, i + 1, nums.length - 1);
	}

	private void swap(int[] nums, int i, int j) {
		nums[j] = nums[i] + nums[j];
		nums[i] = nums[j] - nums[i];
		nums[j] = nums[j] - nums[i];
	}

	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}

	public static void main(String[] args) {

		int[] result = new int[] { 1, 5, 8, 4, 7, 6, 5, 3, 1 };

		new Next_Permutation_31().nextPermutationII(result);


		for (int i : result) {
			System.out.println(i);
		}

		result = new int[] {1, 2, 3};


		new Next_Permutation_31().nextPermutationII(result);


		for (int i : result) {
			System.out.println(i);
		}


		result = new int[] {3, 2, 1};

		new Next_Permutation_31().nextPermutationII(result);


		for (int i : result) {
			System.out.println(i);
		}


		result = new int[] {1, 1, 5};

		new Next_Permutation_31().nextPermutationII(result);


		for (int i : result) {
			System.out.println(i);
		}
	}
}