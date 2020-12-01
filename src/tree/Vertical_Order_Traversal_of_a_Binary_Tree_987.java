package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;

/*
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position `(X, Y)`,

its left and right children respectively will be at positions `(X - 1, Y + 1)` and `(X + 1, Y + 1)`.

Running a vertical line from `X = -infinity` to `X = +infinity`,

whenever the vertical line touches some nodes,

we report the values of the nodes in order from top to bottom (decreasing `Y` coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

Example 1:

    3
   / \
  9  20
    / \
   15 7
Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:

     1
   /  \
  2   3
 /\  / \
4 5 6  7

Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Vertical_Order_Traversal_of_a_Binary_Tree_987 {
	Map<Integer, ArrayList<Pair<Integer, Integer>>> map = new HashMap<Integer, ArrayList<Pair<Integer, Integer>>>();

	int maxColumn = 0, minColumn = 0;

	public List<List<Integer>> verticalTraversalBFS(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null) {
			return result;
		}

		BFS(root);

		for (int i = minColumn; i <= maxColumn; ++i) {
			Collections.sort(map.get(i), new Comparator<Pair<Integer, Integer>>() {
				@Override
				public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
					if (p1.getKey().equals(p2.getKey())) {
						return p1.getValue() - p2.getValue();
					} else {
						return p1.getKey() - p2.getKey();
					}
				}
			});

			List<Integer> sortedColumn = new ArrayList<Integer>();

			for (Pair<Integer, Integer> p : map.get(i)) {
				sortedColumn.add(p.getValue());
			}

			result.add(sortedColumn);
		}

		return result;
	}

	private void BFS(TreeNode node) {
		Queue<Pair<TreeNode, Pair<Integer, Integer>>> queue  = new LinkedList<Pair<TreeNode, Pair<Integer, Integer>>>();

		int row = 0, column = 0;

		queue.offer(new Pair<TreeNode, Pair<Integer, Integer>>(node, new Pair<Integer, Integer>(row, column)));

		while (!queue.isEmpty()) {
			Pair<TreeNode, Pair<Integer, Integer>> p = queue.poll();
			TreeNode curr =	p.getKey();
			row = p.getValue().getKey();
			column = p.getValue().getValue();

			if (curr != null) {
				if (!map.containsKey(column)) {
					map.put(column, new ArrayList<Pair<Integer, Integer>>());
				}

				map.get(column).add(new Pair<Integer, Integer>(row, curr.val));

				maxColumn = Math.max(maxColumn, column);
				minColumn = Math.min(minColumn, column);

				queue.offer(new Pair<TreeNode, Pair<Integer, Integer>>(node.left, new Pair<Integer, Integer>(row + 1, column - 1)));
				queue.offer(new Pair<TreeNode, Pair<Integer, Integer>>(node.right, new Pair<Integer, Integer>(row + 1, column + 1)));
			}
		}
	}

	private void DFS(TreeNode node, int row, int column) {
		if (!map.containsKey(column)) {
			map.put(column, new ArrayList<Pair<Integer, Integer>>());
		}

		map.get(column).add(new Pair<Integer, Integer>(row, node.val));

		maxColumn = Math.max(column, maxColumn);
		minColumn = Math.min(column, minColumn);


		if (node.left != null) {
			DFS(node.left, row + 1, column - 1);
		}

		if (node.right != null) {
			DFS(node.right, row + 1, column + 1);
		}
	}

	public List<List<Integer>> verticalTraversalDFS(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null) {
			return result;
		}

		DFS(root, 0, 0);

		for (int i = minColumn; i <= maxColumn; ++i) {
			Collections.sort(map.get(i), new Comparator<Pair<Integer, Integer>>() {
				@Override
				public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
					if (p1.getKey().equals(p2.getKey())) {
						return p1.getValue() - p2.getValue();
					} else {
						return p1.getKey() - p2.getKey();
					}
				}
			});

			List<Integer> sortedColumn = new ArrayList<Integer>();

			for (Pair<Integer, Integer> p : map.get(i)) {
				sortedColumn.add(p.getValue());
			}

			result.add(sortedColumn);
		}

		return result;
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<NodeCoordinate> nodePositionList = new ArrayList<NodeCoordinate>();
		dfs(root, 0, 0, nodePositionList);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Collections.sort(nodePositionList);

		int currentX = nodePositionList.get(0).x;
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(nodePositionList.get(0).val);

		for (int i = 1; i < nodePositionList.size(); i++) {

			if (nodePositionList.get(i).x == currentX) {
				integerList.add(nodePositionList.get(i).val);
			} else {
				result.add(integerList);
				integerList = new ArrayList<Integer>();
				integerList.add(nodePositionList.get(i).val);
				currentX = nodePositionList.get(i).x;
			}
		}

		if (integerList.size() > 0) {
			result.add(integerList);
		}

		return result;
	}

	void dfs(TreeNode root, int x, int y, List<NodeCoordinate> list) {
		if (root == null)
			return;

		list.add(new NodeCoordinate(x, y, root.val));
		dfs(root.left, x - 1, y - 1, list);
		dfs(root.right, x + 1, y - 1, list);
	}

	public static void main(String[] args) {
		TreeNode fifteen = new TreeNode(15);
		TreeNode seven = new TreeNode(7);

		TreeNode twenty = new TreeNode(20);

		twenty.left = fifteen;
		twenty.right = seven;

		TreeNode nine = new TreeNode(9);
		TreeNode three = new TreeNode(3);

		three.left = nine;
		three.right = twenty;

		List<List<Integer>> res = new Vertical_Order_Traversal_of_a_Binary_Tree_987().verticalTraversal(three);

		for (List<Integer> items : res) {
			for (Integer item : items) {
				System.out.println(item);
			}
		}
	}
}

class Pair<T, V> {
	T key;
	V value;

	Pair(T x, V y) {
		this.key = x;
		this.value = y;
	}

	T getKey() {
		return key;
	}

	V getValue() {
		return value;
	}
}

class NodeCoordinate implements Comparable<NodeCoordinate> {
	int x;
	int y;
	int val;

	public NodeCoordinate(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}

	@Override
	public int compareTo(NodeCoordinate n) {
		if (this.x != n.x) {
			return this.x - n.x;
		} else if (this.y != n.y) {
			return n.y - this.y;
		} else {
			return this.val - n.val;
		}
	}
}