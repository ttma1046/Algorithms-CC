package twopointers;
/*
There are n flights, and they are labeled from 1 to n.

We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.

Return an array answer of length n, representing the number of seats booked on each flight in order of their label.



Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]


Constraints:

1 <= bookings.length <= 20000
1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
1 <= bookings[i][2] <= 10000
*/
class Corporate_Flight_Bookings_1109 {
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] result = new int[n];
		for (int[] x : bookings) {

			for (int i = x[0]; i <= x[1]; i++) {
				result[i - 1] += x[2];
			}

		}

		return result;
	}

	public int[] corpFlightBookingsII(int[][] bookings, int n) {
		int[] result = new int[n];
		for (int[] b : bookings) {
			result[b[0] - 1] += b[2];
			if (b[1] < n) result[b[1]] -= b[2];
		}
		for (int i = 1; i < n; ++i) result[i] += result[i - 1];
		return result;
	}



	public int[] corpFlightBookingsIII(int[][] bookings, int n) {
		int[] result = new int[n];

		for(int[] x: bookings) {
			result[x[0] - 1] += x[2];

			if (x[1] < n) result[x[1]] -= x[2];
		}
		
		for (int i = 1; i < n; ++i) {
			result[i] += result[i - 1];
		}

		return result;
	}

	public static void main(String[] args) {
		int[] res = new Corporate_Flight_Bookings_1109().corpFlightBookings(
		new int[][] {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5);
		for (int x : res) {
			System.out.println(x);
		}
	}
}