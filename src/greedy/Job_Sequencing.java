package greedy;

import java.util.*;
import java.io.*;

class Job_Sequencing {
	private int[] jobScheduling(int[][] jobs, int maxDeadline) {
		if (jobs == null || jobs.length == 0) {
			return null;
		}

		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int [] b) {
				return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
			}
		});

		boolean[] occupancy = new boolean[maxDeadline];
		int maxValue = 0;
		int jobCount = 0;
		for (int[] job : jobs) {
			int index = job[0];

			for (int j = Math.min(maxDeadline - 1, index - 1); j >= 0; j--) {
				if (occupancy[j] == false) {
					maxValue += job[1];
					jobCount++;
					occupancy[j] = true;
					break;
				}
			}
		}
		return new int[] { jobCount, maxValue };
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());

		while (t-- > 0) {
			int length = Integer.parseInt(bf.readLine());
			String input[] = bf.readLine().trim().split(" ");
			int[][] jobs = new int[length][2];
			int maxDeadline = 0;
			for (int i = 0; i < length; i++) {
				jobs[i] = new int[] { Integer.parseInt(input[i * 3 + 1]), Integer.parseInt(input[i * 3 + 2]) };
				if (Integer.parseInt(input[i * 3 + 1]) > maxDeadline) {
					maxDeadline = Integer.parseInt(input[i * 3 + 1]);
				}
			}

			int[] result = new Job_Sequencing().jobScheduling(jobs, maxDeadline);
			StringBuffer sb = new StringBuffer();
			for (int res : result) {
				sb.append(Integer.toString(res) + " ");
			}
			System.out.println(sb);
		}
	}
}