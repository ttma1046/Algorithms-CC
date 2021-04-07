package leetcode;
import java.util.List;
import java.util.ArrayList;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]

Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.

You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.

Return a list of boolean, the answers to the given queries.

Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.

Example 1:


Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.

Example 2:

Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.

Example 3:


Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
Example 4:

Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]
Example 5:

Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]

Constraints:

2 <= n <= 100
0 <= prerequisite.length <= (n * (n - 1) / 2)
0 <= prerequisite[i][0], prerequisite[i][1] < n
prerequisite[i][0] != prerequisite[i][1]
The prerequisites graph has no cycles.
The prerequisites graph has no repeated edges.
1 <= queries.length <= 10^4
queries[i][0] != queries[i][1]
*/

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
						// if (map[col][k])
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

	/*
	0 1 2 3 4
	0	0 0 0 0 0
	1	0 0 1 1 0             1 2
	2	0 0 0 1 0
	3	0 0 0 0 0
	4	0 0 0 0 0
	*/

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

		List<Boolean> res = new ArrayList<>();
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

		List<Boolean> res = new ArrayList<>();
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
		/*
		List<Boolean> result = new Course_Schedule_IV_1462().checkIfPrerequisiteFloydWarshall(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}}, new int[][] {{0, 4}, {4, 0}, {1, 3}, {3, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}

		result = new Course_Schedule_IV_1462().checkIfPrerequisiteFloydWarshall(5, new int[][] {{3, 4}, {2, 3}, {1, 2}, {0, 1}}, new int[][] {{0, 4}, {4, 0}, {1, 3}, {3, 0}});
		for (boolean item : result) {
			System.out.println(item);
		}
		*/

		List<Boolean> result = new Course_Schedule_IV_1462().checkIfPrerequisiteFloydWarshall(13,
		new int[][] {{2, 1}, {2, 7}, {2, 0}, {2, 10}, {2, 11}, {1, 7}, {1, 0}, {1, 9}, {1, 4}, {1, 11}, {7, 3}, {7, 9}, {7, 4}, {7, 11}, {7, 8}, {3, 6}, {3, 12}, {3, 5}, {6, 10}, {6, 8}, {0, 4}, {12, 9}, {12, 8}, {9, 4}, {9, 11}, {9, 8}, {9, 5}, {10, 8}, {4, 8}},
		new int[][] {{12, 11}, {11, 1}, {10, 12}, {9, 10}, {10, 11}, {11, 12}, {2, 7}, {6, 8}, {3, 2}, {9, 5}, {8, 7}, {1, 4}, {3, 12}, {9, 6}, {4, 3}, {11, 4}, {5, 7}, {3, 9}, {3, 1}, {8, 12}, {5, 12}, {0, 8}, {10, 5}, {10, 11}, {12, 11}, {12, 9}, {5, 4}, {11, 5}, {12, 10}, {11, 0}, {6, 10}, {11, 7}, {8, 10}, {2, 1}, {3, 4}, {8, 7}, {11, 6}, {9, 11}, {1, 4}, {10, 8}, {7, 1}, {8, 7}, {9, 7}, {5, 1}, {8, 10}, {11, 8}, {8, 12}, {9, 12}, {12, 11}, {6, 12}, {12, 11}, {6, 10}, {9, 12}, {8, 10}, {8, 11}, {8, 5}, {7, 9}, {12, 11}, {11, 12}, {8, 0}, {12, 11}, {7, 0}, {8, 7}, {5, 11}, {11, 8}, {1, 9}, {4, 10}, {11, 6}, {10, 12}});
		for (boolean item : result) {
			System.out.println(item);
		}

		/*
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
		*/
	}
}




































