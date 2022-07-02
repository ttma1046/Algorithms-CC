package tree;
import java.util.Stack;
/*
You are given the root of a binary search tree (BST),

where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:

Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1

Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
*/

/**
 * Definition for a binary tree node.
 */ 
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
/**/

class Recover_Binary_Search_Tree_99 {
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        if (root == null) return;

        dfs(root);
        if (first == null || second == null) 
            return;

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    void dfs(TreeNode node) {
        if (node == null) return;

        if (node.left != null) 
            dfs(node.left);

        if (prev.val > node.val) {
            if (first == null) {
                first = prev;
                second = node;
            } else {
                second = node;
            }
        }

        prev = node;

        if (node.right != null) 
            dfs(node.right);
    }

    public void recoverTreeII(TreeNode root) {
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        TreeNode first = null;
        TreeNode second = null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || stack.size() > 0) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            if (curr.val < prev.val) {
                if (first == null) {
                    first = prev;
                    second = curr;
                } else {
                    second = curr;
                }
            }

            prev = curr;
            curr = curr.right;
        }

        if (first == null || second == null) 
            return;

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public static void main(String[] args) {
        Recover_Binary_Search_Tree_99 obj = new Recover_Binary_Search_Tree_99();
    }
}