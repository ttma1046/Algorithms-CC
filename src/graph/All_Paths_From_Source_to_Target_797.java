package graph;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Example 2:

Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]

Example 3:

Input: graph = [[1],[]]
Output: [[0,1]]

Example 4:

Input: graph = [[1,2,3],[2],[3],[]]
Output: [[0,1,2,3],[0,2,3],[0,3]]

Example 5:

Input: graph = [[1,3],[2],[3],[]]
Output: [[0,1,2,3],[0,3]]
*/
class All_Paths_From_Source_to_Target_797 {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> list = new ArrayList<Integer>();

		list.add(0);

		traverse(0, graph, list, ans);

		return ans;
	}

	private void traverse(int i, int[][] graph, List<Integer> list, List<List<Integer>> ans) {
		if (i == graph.length - 1) {
			ans.add(new ArrayList<Integer>(list));
			return;
		}

		for (int k : graph[i]) {
			list.add(k);
			traverse(k, graph, list, ans);
			list.remove(list.size() - 1);
		}
	}

	public List<List<Integer>> allPathsSourceTargetII(int[][] graph) {
		List<List<Integer>> result = new ArrayList<>();

		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		dfs(0, graph, list, result);
		return result;
	}

	private void dfs(int index, int[][] graph, List<Integer> list, List<List<Integer>> result) {
		for (int k : graph[index]) {
			List<Integer> newList = new ArrayList<>(list);
			newList.add(k);
			if (k == graph.length - 1) {
				result.add(newList);
			} else {
				dfs(k, graph, newList, result);
			}
		}
	}

	/*
	private HashMap<Integer, List<List<Integer>>> memo;

	protected List<List<Integer>> allPathsToTargetDP(int currNode, int[][] graph, int target) {
	    // memoization. check the result in the cache first
	    if (memo.containsKey(currNode))
	        return memo.get(currNode);

	    List<List<Integer>> results = new ArrayList<>();
	    // base case
	    if (currNode == target) {
	        ArrayList<Integer> path = new ArrayList<>();
	        path.add(target);
	        results.add(path);
	        return results;
	    }

	    // iterate through the paths starting from each neighbor.
	    for (int nextNode : graph[currNode]) {
	        for (List<Integer> path : allPathsToTargetDP(nextNode, graph, target)) {
	            ArrayList<Integer> newPath = new ArrayList<>();
	            newPath.add(currNode);
	            newPath.addAll(path);
	            results.add(newPath);
	        }
	    }

	    memo.put(currNode, results);
	    return results;
	}

	public List<List<Integer>> allPathsSourceTargetDP(int[][] graph) {
	    this.memo = new HashMap<>();

	    return this.allPathsToTargetDP(0, graph, graph.length - 1);
	}
	*/

	private Map<Integer, List<List<Integer>>> memo;

	public List<List<Integer>> allPathsSourceTargetDP(int index, int[][] graph, int target) {
		if (memo.containsKey(index)) return memo.get(index);

		List<List<Integer>> result = new ArrayList<>();

		if (index == target) {
			List<Integer> newList = new ArrayList<>();
			newList.add(index);
			result.add(newList);
			return result;
		}

		for (int i: graph[index]) {
			for (List<Integer> subs : allPathsSourceTargetDP(i, graph, target)) {
				List<Integer> newList = new ArrayList<>();
				newList.add(index);
				newList.addAll(subs);
				result.add(newList);
			}
		}

		memo.put(index, result);

		return result;
	}

	private List<List<Integer>> allPathsSourceTargetDP(int[][] graph) {
		this.memo = new HashMap<>();

		return allPathsSourceTargetDP(0, graph, graph.length - 1);
	}

	public static void main(String[] args) {
		// int[][] graph = new int[][] {{1, 2}, {3}, {3}, {}};
		int[][] graph = new int[][] {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
		List<List<Integer>> result = new All_Paths_From_Source_to_Target_797().allPathsSourceTargetDP(graph);

		for (List<Integer> item : result) {
			for (int k : item) {
				System.out.print(k);
				System.out.print(',');
			}

			System.out.println();
		}

		result = new All_Paths_From_Source_to_Target_797().allPathsSourceTarget(graph);

		for (List<Integer> item : result) {
			for (int k : item) {
				System.out.print(k);
				System.out.print(',');
			}

			System.out.println();
		}

	}
}