package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position `(X, Y)`,

its left and right children respectively will be at positions `(X-1, Y-1)` and `(X+1, Y-1)`.

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
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root != null) rec(root, res);

		return res;
	}

	private void rec(TreeNode node, List<List<Integer>> res) {
		if (node.left != null) rec(node.left, res);
		List<Integer> list = new ArrayList<Integer>();
		list.add(node.val);
		res.add(list);
	
		if (node.right != null) rec(node.right, res);
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
