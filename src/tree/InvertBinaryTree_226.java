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
        if (root == null)
            return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }

    TreeNode invertTree(TreeNode root) {
        // 遍历二叉树，交换每个节点的子节点
        traverse(root);
        return root;
    }

    // 二叉树遍历函数
    void traverse(TreeNode root) {
        if (root == null)
            return;

        // 遍历框架，去遍历左右子树的节点
        traverse(root.left);
        /**** 前序位置 ****/
        // 每一个节点需要做的事就是交换它的左右子节点

        TreeNode rightSide = root.right;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        traverse(rightSide);
    }

    public TreeNode invertTreeII(TreeNode node) {
        if (node == null)
            return null;

        TreeNode left = invertTreeII(node.left);
        TreeNode right = invertTreeII(node.right);
        node.left = right;
        node.right = left;

        return node;
    }

    public TreeNode invertTreeIII(TreeNode root) {
        if (root == null)
            return null;

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

    public TreeNode invertTreeII(TreeNode node) {
        if (node == null)
            return null;

        TreeNode left = invertTreeII(node.left);
        TreeNode right = invertTreeII(node.right);

        node.left = right;
        node.right = left;

        return node;
    }
}
