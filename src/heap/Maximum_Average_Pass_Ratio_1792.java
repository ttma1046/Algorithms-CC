package heap;
import java.util.PriorityQueue;

/*
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485

Constraints:

1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
*/

class ClassInfo {
	int pass;
	int total;
	double opti;

	public ClassInfo(int x, int y, double z) {
		pass = x;
		total = y;
		opti = z;
	}
}


class Maximum_Average_Pass_Ratio_1792 {
	public double maxAverageRatio(int[][] classes, int extraStudents) {
		PriorityQueue<ClassInfo> pq = new PriorityQueue<>((x, y) -> Double.compare(y.opti, x.opti));

		int pass = 0;
		int total = 0;

		for (int[] c : classes) {
			pass = c[0];
			total = c[1];

			pq.offer(new ClassInfo(pass, total, (double)(pass + 1) / (double)(total + 1) - (double)(pass) / (double)(total)));
		}

		double res = 0.0;

		while (!pq.isEmpty() && extraStudents != 0) {
			ClassInfo kk = pq.poll();

			if (kk.pass == kk.total) {
				res += 1.0;
			} else {
				pq.offer(new ClassInfo(kk.pass + 1, kk.total + 1, (double)(kk.pass + 2) / (double)(kk.total + 2) - (double)(kk.pass + 1) / (double)(kk.total + 1)));
				extraStudents--;
			}
		}

		while (!pq.isEmpty()) {
			ClassInfo pp = pq.poll();
			res += (double)pp.pass / (double)pp.total ;
		}

		return res / classes.length;
	}

	public static void main(String[] args) {
		Maximum_Average_Pass_Ratio_1792 obj = new Maximum_Average_Pass_Ratio_1792();

		int[][] classes = new int[][] {{1, 2}, {3, 5}, {2, 2}};
		int extraStudents = 2;

		System.out.println(obj.maxAverageRatio(classes, extraStudents));

		classes = new int[][] {{2, 4}, {3, 9}, {4, 5}, {2, 10}};
		extraStudents = 4;

		System.out.println(obj.maxAverageRatio(classes, extraStudents));
	}
}