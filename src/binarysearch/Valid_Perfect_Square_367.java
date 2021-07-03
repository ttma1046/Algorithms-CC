package binarysearch;

/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.



Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false


Constraints:

1 <= num <= 2^31 - 1
*/
class Valid_Perect_Square_367 {
	public boolean isPerfectSquare(int num) {
		if (num < 2) return true;

		long left = 2, right = num / 2, mid, t;
		while (left <= right) {
			mid = left + (right - left) / 2;
			t = mid * mid;
			if (t == num) return true;
			else if (t > num) right = mid - 1;
			else left = mid + 1;
		}

		return false;
	}

	public static void main(String[] args) {
		Valid_Perect_Square_367 obj = new Valid_Perect_Square_367();

		System.out.println(obj.isPerfectSquare(16));
	}
}