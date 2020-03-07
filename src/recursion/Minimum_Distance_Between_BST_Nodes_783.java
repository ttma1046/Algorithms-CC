package recursion;

import 

class Minimum_Distance_Between_BST_Nodes_783 {
    public int minDiffInBST(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int minDiff = Integer.MAX_VALUE;

        if (node.right != null) {
            minDiff = Math.min(minDiff, Math.abs(node.val - getFarLeftValue(node.right)));
            minDiff = Math.min(minDiff, minDiffInBST(node.right));
        }

        if (node.left != null) {
            minDiff = Math.min(minDiff, Math.abs(node.val - getFarRightValue(node.left)));
            minDiff = Math.min(minDiff, minDiffInBST(node.left));
        }

        return minDiff;

    }

    private int getFarRightValue(TreeNode node) {
        if (node.right == null) {
            return node.val;
        }

        return getFarRightValue(node.right);
    }

    private int getFarLeftValue(TreeNode node) {
        if (node.left == null) {
            return node.val;
        }

        return getFarLeftValue(node.left);
    }

    Integer res = Integer.MAX_VALUE, pre = null;
    public int minDiffInBST(TreeNode root) {

        if (root.left != null) minDiffInBST(root.left);

        if (pre != null) res = Math.min(res, root.val - pre);

        pre = root.val;
        
        if (root.right != null) minDiffInBST(root.right);
        return res;
    }
}