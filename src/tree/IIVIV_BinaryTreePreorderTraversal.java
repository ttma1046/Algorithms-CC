package tree;

import java.util.ArrayList;
import java.util.List;

public class IIVIV_BinaryTreePreorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<Integer>();

        if (root != null) {
            result.add(root.val);
        }

        if (root.left != null) {
            preorderTraversal(root.left);
        }


        if (root.right != null) {
            preorderTraversal(root.right);
        }

        return result;
    }
}
