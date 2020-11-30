/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
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
class Solution {
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> output = new ArrayList();
		if (root == null) {
			return output;
		}

		Map<Integer, ArrayList> columnTable = new HashMap();
		Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
		int column = 0;
		queue.offer(new Pair(root, column));

		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> p = queue.poll();
			root = p.getKey();
			column = p.getValue();

			if (root != null) {
				if (!columnTable.containsKey(column)) {
					columnTable.put(column, new ArrayList<Integer>());
				}
				columnTable.get(column).add(root.val);

				queue.offer(new Pair(root.left, column - 1));
				queue.offer(new Pair(root.right, column + 1));
			}
		}

		List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
		Collections.sort(sortedKeys);
		for (int k : sortedKeys) {
			output.add(columnTable.get(k));
		}

		return output;
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> output = new ArrayList();
		if (root == null) {
			return output;
		}

		Map<Integer, ArrayList> columnTable = new HashMap();
		// Pair of node and its column offset
		Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
		int column = 0;
		queue.offer(new Pair(root, column));

		int minColumn = 0, maxColumn = 0;

		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> p = queue.poll();
			root = p.getKey();
			column = p.getValue();

			if (root != null) {
				if (!columnTable.containsKey(column)) {
					columnTable.put(column, new ArrayList<Integer>());
				}
				columnTable.get(column).add(root.val);
				minColumn = Math.min(minColumn, column);
				maxColumn = Math.max(maxColumn, column);

				queue.offer(new Pair(root.left, column - 1));
				queue.offer(new Pair(root.right, column + 1));
			}
		}

		for (int i = minColumn; i < maxColumn + 1; ++i) {
			output.add(columnTable.get(i));
		}

		return output;
	}

	Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
	int minColumn = 0, maxColumn = 0;

	private void DFS(TreeNode node, Integer row, Integer column) {
		if (node == null)
			return;

		if (!columnTable.containsKey(column)) {
			this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
		}

		this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
		this.minColumn = Math.min(minColumn, column);
		this.maxColumn = Math.max(maxColumn, column);
		// preorder DFS traversal
		this.DFS(node.left, row + 1, column - 1);
		this.DFS(node.right, row + 1, column + 1);
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> output = new ArrayList();
		if (root == null) {
			return output;
		}

		this.DFS(root, 0, 0);

		// Retrieve the resuts, by ordering by column and sorting by row
		for (int i = minColumn; i < maxColumn + 1; ++i) {

			Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
				@Override
				public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
					return p1.getKey() - p2.getKey();
				}
			});

			List<Integer> sortedColumn = new ArrayList();
			for (Pair<Integer, Integer> p : columnTable.get(i)) {
				sortedColumn.add(p.getValue());
			}
			output.add(sortedColumn);
		}

		return output;
	}
}