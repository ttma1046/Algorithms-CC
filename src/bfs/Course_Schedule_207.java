package bfs;

/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.


Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
*/
class Solution {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses]; // i -> j
		int[] indegree = new int[numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			int ready = prerequisites[i][0];
			int pre = prerequisites[i][1];
			if (matrix[pre][ready] == 0)
				indegree[ready]++; //duplicate case
			matrix[pre][ready] = 1;
		}

		int count = 0;
		Queue<Integer> queue = new LinkedList();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) queue.offer(i);
		}
		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (int i = 0; i < numCourses; i++) {
				if (matrix[course][i] != 0) {
					if (--indegree[i] == 0)
						queue.offer(i);
				}
			}
		}
		return count == numCourses;
	}


	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++)
			graph[i] = new ArrayList();

		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}

		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i))
				return false;
		}
		return true;
	}

	private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course])
			return false;
		else
			visited[course] = true;;

		for (int i = 0; i < graph[course].size(); i++) {
			if (!dfs(graph, visited, (int)graph[course].get(i)))
				return false;
		}
		visited[course] = false;
		return true;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		int[] degree = new int[numCourses];
		Queue queue = new LinkedList();
		int count = 0;

		for (int i = 0; i < numCourses; i++)
			graph[i] = new ArrayList();

		for (int i = 0; i < prerequisites.length; i++) {
			degree[prerequisites[i][1]]++;
			graph[prerequisites[i][0]].add(prerequisites[i][1]);
		}
		for (int i = 0; i < degree.length; i++) {
			if (degree[i] == 0) {
				queue.add(i);
				count++;
			}
		}

		while (queue.size() != 0) {
			int course = (int)queue.poll();
			for (int i = 0; i < graph[course].size(); i++) {
				int pointer = (int)graph[course].get(i);
				degree[pointer]--;
				if (degree[pointer] == 0) {
					queue.add(pointer);
					count++;
				}
			}
		}
		if (count == numCourses)
			return true;
		else
			return false;
	}


	public boolean canFinish(int n, int[][] prerequisites) {
		ArrayList<Integer>[] G = new ArrayList[n];
		int[] degree = new int[n];
		ArrayList<Integer> bfs = new ArrayList();
		for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
		for (int[] e : prerequisites) {
			G[e[1]].add(e[0]);
			degree[e[0]]++;
		}
		for (int i = 0; i < n; ++i) if (degree[i] == 0) bfs.add(i);
		for (int i = 0; i < bfs.size(); ++i)
			for (int j : G[bfs.get(i)])
				if (--degree[j] == 0) bfs.add(j);
		return bfs.size() == n;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		// course -> list of next courses
		HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

		// build the graph first
		for (int[] relation : prerequisites) {
			// relation[0] depends on relation[1]
			if (courseDict.containsKey(relation[1])) {
				courseDict.get(relation[1]).add(relation[0]);
			} else {
				List<Integer> nextCourses = new LinkedList<>();
				nextCourses.add(relation[0]);
				courseDict.put(relation[1], nextCourses);
			}
		}

		boolean[] path = new boolean[numCourses];

		for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
			if (this.isCyclic(currCourse, courseDict, path)) {
				return false;
			}
		}

		return true;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List[] graphs = new ArrayList[numCourses];
		if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)return true;
		boolean[]  visited = new boolean[numCourses];
		boolean[]  recStk = new boolean[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graphs[i] = new ArrayList<>();
		}

		for (int i = 0; i < prerequisites.length; i++) {
			graphs[prerequisites[i][1]].add(prerequisites[i][0]);
		}

		for (int i = 0; i < graphs.length; i++) {
			// if(recStk[i])continue;
			if (!dfs(i, visited, recStk, graphs)) {
				return false;
			}
		}

		return true;
	}

	private static boolean dfs(int s, boolean[] visited, boolean[] recStk, List[] graph) {
		if (visited[s]) return false;
		if (recStk[s]) return true;
		visited[s] = true;
		recStk[s] = true;

		for (int i = 0; i < graph[s].size(); i++) {
			if (!dfs((int)graph[s].get(i),  visited, recStk, graph))return false;
		}
		visited[s] = false;
		return true;
	}
}