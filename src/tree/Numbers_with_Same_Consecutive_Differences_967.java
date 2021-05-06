package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
/*

Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.

Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.

You may return the answer in any order.

Example 1:

Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.

Example 2:

Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Example 3:

Input: n = 2, k = 0
Output: [11,22,33,44,55,66,77,88,99]

Example 4:

Input: n = 2, k = 2
Output: [13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]

Constraints:

2 <= n <= 9
0 <= k <= 9
*/

class Numbers_with_Same_Consecutive_Differences_967 {
	public int[] numsSameConsecDiffDFS(int n, int k) {
		if (n == 1) return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		List<Integer> results = new ArrayList<Integer>();
		for (int num = 1; num < 10; ++num) DFS(n - 1, num, k, results);

		int[] res = new int[results.size()];
		for (int i = 0; i < results.size(); i++) res[i] = results.get(i);
		return res;
	}

	private void DFS(int n, int num, int k, List<Integer> results) {
		if (n == 0) {
			results.add(num);
			return;
		}

		int tail = num % 10;
		List<Integer> nextDigits = new ArrayList<>();

		if (tail + k >= 0 && tail + k <= 9) nextDigits.add(tail + k);
		if (k != 0 && tail - k >= 0 && tail - k <= 9) nextDigits.add(tail - k);

		for (int nextDigit : nextDigits) {
			DFS(n - 1, num * 10 + nextDigit, k, results);
		}
	}


	public int[] numsSameConsecDiffBFS(int N, int K) {
		if (N == 1)
			return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		for (int level = 1; level < N; ++ level) {
			ArrayList<Integer> nextQueue = new ArrayList<>();
			// iterate through each number within the level
			for (Integer num : queue) {
				Integer tailDigit = num % 10;

				ArrayList<Integer> nextDigits = new ArrayList<>();
				nextDigits.add(tailDigit + K);
				if (K != 0)
					nextDigits.add(tailDigit - K);
				for (Integer nextDigit : nextDigits) {
					if (0 <= nextDigit && nextDigit < 10) {
						Integer newNum = num * 10 + nextDigit;
						nextQueue.add(newNum);
					}
				}
			}
			// prepare for the next level
			queue = nextQueue;
		}

		int[] res = new int[queue.size()];
		for (int i = 0; i < queue.size(); i++) res[i] = queue.get(i);
		return res;
	}

	public int[] numsSameConsecDiff(int n, int k) {
		if (n == 1) return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		Queue<Integer> queue = new LinkedList<>();
		int size = 0;

		for (int num = 1; num < 10; ++num) queue.offer(num);
		for (int i = 1; i < n; ++i) {
			size = queue.size();
			for (int m = 0; m < size; m++) {
				int j = queue.poll();
				int tail = j % 10;
				if (tail + k >= 0 && tail + k <= 9) queue.offer(j * 10 + tail + k);
				if (k != 0 && tail - k >= 0 && tail - k <= 9) queue.offer(j * 10 + tail - k);
			}
		}
		
		size = queue.size();
		int[] res = new int[size];
		for (int i = 0; i < size; i++) res[i] = queue.poll();
		return res;
	}

	public static void main(String[] args) {
		Numbers_with_Same_Consecutive_Differences_967 obj = new Numbers_with_Same_Consecutive_Differences_967();

		int[] res = obj.numsSameConsecDiff(3, 7);

		for (int i : res) {
			System.out.print(i);
			System.out.print(",");
		}

		System.out.println();

		res = obj.numsSameConsecDiff(2, 1);

		for (int i : res) {
			System.out.print(i);
			System.out.print(",");
		}

		System.out.println();

		res = obj.numsSameConsecDiff(2, 0);

		for (int i : res) {
			System.out.print(i);
			System.out.print(",");
		}

		System.out.println();

		res = obj.numsSameConsecDiff(2, 2);

		for (int i : res) {
			System.out.print(i);
			System.out.print(",");
		}

		System.out.println();
	}


}