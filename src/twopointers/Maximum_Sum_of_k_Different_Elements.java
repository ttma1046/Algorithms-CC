package twopointers;

/*
Given an array of integers of size ‘n’.
Our aim is to calculate the maximum sum of ‘k’
consecutive elements in the array.

Input  : arr[] = {100, 200, 300, 400}
         k = 2
Output : 700

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         k = 4
Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23}
of size 4.

Input  : arr[] = {2, 3}
         k = 3
Output : Invalid
There is no subarray of size 3 as size of whole
array is 2.
*/

class Maximum_Sum_of_k_Different_Elements {
	// Returns maximum sum in
	// a subarray of size k.
	// Brute Force Approach
	int maxSum(int arr[], int n, int k) {
		// Initialize result
		int max_sum = Integer.MIN_VALUE;

		// Consider all blocks starting with i.
		for (int i = 0; i < n - k + 1; i++) {
			int current_sum = 0;
			for (int j = 0; j < k; j++)
				current_sum = current_sum + arr[i + j];

			// Update result if required.
			max_sum = Math.max(current_sum, max_sum);
		}

		return max_sum;
	}

	int maxSum(int arr[], int n, int k) {
		if (n < k) {
			return -1;
		}

		int max_sum = 0;
		for (int i = 0; i < k; i++) max_sum += arr[i];

		int window_sum = max_sum;

		for (int i = k; i < n; i++) {
			window_sum += arr[i] - arr[i - k];
			if (window_sum > max_sum) {
				max_sum = window_sum;
			}
		}

		return max_sum;
	}

	// Driver code
	public static void main(String[] args) {
		int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
		int k = 4;
		int n = arr.length;
		System.out.println(new Maximum_Sum_of_k_Different_Elements().maxSum(arr, n, k));
	}
}