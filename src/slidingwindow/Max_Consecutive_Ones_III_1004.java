package slidingwindow;
/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1

two pointers and sliding window
*/
class Max_Consecutive_Ones_III_1004 {
	public int longestOnes(int[] A, int K) {
		int left = 0, right;
		for (right = 0; right < A.length; ++right) {
			if (A[right] == 0) K--;
			if (K < 0) {
				// If the left element to be thrown out is zero we increase K.
				if (A[left] == 0) K++;
				left++;
			}
		}

		return right - left;
	}

	public static void main(String[] args) {
		System.out.println(new Max_Consecutive_Ones_III_1004().longestOnes(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
	}
}
/*
0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
|
l
r
K = 2

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
| |
l r
K = 2

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
|   |
l   r
K = 2

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
|     |
l     r
K: 2 => 1

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
|       |
l       r
K: 1 => 0

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
  |       |
  l       r
K: 0 => -1

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
    |       |
    l       r
K = -1

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
      |       |
      l       r
K = -1

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
        |       |
        l       r
K = 0

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
        |         |
        i         j
K = 0

0 1 2 3 4 5 6 7 8 9 10
1,1,1,0,0,0,1,1,1,1,0
          |         |
          i         j
K: 0 => -1 => 0
*/