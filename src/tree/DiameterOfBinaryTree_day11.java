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