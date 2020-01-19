package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal_145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        postorderTraversal(root, result);

        return result;
    }

    private void postorderTraversal(TreeNode current, List<Integer> result) {
        if (current != null) {
            if (current.left != null) {
                postorderTraversal(current.left, result);
            }

            if (current.right != null) {
                postorderTraversal(current.right, result);
            }

            result.add(current.val);
        }
    }

    public List<Integer> postorderTraversalII(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<Integer>();

        if (root != null) {
            if (root.left != null) {
                postorderTraversalII(root.left);
            }

            if (root.right != null) {
                postorderTraversalII(root.right);
            }

            result.add(root.val);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreePostorderTraversal_145().postorderTraversalII(root);

        for (int result: results) {
            System.out.println(result);
        }
    }
}
