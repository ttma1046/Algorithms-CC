package tree;
/*

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.



Example 1:


Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:


Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.


Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.

*/


/**
 * Definition for a binary tree node.
 **/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Sum_Root_to_Leaf_Numbers_129 {
    int rootToLeaf = 0;

    public int sumNumbers(TreeNode root) {
    	preorder(root, 0);
    	return rootToLeaf;
	}

    public void preorder(TreeNode node, int exist) {
        if (node != null) {
            exist = exist * 10 + node.val;

            if (r.left == null && r.right == null) rootToLeaf += exist;
            preorder(node.left, exist);
            preorder(node.right, exist);
        }
    }

    public int sumNumbers(TreeNode root) {
		return dfs(root, 0);
    }

    public int dfs(TreeNode node, int current) {
    	if (node == null) return 0;
    	current = 10 * current + node.val;
    	if (node.left == null && node.right == null) return current;
    	return dfs(node.left, current) + dfs(node.right, current);
    }

    public static void main(String[] args) {
        Sum_Root_to_Leaf_Numbers_129 obj = new Sum_Root_to_Leaf_Numbers_129():
    }

    // 11/02/2022
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int exist) {
        exist = node.value + exist * 10;
        if (node.left == null && node.right == null) sum += exist;
        if (node.left != null) dfs(node.left, exist);
        if (node.right != null) dfs(node.right, exist);
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (root == null) return 0;
        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) return currentSum;
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}