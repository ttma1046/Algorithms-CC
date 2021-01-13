package twopointers;

/*
Intuition
Have you read this?
992. Subarrays with K Different Integers

Explanation
Exactly K times = at most K times - at most K - 1 times

Complexity
Time O(N) for one pass
Space O(1)

Java:
*/

class Count_Number_of_Nice_Subarrays_1248 {
	public int numberOfSubarraysIII(int[] nums, int k) {
		int n = nums.length;

		int[] cnt = new int[n + 1];

		int odd = 0, ans = 0;

		cnt[0] = 1;

		for (int i = 0; i < n; ++i) {
			if (nums[i] % 2 == 1) {
				odd++;
			}

			ans += odd >= k ? cnt[odd - k] : 0;
			cnt[odd] += 1;
		}

		for(int i = 0; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}

		return ans;
	}

	public int numberOfSubarrays(int[] nums, int k) {
		int left = 0, right = 0, n = nums.length, count = 0;

		int ans = 0;
		// nums = [2,2,2,1,2,2,1,2,2,2], k = 2
		for (right = 0; right < n; ++right) {
			if (nums[right] % 2 == 1) {
				k--;
				count = 0;
			}

			while (k == 0) {
				if (nums[left] % 2 == 1) {
					k++;
				}

				count++;
				left++;
			}
			ans += count;
		}

		return ans;
	}

	public int numberOfSubarraysII(int[] A, int k) {
		return atMost(A, k) - atMost(A, k - 1);
	}

	public int atMost(int[] A, int k) {
		int res = 0, i = 0, n = A.length;
		for (int j = 0; j < n; j++) {
			k -= A[j] % 2;
			while (k < 0)
				k += A[i++] % 2;

			System.out.println("res += " + (j - i + 1));
			res += j - i + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new Count_Number_of_Nice_Subarrays_1248().numberOfSubarraysIII(
		                       new int[] {2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2)
		                  );
	}
}

/*
More Similar Sliding Window Problems
Here are some similar sliding window problems.
Also find more explanations.
Good luck and have fun.

Number of Substrings Containing All Three Characters
Count Number of Nice Subarrays
Replace the Substring for Balanced String
Max Consecutive Ones III
Binary Subarrays With Sum
Subarrays with K Different Integers
Fruit Into Baskets
Shortest Subarray with Sum at Least K
Minimum Size Subarray Sum
*/
