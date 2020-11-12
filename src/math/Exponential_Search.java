package math;

public class Exponential_Search {
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
			while (value + value < dividend) {
				value += value;
				powerOfTwo += powerOfTwo;
			}

			/*
				1 -> 2 -> 4 -> 8 -> 16 -> 32 -> 64 -> 128 -> 256 -> 512
			*/

			// We have been able to subtract divisor another powerOfTwo times.
			quotient += powerOfTwo;
			// Remove value so far so that we can continue the process with remainder.
			dividend -= value;
		}

		return quotient;
	}

	public static void main(String[] args) {
		System.out.println(new Divide_Two_Integers_29().exponentialSearch(93706, 157));

		System.out.println(new Divide_Two_Integers_29().exponentialSearch(6, 3));
	}
}