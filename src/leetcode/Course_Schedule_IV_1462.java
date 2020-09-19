package leetcode;
import java.util.List;
import java.util.ArrayList;

class Course_Schedule_IV {
	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
		boolean [][] outdegree = new boolean[n][n];
		boolean [][] indegree = new boolean[n][n];

		for (int[] item : prerequisites) {
			outdegree[item[0]][item[1]] = true;

			for (int i  = 0; i < outdegree[item[1]].length; i++) {
				if (outdegree[item[1]][i] == true) {
					outdegree[item[0]][i] = true;
				}
			}

			rec(item[0], item[1], indegree, outdegree);

			indegree[item[1]][item[0]] = true;
		}

		List<Boolean> result = new ArrayList<Boolean>();

		for (int[] query : queries) {
			result.add(outdegree[query[0]][query[1]]);
		}

		return result;
	}

	private void rec(int a, int b, boolean[][] indegree, boolean[][] outdegree) {
		for (int kk = 0; kk < indegree[a].length; kk++) {
			if (indegree[a][kk] == true) {
				outdegree[kk][b] = true;
				rec(kk, b, indegree, outdegree);
			}
		}
	}

	public static void main(String[] args) {
		List<Boolean> result = new Course_Schedule_IV().checkIfPrerequisite(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}}, new int[][] {{0, 4}, {4, 0}, {1, 3}, {3, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV().checkIfPrerequisite(5, new int[][] {{3, 4}, {2, 3}, {1, 2}, {0, 1}}, new int[][] {{0, 4}, {4, 0}, {1, 3}, {3, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV().checkIfPrerequisite(2,
		new int[][] {{1, 0}},
		new int[][] {{0, 1}, {1, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV().checkIfPrerequisite(2,
		        new int[][] {},
		new int[][] {{0, 1}, {1, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV().checkIfPrerequisite(3,
		new int[][] {{ 1, 2 }, { 1, 0 }, { 2, 0 }},
		new int[][] {{ 1, 0 }, { 1, 2 }});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV().checkIfPrerequisite(3,
		new int[][] {{ 1, 0 }, { 2, 0 }},
		new int[][] {{ 0, 1 }, { 2, 0 }});
		for (boolean item : result) {
			System.out.println(item);
		}
	}
}







































