package prefix_sum;

/*
We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non - empty) subarrays 

such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input:
A = [2, 1, 4, 3]
L = 2 R = 3
Output: 3

2 i = 0
2 < 3 left = -1
2 >= 2 right = 0; 

result = 0 - -1 = 1

1 i = 1
1 < 3  left = -1
1 < 2  right = 0
result = 1 + 0 - -1 = 2

4 i = 2
4 > 3  left = 2
4 >= 2 right = 2
result = 2 + 0 = 2;

3 i = 3
3 == 3 left = 2 
3 >= 2 right = 3

result = 2 + 3 - 2 = 3


Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Note:

L, R  and A[i] will be an integer in the range [0, 10 ^ 9].
The length of A will be in the range of [1, 50000].
*/

class Number_of_Subarryas_with_Bounded_Maximum_795 {
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		int result = 0, left = -1, right = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > R) left = i;
			if (A[i] >= L) right = i;
			result += right - left;
		}
		return result;
	}

	public int countSubArrayLessThanK(int[] nums, int k)  {
		int ans = 0, pre = 0;
		for (int x : nums) {
			if (x <= k) {
				pre += 1;
			} else {
				pre = 0;
			}

			ans += pre;
		}

		return ans;
	}

	public int myNumSubarrayBoundedMaxII(int[] A, int L, int R) {
		return countSubArrayLessThanK(A, R) - countSubArrayLessThanK(A, L - 1);
	}

	public int numSubarrayBoundedMaxI(int[] A, int L, int R) {
		return count(A, R) - count(A, L - 1);
	}

	public int count(int[] A, int bound) {
		int ans = 0, cur = 0;
		for (int x : A) {
			cur = x <= bound ? cur + 1 : 0;
			ans += cur;
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(new Number_of_Subarryas_with_Bounded_Maximum_795().numSubarrayBoundedMax(new int[] {2, 1, 4, 3}, 2, 3));
		System.out.println(new Number_of_Subarryas_with_Bounded_Maximum_795().numSubarrayBoundedMaxII(new int[] {2, 1, 4, 3}, 2, 3));
	}
}