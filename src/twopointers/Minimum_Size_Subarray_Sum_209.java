package twopointers;

/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

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

				// System.out.println("where is left now:" + left);

				sum -= nums[left++];

				// System.out.println("where is left after:" + left);
			}
		}

		return (ans != Integer.MAX_VALUE) ? ans : 0;
	}

	/*

	Complexity analysis

	Time complexity: O(n)O(n). Single iteration of O(n)O(n).
	Each element can be visited atmost twice, once by the right pointer(ii) and (atmost)once by the \text{left}left pointer.
	Space complexity: O(1)O(1) extra space. Only constant space required for \text{left}left, \text{sum}sum, \text{ans}ans and ii.

	*/

	public static void main(String[] args) {
		new Minimum_Size_Subarray_Sum_209().minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3});
	}

	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		int[] sums = new int[nums.length + 1];
		for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
		int minLen = Integer.MAX_VALUE;

		for (int i = 0; i < sums.length; i++) {
			int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);

			if (end == sums.length) break;

			if (end - i < minLen) minLen = end - i;
		}

		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	private int binarySearch(int lo, int hi, int key, int[] sums) {
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (sums[mid] >= key) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	public int minSubArrayLen(int s, int[] nums) {
		int i = 0, j = 0;
		int len = nums.length;
		int sum = 0;

		while (j < len && sum < s) {
			sum += nums[j];
			j++;
		}

		if (sum < s)
			return 0;

		while (sum - nums[i] >= s) {
			sum -= nums[i];
			i++;
		}

		while (j < len) {
			sum = sum + nums[j] - nums[i];
			j++;
			i++;
			while (sum - nums[i] >= s) {
				sum -= nums[i];
				i++;
			}
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
}

