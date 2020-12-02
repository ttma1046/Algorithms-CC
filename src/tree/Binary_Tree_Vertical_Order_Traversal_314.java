package tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;

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
class Binary_Tree_Vertical_Order_Traversal_314 {
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

		List<List<Integer>> res = new Binary_Tree_Vertical_Order_Traversal_314().verticalOrderMyDFS(three);

		for (List<Integer> items : res) {
			for (Integer item : items) {
				System.out.println(item);
			}
		}


		res = new Binary_Tree_Vertical_Order_Traversal_314().verticalOrderMyBFS(three);

		for (List<Integer> items : res) {
			for (Integer item : items) {
				System.out.print(item);
				System.out.print(",");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> verticalOrderMyBFS(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();

		if (root == null) {
			return output;
		}

		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

		int column = 0;
		int minColumn = 0, maxColumn = 0;

		queue.offer(new Pair<TreeNode, Integer>(root, column));

		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> p = queue.poll();
			TreeNode curr = p.getKey();
			column = p.getValue();

			if (curr != null) {
				if (!map.containsKey(column)) {
					map.put(column, new ArrayList<Integer>());
				}

				map.get(column).add(curr.val);

				maxColumn = Math.max(maxColumn, column);
				minColumn = Math.min(minColumn, column);

				queue.offer(new Pair<TreeNode, Integer>(curr.left, column - 1));
				queue.offer(new Pair<TreeNode, Integer>(curr.right, column + 1));
			}
		}

		for (int i = minColumn; i < maxColumn + 1; ++i) {
			output.add(map.get(i));
		}

		return output;
	}

	Map<Integer, ArrayList<Integer>> map = new HashMap<>();

	public List<List<Integer>> verticalOrderMyDFS(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();

		if (root == null) {
			return output;
		}

		int minColumn = 0, maxColumn = 0;

		DFS(root, 0);

		for (int i = minColumn; i <= maxColumn; ++i) {
			output.add(map.get(i));
		}

		return output;
	}

	private void DFS(TreeNode node, int column) {
		if (!map.containsKey(column)) {
			map.put(column, new ArrayList<Integer>());
		}

		map.get(column).add(node.val);

		maxColumn = Math.max(maxColumn, column);
		minColumn = Math.min(minColumn, column);


		if (node.left != null) {
			DFS(node.left, column - 1);
		}

		if (node.right != null) {
			DFS(node.right, column + 1);
		}
	}

	public List<List<Integer>> verticalOrderII(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();
		if (root == null) {
			return output;
		}

		Map<Integer, ArrayList<Integer>> columnTable = new HashMap<>();
		Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
		int column = 0;
		queue.offer(new Pair<TreeNode, Integer>(root, column));

		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> p = queue.poll();
			root = p.getKey();
			column = p.getValue();

			if (root != null) {
				if (!columnTable.containsKey(column)) {
					columnTable.put(column, new ArrayList<Integer>());
				}
				columnTable.get(column).add(root.val);

				queue.offer(new Pair<TreeNode, Integer>(root.left, column - 1));
				queue.offer(new Pair<TreeNode, Integer>(root.right, column + 1));
			}
		}

		List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
		Collections.sort(sortedKeys);
		for (int k : sortedKeys) {
			output.add(columnTable.get(k));
		}

		return output;
	}

	public List<List<Integer>> verticalOrderIII(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();
		if (root == null) {
			return output;
		}

		Map<Integer, ArrayList<Integer>> columnTable = new HashMap<>();
		// Pair of node and its column offset
		Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
		int column = 0;
		queue.offer(new Pair<TreeNode, Integer>(root, column));

		int minColumn =  0, maxColumn = 0;

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

				queue.offer(new Pair<TreeNode, Integer>(root.left, column - 1));
				queue.offer(new Pair<TreeNode, Integer>(root.right, column + 1));
			}
		}

		for (int i = minColumn; i < maxColumn + 1; ++i) {
			output.add(columnTable.get(i));
		}

		return output;
	}

	Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap<>();
	int minColumn = 0, maxColumn = 0;

	private void DFSII(TreeNode node, Integer row, Integer column) {
		if (node == null)
			return;

		if (!columnTable.containsKey(column)) {
			this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
		}

		this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
		this.minColumn = Math.min(minColumn, column);
		this.maxColumn = Math.max(maxColumn, column);
		// preorder DFS traversal
		this.DFSII(node.left, row + 1, column - 1);
		this.DFSII(node.right, row + 1, column + 1);
	}

	public List<List<Integer>> verticalOrderIV(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();
		if (root == null) {
			return output;
		}

		this.DFSII(root, 0, 0);

		// Retrieve the resuts, by ordering by column and sorting by row
		for (int i = minColumn; i < maxColumn + 1; ++i) {
			Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
				@Override
				public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
					return p1.getKey() - p2.getKey();
				}
			});

			List<Integer> sortedColumn = new ArrayList<>();
			for (Pair<Integer, Integer> p : columnTable.get(i)) {
				sortedColumn.add(p.getValue());
			}
			output.add(sortedColumn);
		}

		return output;
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