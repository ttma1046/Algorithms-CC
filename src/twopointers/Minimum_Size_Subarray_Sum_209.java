package twopointers;

/*
Given an array of n positive integers and a positive integer s, 

find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the mimanimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

class Minimum_Size_Subarray_Sum_209 {
	// sliding window
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		int left = 0, sum = 0, ans = Integer.MAX_VALUE;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while (sum >= s) {
				ans = Math.min(ans, i + 1 - left);

				sum -= nums[left++];
			}
		}

		return ans != Integer.MAX_VALUE ? ans : 0;
	}

	public int minSubArrayLen(int[] nums, int s) {
		if (nums == null || nums.length == 0) return 0;

		int i = 0, n = nums.length, sum = 0;

		int res = Integer.MAX_VALUE;

		for (int j = 0; j < n; j++) {
			sum += nums[j];

			while (sum >= s) {
				if (j - i + 1 < res) {
					res = j - i + 1;
				}
				sum -= nums[i++];
			}
		}

		return res != Integer.MAX_VALUE ? res : 0;
	}

	/*
	Complexity analysis

	Time complexity: O(n)O(n). Single iteration of O(n)O(n).
	Each element can be visited atmost twice, once by the right pointer(ii) and (atmost)once by the \text{left}left pointer.
	Space complexity: O(1)O(1) extra space. Only constant space required for \text{left}left, \text{sum}sum, \text{ans}ans and ii.
	*/

	// 7, {2, 3, 1, 2, 4, 3});
	public int minSubArrayLenII(int s, int[] nums) {
		int i = 0, j = 0;
		int len = nums.length;
		int sum = 0;

		while (j < len && sum < s) {
			sum += nums[j];
			j++;
		}

		// sum: 8
		// j index: 4
		System.out.println(sum);

		System.out.println(j);

		if (sum < s)
			return 0;

		while (sum - nums[i] >= s) {
			sum -= nums[i];
			i++;
		}

		// 8 - 2 = 6 < 7, so i index: 0;

		System.out.println(i);

		while (j < len) {
			sum = sum + nums[j] - nums[i];

			// sum = 8 + nums[j = 4] - nums[i = 0];
			// sum = 8 + 4 - 2;
			// sum = 10;

			j++;
			// j = 5
			i++;
			// i = 1;
			while (sum - nums[i] >= s) {
				sum -= nums[i];
				i++;
			}

			//
		}

		return j - i;
	}

	/*
	Complexity analysis

	Time complexity: O(n\log(n))O(nlog(n)).
	For each element in the vector, find the subarray starting from that index, and having sum greater than ss using binary search. Hence, the time required is O(n)O(n) for iteration over the vector and O(\log(n))O(log(n)) for finding the subarray for each index using binary search.
	Therefore, total time complexity = O(n * \log(n))O(n∗log(n))
	Space complexity : O(n)O(n). Additional O(n)O(n) space for \text {sums} sums vector
	*/
	public int minSubArrayLenIII(int s, int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		int n = nums.length, len = Integer.MAX_VALUE;

		int[] sums = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			sums[i] = sums[i - 1] + nums[i - 1];
		}

		for (int item : sums) {
			System.out.println(item);
		}


		for (int i = n; i >= 0 && sums[i] >= s; i--) {
			int j = upper_bound(sums, 0, i, sums[i] - s);
			if (i - j + 1 < len) len = i - j + 1;
			System.out.println("i:" + i);
			System.out.println("len:" + len);
		}

		return len == Integer.MAX_VALUE ? 0 : len;
	}

	private int upper_bound(int[] a, int low, int high, int element) {
		/*
		element = 15 - 7 = 8
		low - 0
		high - 6
		middle - 3
		a[middle] -6

		low - 4
		high - 6
		middle - 5

		a[middle] - 12
		low - 4
		high - 5
		middle - 4

		*/

		/*
		      0  1  2  3  4   5   6
		//7  [0, 2, 5, 6, 8, 12, 15]
		      l        m          h

		                  l   m   h

		                  l   h
		                  m

		                  l
		                  m
		                  h
		*/

		while (low < high) {
			int middle = low + (high - low) / 2;
			if (a[middle] > element) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		System.out.println(new Minimum_Size_Subarray_Sum_209().minSubArrayLenIII(7, new int[] {4, 3, 2, 3, 1, 2}));
	}
}

