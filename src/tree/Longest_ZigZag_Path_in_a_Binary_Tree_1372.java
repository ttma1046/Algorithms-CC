package tree;
/*
You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

Example 1:

Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

Example 2:

Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

Example 3:

Input: root = [1]
Output: 0

Constraints:

The number of nodes in the tree is in the range [1, 5 * 104].
1 <= Node.val <= 100
*/

class Pair {
	int left = 0;
	int right = 0;

	Pair(int l, int r) {
		this.left = l;
		this.right = r;
	}
}

class Longest_ZigZag_Path_in_a_Binary_Tree_1372 {
	int maxLength = 0;

	public int longestZigZag(TreeNode root) {
		dfs(root);

		return maxLength;
	}

	private Pair dfs(TreeNode node) {
		if (node == null) return new Pair(0, 0);

		Pair leftSide =	dfs(node.left);
		Pair rightSide = dfs(node.right);

	 	maxLength = Math.max(maxLength, Math.max(leftSide.right + 1, rightSide.left + 1));

	 	return new Pair(leftSide.right + 1, rightSide.left + 1);
	}

    public static void main(String[] args) {
    	Longest_ZigZag_Path_in_a_Binary_Tree_1372 obj = new Longest_ZigZag_Path_in_a_Binary_Tree_1372();
    	
    }
}