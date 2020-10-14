package dfs;

/*
 * In a binary tree, the root node is at depth 0, and children of each depth k
 * node are at depth k+1.
 * 
 * Two nodes of a binary tree are cousins if they have the same depth, but have
 * different parents.
 * 
 * We are given the root of a binary tree with unique values, and the values x
 * and y of two different nodes in the tree.
 * 
 * Return true if and only if the nodes corresponding to the values x and y are
 * cousins.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4], x = 4, y = 3 Output: false Example 2:
 * 
 * 
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4 Output: true Example 3:
 * 
 * 
 * 
 * Input: root = [1,2,3,null,4], x = 2, y = 3 Output: false
 * 
 * 
 * Note:
 * 
 * The number of nodes in the tree will be between 2 and 100. Each node has a
 * unique integer value from 1 to 100.
 */

class CousinsInBinaryTree_993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        if (root.val == x || root.val == y) {
            return false;
        }

        return Rec(root.right, x, y, 0) == Rec(root.left, x, y, 0);
    }

    private int Rec(TreeNode node, int x, int y, int level) {
        if (node == null) {
            return 0;
        }

        if (node.val == x || node.val == y) {
            return level + 1;
        }

        if (node.right != null) {
            Rec(node.right, x, y, level + 1);
        }

        if (node.left != null) {
            Rec(node.left, x, y, level + 1);
        }

        return 0;
    }
}