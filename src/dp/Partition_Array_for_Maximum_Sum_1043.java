package dp;

/*
Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k.

After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

Example 1:

Input:
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]

Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83

Example 3:

Input: arr = [1], k = 1
Output: 1

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
*/
class Parition_Array_for_Maximum_Sum_1043 {
	int maxSumAfterPartitioning(int[] a, int k) {
		int n = a.length;

		int[] dp = new int[n + 1];

		// arr = [1,15,7,9,2,5,10], k = 3

		for (int i = 0; i < n; ++i) {
			int interval_max = a[i];
			for (int j = i; j < n && j - i + 1 <= k; ++j) {
				interval_max = Math.max(interval_max, a[j]);
				dp[j + 1] = Math.max(dp[j + 1], dp[i] + (j - i + 1) * interval_max);
			}
		}

		return dp[n];
	}

	//[ 1,15, 7, 9, 2, 5,10] 
	dp[ 0, 0, 0, 0, 0, 0, 0, 0 ]
     
     i = 0
     interval_max = a[i] = 1

     	j = 0
     	interval_max = 1 (a[j] = a[0] = 1, interval_max = 1)

     	dp[j + 1] = dp[1] = 1

     	j = 1
     	interval_max = 15  (a[j] = a[1] = 15, interval_max = 1)

     	dp[j + 1] = dp[2] = Max(dp[2] = 0, dp[0] + (1 - 0 + 1) * 15) = 30 

}