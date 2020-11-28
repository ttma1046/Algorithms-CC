package binarysearch;

/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
*/

class Find_First_and_Last_Position_of_Element_in_Sorted_34 {
	/*	public int[] searchRange(int[] nums, int target) {
			int start = 0;
			int end = nums.length - 1;

			int[] res = new int[] { -1, -1};
			while (start < end) {
				int mid = start + (end - start) / 2;
				if (nums[mid] == target) {
					System.out.println(mid);
					res[0] = mid;
					res[1] = mid;
					int tempOne = mid;
					int tempTwo = mid;

					while (nums[--tempOne] == target) res[0] = tempOne;
					while (nums[++tempTwo] == target) res[1] = tempTwo;
					break;
				} else if (nums[mid] > target) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}

			return res;
		}*/

	public int[] searchRange(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		int[] res = new int[] { -1, -1 };
		// Search for the left one
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (A[mid] < target) {
				i = mid + 1;
			} else {
				j = mid;
			}
		}

		if (A[i] != target) {
			return ret;
		} else {
			ret[0] = i;
		}

		// Search for the right one
		j = n - 1; // We don't have to set i to 0 the second time.
		while (i < j) {
			int mid = i + (j - i) / 2 + 1;	// Make mid biased to the right
			if (A[mid] > target) {
				j = mid - 1;
			} else {
				i = mid;				// So that this won't make the search range stuck.
			}
		}

		ret[1] = j;
		return ret;
	}

	// returns leftmost (or rightmost) index at which `target` should be
	// inserted in sorted array `nums` via binary search.
	private int extremeInsertionIndex(int[] nums, int target, boolean left) {
		int lo = 0;
		int hi = nums.length;

		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] > target || (left && target == nums[mid])) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		return lo;
	}

	public int[] searchRange(int[] nums, int target) {
		int[] targetRange = { -1, -1};

		int leftIdx = extremeInsertionIndex(nums, target, true);

		// assert that `leftIdx` is within the array bounds and that `target`
		// is actually in `nums`.
		if (leftIdx == nums.length || nums[leftIdx] != target) {
			return targetRange;
		}

		targetRange[0] = leftIdx;
		targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

		return targetRange;
	}

	public static void main(String[] args) {
		int[] input = new int[] {5, 7, 7, 8, 8, 10};

		int[] result = new Find_First_and_Last_Position_of_Element_in_Sorted_34().searchRange(input, 8);
		for (int i : result) System.out.println(i);

		result = new Find_First_and_Last_Position_of_Element_in_Sorted_34().searchRange(input, 6);

		for (int i : result) System.out.println(i);

		input = new int[0];
		result = new Find_First_and_Last_Position_of_Element_in_Sorted_34().searchRange(input, 0);

		for (int i : result) System.out.println(i);
	}
}