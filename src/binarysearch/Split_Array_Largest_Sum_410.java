package binarysearch;
/*
Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9

Example 3:

Input: nums = [1,4,4], m = 3
Output: 4

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
*/
class Split_Array_Largest_Sum_410 {
	public int splitArray(int[] nums, int m) {
		long l = 0;
		long r = 0;
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			r += nums[i];
			if (l < nums[i]) {
				l = nums[i];
			}
		}

		System.out.println("l:" + l);
		System.out.println("r:" + r);

		long ans = r;
		
		while (l <= r) {
			long mid = l + ((r - l) >> 1);
			long sum = 0;
			int cnt = 1;
			for (int i = 0; i < n; i++) {
				if (sum + nums[i] > mid) {
					cnt ++;
					sum = nums[i];
				} else {
					sum += nums[i];
				}
			}
			if (cnt <= m) {
				ans = Math.min(ans, mid);
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		return (int)ans;
	}

	public int splitArrayII(int[] nums, int m) {
		int n = nums.length;
		int[][] f = new int[n + 1][m + 1];
		int[] sub = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				f[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < n; i++) {
			sub[i + 1] = sub[i] + nums[i];
		}
		f[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				for (int k = 0; k < i; k++) {
					f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
				}
			}
		}
		return f[n][m];
	}

	public static void main(String[] args) {
		Split_Array_Largest_Sum_410 obj = new Split_Array_Largest_Sum_410();
		int[] input = new int[] {7,2,5,10,8};
		obj.splitArray(input, 2);
	}

	public int splitArray(int[] nums, int m) {
		int sum = Arrays.stream(nums).sum();
		int max = Arrays.stream(nums).max().getAsInt();
		return binary(nums, m, sum, max);
	}

	private int binary(int[] nums, int m, int high, int low) {
		int mid = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (valid(nums, m, mid)) high = mid - 1;
			else low = mid + 1;
		}
	}

	private boolean valid(int[] nums, int m, int subArraySum) {
		int curSum = 0, count = 1;
		for (int num: nums) {
			curSum += num;
			if (curSum > subArraySum) {
				curSum = num;
				count++;
				if (count > m) return false;
			}
		}

		return true;
	}
}