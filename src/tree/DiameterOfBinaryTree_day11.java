package tree;

class DiameterOfBinaryTree_day11 {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        travsel(root);
        return max;
    }

    private int travsel(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftLength = travsel(node.left);

        int rightLength = travsel(node.right);

        max = Math.max(max, leftLength + rightLength + 1);

        return Math.max(leftLength, rightLength) + 1;
    }

    int height(TreeNode node) {
        /* base case tree is empty */
        if (node == null)
            return 0;

        /*
         * If tree is not empty then height = 1 + max of left height and right heights
         */
        return (1 + Math.max(height(node.left), height(node.right)));
    }
}

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int [] max = new int[1];
        travsel(root, max);
        return max[0];
    }

    private int travsel(TreeNode node, int[] max) {
        if (node == null) {
            return 0;
        }

        int leftLength = travsel(node.left, max);

        int rightLength = travsel(node.right, max);

        max[0] = Math.max(max[0], leftLength + rightLength);

        return Math.max(leftLength, rightLength) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}