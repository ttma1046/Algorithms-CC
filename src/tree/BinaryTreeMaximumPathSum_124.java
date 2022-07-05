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
    int maxValue = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        dfsTwo(root);
        return maxValue;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = 0;
        int dfsLeft = dfs(node.left);
        if (dfsLeft > 0)
            left = dfsLeft;

        int right = 0;
        int dfsRight = dfs(node.right);
        if (dfsRight > 0)
            right = dfsRight;

        maxValue = Math.max(maxValue, node.val + right + left);

        return Math.max(left, right) + node.val;
    }

    public int dfsTwo(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, dfsTwo(node.left));
        int right = Math.max(0, dfsTwo(node.right));

        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public int maxPathSumII(TreeNode root) {
        if (root == null)
            return 0;
        return postorderTraversalII(root)[0];
    }

    private int[] postorderTraversalII(TreeNode node) {
        if (node == null) return new int [] {Integer.MIN_VALUE, 0};

        int[] left = postorderTraversalII(node.left);
        int[] right = postorderTraversalII(node.right);

        int leftbig = left[1] > 0 ? left[1] : 0;
        int rightbig = right[1] > 0 ? right[1] : 0;

        int result = Math.max(Math.max(left[0], right[0]), leftbig + rightbig + node.val);

        return new int[] { result, node.val + Math.max(leftbig, rightbig) };
    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        postOrder(root);
        return maxValue;
    }

    public int postOrder(TreeNode node) {
        if (node == null)
            return 0;

        int left = postOrder(node.left);
        int right = postOrder(node.right);
        
        maxValue = Math.max(maxValue, node.val + left + right);

        return Math.max(0, node.val + Math.max(left, right));
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