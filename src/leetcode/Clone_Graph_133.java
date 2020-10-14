package leetcode;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:

Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Example 4:

Input: adjList = [[2],[1]]
Output: [[2],[1]]

Constraints:

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
*/
class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {
		val = 0;
		neighbors = new ArrayList<Node>();
	}

	public Node(int _val) {
		val = _val;
		neighbors = new ArrayList<Node>();
	}

	public Node(int _val, ArrayList<Node> _neighbors) {
		val = _val;
		neighbors = _neighbors;
	}
}

class Clone_Graph_133 {
	/* DFS */
	private HashMap<Node, Node> visited = new HashMap<>();

	public Node cloneGraphI(Node node) {
		if (node == null) {
			return node;
		}

		// If the node was already visited before.
		// Return the clone from the visited dictionary.
		if (visited.containsKey(node)) {
			return visited.get(node);
		}

		// Create a clone for the given node.
		// Note that we don't have cloned neighbors as of now, hence [].
		Node cloneNode = new Node(node.val, new ArrayList<>());
		// The key is original node and value being the clone node.
		visited.put(node, cloneNode);

		// Iterate through the neighbors to generate their clones
		// and prepare a list of cloned neighbors to be added to the cloned node.
		for (Node neighbor : node.neighbors) {
			cloneNode.neighbors.add(cloneGraph(neighbor));
		}
		return cloneNode;
	}

	/*
	 * Complexity Analysis Time Complexity: O(N)O(N) since we process each node
	 * exactly once. Space Complexity: O(N)O(N). This space is occupied by the
	 * visited hash map and in addition to that, space would also be occupied by the
	 * recursion stack since we are adopting a recursive approach here. The space
	 * occupied by the recursion stack would be equal to O(H)O(H) where HH is the
	 * height of the graph. Overall, the space complexity would be O(N)O(N).
	 */
	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}

		// Hash map to save the visited node and it's respective clone
		// as key and value respectively. This helps to avoid cycles.
		HashMap<Node, Node> visited = new HashMap<>();

		// Put the first node in the queue
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(node);
		// Clone the node and put it in the visited dictionary.
		visited.put(node, new Node(node.val, new ArrayList<>()));

		// Start BFS traversal
		while (!queue.isEmpty()) {
			// Pop a node say "n" from the from the front of the queue.
			Node n = queue.remove();
			// Iterate through all the neighbors of the node "n"
			for (Node neighbor : n.neighbors) {
				if (!visited.containsKey(neighbor)) {
					// Clone the neighbor and put in the visited, if not present already
					visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
					// Add the newly encountered node to the queue.
					queue.add(neighbor);
				}
				// Add the clone of the neighbor to the neighbors of the clone node "n".
				visited.get(n).neighbors.add(visited.get(neighbor));
			}
		}

		// Return the clone of the node from visited.
		return visited.get(node);
	}

	/*
	 * Complexity Analysis
	 * 
	 * Time Complexity : O(N)O(N) since we process each node exactly once.
	 * 
	 * Space Complexity : O(N)O(N). This space is occupied by the visited dictionary
	 * and in addition to that, space would also be occupied by the queue since we
	 * are adopting the BFS approach here. The space occupied by the queue would be
	 * equal to O(W)O(W) where WW is the width of the graph. Overall, the space
	 * complexity would be O(N)O(N).
	 */
}