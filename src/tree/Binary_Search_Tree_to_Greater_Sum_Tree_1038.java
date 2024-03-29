package tree;

/*
Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Example 2:

Input: root = [0,null,1]
Output: [1,null,1]

Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val <= 100
All the values in the tree are unique.

Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
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
class Binary_Search_Tree_to_Greater_Sum_Tree_1038 {
    int pre = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return root;

        if (root.right != null)
            bstToGst(root.right);

        pre = root.val = pre + root.val;

        if (root.left != null)
            bstToGst(root.left);

        return root;
    }

    TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    // 记录累加和
    int sum = 0;
    void traverse(TreeNode root) {
        if (root == null)
            return;

        traverse(root.right);
        // 维护累加和
        root.val = sum += root.val;
        // 将 BST 转化成累加树
        traverse(root.left);
    }

    public static void main(String[] args) {
        Binary_Search_Tree_to_Greater_Sum_Tree_1038 obj = new Binary_Search_Tree_to_Greater_Sum_Tree_1038();
    }
}