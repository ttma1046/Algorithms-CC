package binarysearch;
class Binary_Search_704 {
	public int searchIte(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] < target)
				left = mid + 1;
			else if (nums[mid] > target)
				right = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	public int searchRec(int[] nums, int target) {
		return bs(nums, target, 0, nums.length - 1);
	}

	public int bs(int[] nums, int target, int left, int right) {
		if (left > right) {
			return -1;
		}

		int mid = left + (right - left) / 2;
		if (nums[mid] == target) {
			return mid;
		}

		if (nums[mid] > target) {
			return bs(nums, target, left, mid - 1);
		} else {
			return bs(nums, target, mid + 1, right);
		}
	}
}




