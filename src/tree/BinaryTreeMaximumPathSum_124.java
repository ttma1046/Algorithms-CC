package tree;

/*
A path in a binary tree is a sequence of nodes

where each pair of adjacent nodes in the sequence has an edge connecting them.

A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any path.


Example 1:

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
*/

public class BinaryTreeMaximumPathSum_124 {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        postorderTraversal(root);
        return result;
    }

    private int postorderTraversal(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(postorderTraversal(node.left), 0);
        int right = Math.max(postorderTraversal(node.right), 0);
        result = Math.max(result, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public int maxPathSumIII(TreeNode root) {
        if (root == null) return 0;
        postorderTraversalIII(root);
        return result;
    }

    private int postorderTraversalIII(TreeNode node) {
        if (node == null) return 0;
        int leftSum = postorderTraversalIII(node.left);
        int rightSum = postorderTraversalIII(node.right);
        result = Math.max(result, node.val + leftSum + rightSum);
        return Math.max(0, node.val + Math.max(leftSum, rightSum));
    }

    public int maxPathSumII(TreeNode root) {
        if (root == null) return 0;
        return postorderTraversalII(root)[0];
    }

    private int[] postorderTraversalII(TreeNode node) {
        if (node == null) return new int [] {Integer.MIN_VALUE, 0};

        int[] left = postorderTraversalII(node.left);
        int[] right = postorderTraversalII(node.right);

        int leftbig = 0;
        if (left[1] > 0) leftbig = left[1];

        int rightbig = 0;
        if (right[1] > 0) rightbig = right[1];

        int result = Math.max(left[0], right[0]);
        result = Math.max(result, rightbig + leftbig + node.val);

        return new int[] { result, node.val + Math.max(leftbig, rightbig) };
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = twenty;

        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSumIII(root));
        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSumII(root));
        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSum(root));
    }
}