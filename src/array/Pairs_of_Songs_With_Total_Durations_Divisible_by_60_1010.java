package array;
import java.util.Set;
import java.util.HashSet;
/*
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.

Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.

Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500
*/

class Pairs_of_Songs_With_Total_Durations_Divisible_by_60_1010 {
	public int numPairsDivisibleBy60II(int[] time) {
		int res = 0;

		int[] whatever = new int[] { 60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 660, 720, 780, 840, 900, 960 };

		Map<Integer> set = new HashMap<>();

		for (int i : time) {
			if (set.contains(i)) {
				res++;
			}

			for (int left : whatever) {
				set.add(left - i);
			}
		}

		return res;
	}

	public int numPairsDivisibleBy60(int[] time) {
		int remainders[] = new int[60];

		int count = 0;

		for (int t : time) {
			if (t % 60 == 0) { // check if a % 60 == 0 && b % 60 == 0
				count += remainders[0];
			} else { // check if a % 60 + b % 60 == 60
				count += remainders[60 - t % 60];
			}
			remainders[t % 60]++; // remember to update the remainders
		}

		return count;
	}

	public static void main(String[] args) {
		Pairs_of_Songs_With_Total_Durations_Divisible_by_60_1010 obj = new Pairs_of_Songs_With_Total_Durations_Divisible_by_60_1010();

		int res = obj.numPairsDivisibleBy60(new int[] {30, 20, 150, 100, 400});

		System.out.println(res);

		res = obj.numPairsDivisibleBy60(new int[] {60, 60, 60});

		System.out.println(res);
	}
}