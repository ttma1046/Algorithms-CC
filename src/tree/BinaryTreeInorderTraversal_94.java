package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        inorderTraversal(root, result);

        return result;
    }

    private void inorderTraversal(TreeNode current,  List<Integer> result) {
        if (current == null) return;
        if (current.left != null) {
            inorderTraversal(current.left, result);
        }

        result.add(current.val);

        if (current.right != null) {
            inorderTraversal(current.right, result);
        }
    }

    public List<Integer> inorderTraversalII(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<Integer>();

        if (root != null) {
            if (root.left != null) {
                inorderTraversalII(root.left);
            }

            result.add(root.val);

            if (root.right != null) {
                inorderTraversalII(root.right);
            }
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

        List<Integer> results = new BinaryTreeInorderTraversal_94().inorderTraversal(root);

        for (int result: results) {
            System.out.println(result);
        }
    }
}
