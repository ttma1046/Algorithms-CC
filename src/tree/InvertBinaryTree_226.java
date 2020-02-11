package tree;

import java.util.LinkedList;
import java.util.Queue;

// Invert a binary tree.
/*
    Trivia:
        This problem was inspired by this original tweet by Max Howell:

        Google: 90% of our engineers use the software you wrote (Homebrew),
        but you can’t invert a binary tree on a whiteboard so f*** off.
 */

public class InvertBinaryTree_226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        invertTreePrivate(root);

        return root;
    }

    private void invertTreePrivate(TreeNode currentNode) {
        TreeNode temp = currentNode.left;
        currentNode.left = currentNode.right;
        currentNode.right = temp;

        if (currentNode.left != null) {
            invertTreePrivate(currentNode.left);
        }

        if (currentNode.right != null) {
            invertTreePrivate(currentNode.right);
        }
    }

    public TreeNode invertTreeII(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
    }

    public TreeNode invertTreeIII(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            if (current.left != null)
                queue.offer(current.left);
            if (current.right != null)
                queue.offer(current.right);
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode seven = new TreeNode(7);
        seven.left = new TreeNode(6);
        seven.right = new TreeNode(9);

        TreeNode two = new TreeNode(2);
        two.left = new TreeNode(1);
        two.right = new TreeNode(3);

        TreeNode root = new TreeNode(4);
        root.left = two;
        root.right = seven;

        TreeNode result = new InvertBinaryTree_226().invertTree(root);
        preOrderTraversalPrint(result);
    }

    private static void preOrderTraversalPrint(TreeNode result) {
        if (result != null) {
            System.out.println(result.val);
            preOrderTraversalPrint(result.left);
            preOrderTraversalPrint(result.right);
        }
    }
}
