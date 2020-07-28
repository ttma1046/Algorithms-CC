package greedy;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
	You are given n activities with their start and finish times.
	Select the maximum number of activities that can be performed by a single person,
	assuming that a person can only work on a single activity at a time.
*/

/*
	There is one meeting room in a firm. There are N meetings in the form of (S[i], F[i]) where S[i] is start time of meeting i and F[i] is finish time of meeting i.

	What is the maximum number of meetings that can be accommodated in the meeting room?

	Input:
	The first line of input consists number of the test cases. The description of T test cases is as follows:
	The first line consists of the size of the array, second line has the array containing the starting time of all the meetings each separated by a space, i.e., S [ i ]. And the third line has the array containing the finishing time of all the meetings each separated by a space, i.e., F [ i ].

	Output:
	In each separate line print the order in which the meetings take place separated by a space.

	Constraints:
	1 ≤ T ≤ 70
	1 ≤ N ≤ 100
	1 ≤ S[i], F[i] ≤ 100000

	Example:
	Input:
	2
	6
	1 3 0 5 8 5
	2 4 6 7 9 9
	8
	75250 50074 43659 8931 11273 27545 50879 77924
	112960 114515 81825 93424 54316 35533 73383 160252

	Output:
    1 2 4 5
    6 7 1
*/
/*
O(nlogn)
*/

class Activity_Selection {
	public List<Integer> solution(int[] starts, int[] ends) {
		if (starts == null || starts.length <= 0 || ends == null || ends.length <= 0) {
			return new ArrayList<Integer>();
		}
		int length = starts.length;
		int [][] temp = new int[length][3];
		for (int i = 0; i < length; i++) {
			temp[i] = (new int[] {starts[i], ends[i], i + 1});
		}

		Arrays.sort(temp, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] != b[1] ? a[1] - b[1] : a[0] - b[0];
			}
		});

		int j = 0;
		List<Integer> result = new ArrayList<Integer>();
		result.add(temp[j][2]);

		for (int i = 1; i < length; i++) {
			if (temp[j][1] <= temp[i][0]) {
				result.add(temp[i][2]);

				j = i;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> result = new Activity_Selection().solution(new int[] {10, 12, 20}, new int[] {20, 25, 30});
		for (int item : result) {
			System.out.println(item);
		}
		result = new Activity_Selection().solution(new int[] {1, 3, 0, 5, 8, 5}, new int[] {2, 4, 6, 7, 9, 9});
		for (int item : result) {
			System.out.println(item);
		}
		result = new Activity_Selection().solution(new int[] {1}, new int[] {2});
		for (int item : result) {
			System.out.println(item);
		}
		result = new Activity_Selection().solution(
		    new int[] { 75250, 50074, 43659, 8931, 11273, 27545, 50879, 77924 },
		    new int[] { 112960, 114515, 81825, 93424, 54316, 35533, 73383, 160252 });
		for (int item : result) {
			System.out.println(item);
		}
	}

	public static void test(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		while (t-- > 0) {
			int length = Integer.parseInt(bf.readLine());
			String s[] = bf.readLine().trim().split(" ");
			String f[] = bf.readLine().trim().split(" ");

			int [][] temp = new int[length][3];
			for (int i = 0; i < length; i++) {
				temp[i] = (new int[] {Integer.parseInt(s[i]), Integer.parseInt(f[i]), i + 1});
			}

			Arrays.sort(temp, new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return a[1] != b[1] ? a[1] - b[1] : a[0] - b[0];
				}
			});

			int j = 0;
			StringBuffer sb = new StringBuffer();
			sb.append(Integer.toString(temp[j][2]) + " ");

			for (int i = 1; i < length; i++) {
				if (temp[j][1] <= temp[i][0]) {
					sb.append(Integer.toString(temp[i][2]) + " ");
					j = i;
				}
			}

			System.out.println(sb);
		}
	}
}