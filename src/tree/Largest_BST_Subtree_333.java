package tree;
/*
Given the root of a binary tree, find the largest subtree,

which is also a Binary Search Tree (BST),

where the largest means subtree has the largest number of nodes.

A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:

The left subtree values are less than the value of their parent (root) node's value.
The right subtree values are greater than the value of their parent (root) node's value.

Note: A subtree must include all of its descendants.

Example 1:
Input: root = [10,5,15,1,8,null,7]
Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.

Example 2:

Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
Output: 2

Constraints:

The number of nodes in the tree is in the range [0, 104].
-104 <= Node.val <= 104

Follow up: Can you figure out ways to solve it with O(n) time complexity?
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
class Largest_BST_Subtree_333 {
    public int largestBSTSubtree(TreeNode root) {
        int[] res = recursive(root);
        return res[2];
    }

    private int[] recursive(TreeNode node) {
        if (node == null) return new int [] { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 };

        int[] left = recursive(node.left);
        int[] right = recursive(node.right);

        if (node.val > left[0] && node.val < right[1]) {
            return new int [] { Math.max(right[0], node.val), Math.min(left[1], node.val), left[2] + right[2] + 1 };
        }
        return new int [] { Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(left[2], right[2]) };
    }

    public static void main(String[] args) {
        Largest_BST_Subtree_333 obj = new Largest_BST_Subtree_333();
    }
}