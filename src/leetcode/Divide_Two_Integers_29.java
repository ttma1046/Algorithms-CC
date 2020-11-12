package leetcode;
/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note:

Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.


Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
Example 3:

Input: dividend = 0, divisor = 1
Output: 0
Example 4:

Input: dividend = 1, divisor = 1
Output: 1


Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0
*/

/*
Complexity Analysis

Let nn be the absolute value of dividenddividend.

Time Complexity : O(logn).

As we loop over the bits of our dividend, performing an O(1) operation each time, the time complexity is just the number of bits of the dividend: O(logn).

Space Complexity : O(1).

We only use a fixed number of int variables, so the space complexity is O(1).
*/

public class Divide_Two_Integers_29 {
	private static int HALF_INT_MIN = -1073741824;

	public int divide(int dividend, int divisor) {

		// Special cases: overflow.
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		if (dividend == Integer.MIN_VALUE && divisor == 1) {
			return Integer.MIN_VALUE;
		}

		/* We need to convert both numbers to negatives.
		 * Also, we count the number of negatives signs. */
		int negatives = 2;
		if (dividend > 0) {
			negatives--;
			dividend = -dividend;
		}
		if (divisor > 0) {
			negatives--;
			divisor = -divisor;
		}

		/* We want to find the largest doubling of the divisor in the negative 32-bit
		 * integer range that could fit into the dividend.
		 * Note if it would cause an overflow by being less than HALF_INT_MIN,
		 * then we just stop as we know double it would not fit into INT_MIN anyway. */
		int maxBit = 0;
		while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
			maxBit += 1;
			divisor += divisor;
		}

		int quotient = 0;
		/* We start from the biggest bit and shift our divisor to the right
		 * until we can't shift it any further */
		for (int bit = maxBit; bit >= 0; bit--) {
			/* If the divisor fits into the dividend, then we should set the current
			 * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
			 * number of bits. */
			if (divisor >= dividend) {
				quotient -= (1 << bit);
				/* Remove the current divisor from the dividend, as we've now
				 * considered this part. */
				dividend -= divisor;
			}
			/* Shift the divisor to the right so that it's in the right place
			 * for the next positon we're checking at. */
			divisor = (divisor + 1) >> 1;
		}

		/* If there was originally one negative sign, then
		 * the quotient remains negative. Otherwise, switch
		 * it to positive. */
		if (negatives != 1) {
			quotient = -quotient;
		}
		return quotient;
	}

	public int exponentialSearch(int a, int b) {
		int quotient = 0;
		int dividend = a;
		int divisor = b;

		/* Once the divisor is bigger than the current dividend,
		 * we can't fit any more copies of the divisor into it. */
		while (dividend >= divisor) {
			/* Now that we're in the loop, we know it'll fit at least once as
			* divivend >= divisor */
			int powerOfTwo = 1;
			int value = divisor;
			/* Check if double the current value is too big. If not, continue doubling.
			 * If it is too big, stop doubling and continue with the next step */
			while (value + value <= dividend) {
				value += value; // 6
				powerOfTwo += powerOfTwo; // 2
			}

			/*
				1 -> 2 -> 4 -> 8 -> 16 -> 32 -> 64 -> 128 -> 256 -> 512
			*/

			// We have been able to subtract divisor another powerOfTwo times.
			quotient += powerOfTwo; // 2
			// Remove value so far so that we can continue the process with remainder.
			dividend -= value;  // 0
		}

		return quotient;
	}

	public int exponentialSearchII(int a, int b) {
		int quotient = 0;
		int dividend = a;
		int divisor = b;

		/* Once the divisor is bigger than the current dividend,
		 * we can't fit any more copies of the divisor into it. */
		while (dividend >= divisor) {
			/* Now that we're in the loop, we know it'll fit at least once as
			* divivend >= divisor */
			int powerOfTwo = 1;
			int value = divisor;
			/* Check if double the current value is too big. If not, continue doubling.
			 * If it is too big, stop doubling and continue with the next step */
			while (value + value < dividend) {
				value += value; // 6
				powerOfTwo += powerOfTwo; // 2
			}

			/*
				1 -> 2 -> 4 -> 8 -> 16 -> 32 -> 64 -> 128 -> 256 -> 512
			*/

			// We have been able to subtract divisor another powerOfTwo times.
			quotient += powerOfTwo; // 2
			// Remove value so far so that we can continue the process with remainder.
			dividend -= value;  // 0
		}

		return quotient;
	}

	public int divideByHighestDouble(int a, int b) {
		/* In the first loop, we simply find the largest double of divisor. This is
		* very similar to the start of what we did in Approach 2. */
		int dividend = a;
		int divisor = b;

		int highestDouble = divisor;
		int highestPowerOfTwo = 1;
		while (highestDouble + highestDouble <= dividend) {
			highestPowerOfTwo += highestPowerOfTwo;
			highestDouble += highestDouble;
		}

		/* In the second loop, we work out which powers of two fit in, by
		 * halving highestDouble and highestPowerOfTwo repeatedly. */
		int quotient = 0;
		while (divisor <= dividend) {
			if (dividend >= highestDouble) {
				quotient += highestPowerOfTwo;
				dividend -= highestDouble;
			}
			highestPowerOfTwo >>= 1;
			highestDouble >>= 1;
		}

		return quotient;
	}


	public static void main(String[] args) {
		// System.out.println(new Divide_Two_Integers_29().exponentialSearch(93706, 157));
		System.out.println(new Divide_Two_Integers_29().divideByHighestDouble(93706, 157));


		int test = 4;
		System.out.println(test >> 1);
		System.out.println(test);
		System.out.println(test >>= 1);
		/*
		System.out.println(new Divide_Two_Integers_29().exponentialSearch(6, 3));
		System.out.println(new Divide_Two_Integers_29().exponentialSearch(5, 3));
		System.out.println(new Divide_Two_Integers_29().exponentialSearch(7, 3));

		System.out.println(new Divide_Two_Integers_29().exponentialSearchII(6, 3));
		System.out.println(new Divide_Two_Integers_29().exponentialSearchII(5, 3));
		System.out.println(new Divide_Two_Integers_29().exponentialSearchII(7, 3));
		*/
	}
}