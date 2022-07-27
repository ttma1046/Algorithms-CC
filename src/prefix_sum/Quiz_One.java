package prefix_sum;

class Quiz_One {
	public int[] prefix_sum(int[] a) {
		int[] result = new int[a.length];

		result[0] = a[0];

		for (int i = 1; i < a.length; i++)
			result[i] = result[i - 1] + a[i];
		

		return result;
	}

	public int countSubArray(int[] nums) {
		int ans = 0;
		int pre = 0;
		for (int x : nums) {
			pre += 1;
			ans += pre;
		}
		return ans;
	}

	public int countSubArrayIncreaseOne(int[] nums) {
		int ans = 0;
		int pre = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - nums[i - 1] == 1) {
				pre += 1;
			} else {
				pre = 0;
			}

			ans += pre;
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(new Quiz_One().countSubArray(new int[] {1, 3, 4}));

		System.out.println(new Quiz_One().countSubArrayIncreaseOne(new int[] {1, 3, 2}));

		System.out.println(new Quiz_One().countSubArrayEqualK(3, new int[] {1, 3, 4}));
	}

	/*
	 every item less than k.
	*/
	public int countSubArrayLessThanK(int k, int[] nums) {
		int ans = 0;
		int pre = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= k) {
				pre += 1;
			} else {
				pre = 0;
			}

			ans += pre;
		}
		return ans;
	}

	public int countSubArrayEqualK(int k, int[] nums) {
		return countSubArrayLessThanK(k, nums) - countSubArrayLessThanK(k - 1, nums);
	}
}