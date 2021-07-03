package binarysearch;
/*
Implement pow(x, n), which calculates x raised to the power n (i.e. xn).

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
-104 <= xn <= 104
*/
public class Pow_50 {

	/*
	Intuition

	Using the formula x ^ {a + b} = x ^ a * x ^ b, we can write n as a sum of positive integers, n = \sum_i b_i. If we can get the result of x ^ {b_i} quickly, the total time for computing x ^ n will be reduced.

	Algorithm

	We can use the binary representation of n to better understand the problem. Let the binary representation of n to be b_1, b_2, ..., b_{length\_limit}, from the Least Significant Bit(LSB) to the Most Significant Bit(MSB). For the ith bit, if b_i = 1, it means we need to multiply the result by x ^ {2 ^ i}.

	It seems to have no improvement with this representation, since \sum_i b_i * 2 ^ i = n. But using the formula (x ^ n) ^ 2 = x ^ {2 * n} we mentioned above, we can see some differences. Initially x ^ 1 = x, and for each i > 1, we can use the result of x ^ {2 ^ {i - 1}} to get x ^ {2 ^ i}	in one step. Since the number of b_i is at most O(logn), we can get all x ^ {2 ^ i}	in O(logn) time. After that, for all is that satisfy b_i = 1, we can multiply x ^ {2 ^ i} to the result. This also requires O(log n)time.

	Using fast power recursively or iteratively are actually taking different paths towards the same goal. For more information about fast power algorithm, you can visit its wiki[1].

	3 10


	3 => 3 ^ 2
	10 => 5


	result = 3^2

	3 ^ 2 => 3 ^ 4
	5 => 2


	3 ^ 4 => 3 ^ 8
	2 => 1

	result = 3^2 * 3^8 = 3^10
	3^8 => 3^16
	1 => 0
	*/
	public double fastPow(double x, long n) {
		if (n == 0) {
			return 1.0;
		}

		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}


	public double myPowII(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}

		return fastPow(x, N);
	}

	/*
	Time complexity : O(n). We will multiply x for n times.

	Space complexity : O(1). We only need one variable to store the final product of x.
	*/
	public double myPow(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		while (N-- > 0) {
			ans * x;
		}
		return ans;
	}

	/*
	public int power(int a, int b) {
		if (b == 0) return 1;

		int tmp = power(a, b / 2);

		int reulst = tmp * tmp;

		if (b & 2 == 1) result *= a;

		return result;
	}
	*/

	public double power(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}

		double ans = 1;
		double current_product = x;
		while (N > 0) {
			if (N % 2 == 1) ans *= current_product;
			current_product *= current_product;
			N /= 2;
		}
		return ans;
	}
	/*
	Time complexity : O(logn). Each time we apply the formula (x ^ n) ^ 2 = x ^ {2 * n}, 
	n is reduced by half. Thus we need at most O(logn) computations to get the result.

	Space complexity : O(logn). 
	For each computation, we need to store the result of x ^ {n / 2}. 
	We need to do the computation for O(logn) times, so the space complexity is O(logn).
	*/

	public static void main(String[] args) {
		System.out.println(new Pow_50().myPow(2, 10));

		System.out.println(new Pow_50().myPow(2.1, 3));

		System.out.println(new Pow_50().myPow(2, -2));
	}
}
