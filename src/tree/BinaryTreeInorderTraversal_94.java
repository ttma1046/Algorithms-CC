package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode current,  List<Integer> list) {
        if (current.left != null) inorderTraversal(current.left, list);
        list.add(current.val);
        if (current.right != null) inorderTraversal(current.right, list);
    }

    public List<Integer> inorderTraversalIII(TreeNode root) {
        List<Integer> result = new ArrayList();
        inorder(root, result);
        return result;
    }

    public void inorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }    

    public List<Integer> inorderTraversalII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // inorder traversal : left -> node -> right
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    public List<Integer> inorderTraversalII(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<Integer> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = curr.pop();
            result.add(curr.val);
            curr = curr.right;
        }
    }


    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreeInorderTraversal_94().inorderTraversal(root);

        for (int result : results) {
            System.out.println(result);
        }
    }
}
