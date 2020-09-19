package leetcode;
import java.util.List;
import java.util.ArrayList;

class Course_Schedule_IV_1462 {
	/*
	Floyd–Warshall Algorithm
	*/
	public List<Boolean> checkIfPrerequisiteFloydWarshall(int n, int[][] prerequisites, int[][] queries) {
		boolean[][] connected = new boolean[n][n];

		for (int[] p : prerequisites)  {
			connected[p[0]][p[1]] = true; // p[0] -> p[1]
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
				}
			}
		}

		List<Boolean> ans = new ArrayList<>();
		for (int[] q : queries) {
			ans.add(connected[q[0]][q[1]]);
		}
		return ans;
	}


	public List<Boolean> checkIfPrerequisiteFastest(int n, int[][] prerequisites, int[][] queries) {
		boolean[][] map = new boolean[n][n];

		for (int[] i : prerequisites) {
			map[i[1]][i[0]] = true;
			// map[i[0]][i[1]] = true;
		}

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (map[row][col]) {
					for (int k = 0; k < n; k++) {
						// if (map[col][k]) {
						// map[row][k] = true;
						if (map[k][row]) {
							map[k][col] = true;
						}
					}
				}
			}
		}

		List<Boolean> result = new ArrayList<>(queries.length);

		for (int[] i : queries) {
			// result.add(map[i[0]][i[1]]);
			result.add(map[i[1]][i[0]]);
		}

		return result;
	}

	public List<Boolean> checkIfPrerequisiteII(int n, int[][] prerequisites, int[][] queries) {
		ArrayList<Integer>[] graph = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int[] prerequisite : prerequisites) {
			graph[prerequisite[1]].add(prerequisite[0]);
		}

		boolean[][] dp = new boolean[n][n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			dfs(graph, i, dp, visited);
		}

		List<Boolean> res = new ArrayList();
		for (int[] query : queries) {
			res.add(dp[query[1]][query[0]]);
		}
		return res;
	}

	void dfs(ArrayList<Integer>[] graph, int index, boolean[][] dp, boolean[] visited) {
		if (visited[index]) {
			return;
		}

		for (int dependency : graph[index]) {
			dfs(graph, dependency, dp, visited);
			dp[index][dependency] = true;
			for (int i = 0; i < visited.length; i++) {
				dp[index][i] = dp[index][i] || dp[dependency][i];
			}
		}
		visited[index] = true;
	}

	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
		boolean[][] graph = new boolean[n][n];

		for (int[] prerequisite : prerequisites) {
			graph[prerequisite[1]][prerequisite[0]] = true;
		}

		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			dfs(graph, i, visited);
		}

		List<Boolean> res = new ArrayList();
		for (int[] query : queries) {
			res.add(graph[query[1]][query[0]]);
		}
		return res;
	}

	void dfs(boolean[][] graph, int index, boolean[] visited) {
		if (visited[index]) {
			return;
		}

		for (int j = 0; j < visited.length; j++) {
			if (graph[index][j] == true)  {
				dfs(graph, j, visited);
				for (int i = 0; i < visited.length; i++) {
					graph[index][i] |= graph[j][i];
				}
			}
		}
		visited[index] = true;
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




































