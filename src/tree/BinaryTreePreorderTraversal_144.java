package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<Integer>();

        preorderTraversal(root, result);

        return result;
    }

    public void preorderTraversal(TreeNode current, List<Integer> list) {
        list.add(current.val);

        if (current.left != null) preorderTraversal(current.left, list);

        if (current.right != null) preorderTraversal(current.right, list);
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode current, List<Integer> list) {
        if (current == null) return;
        list.add(current.val);
        preorder(current.left, list);
        preorder(current.right, list);
    }

    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // preorder traversal : node -> left -> right top -> bottom left -> right
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }

        return result;
    }

    public List<Integer> preorderTraversalGu(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // preorder traversal : node -> left -> right top -> bottom left -> right
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                curr = curr.right;
            }
        }

        return result;
    }

    public List<Integer> preorderTraversalIII(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> output = new ArrayList<>();
        if (root == null) return output;
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return output;
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreePreorderTraversal_144().preorderTraversal(root);

        for (int result : results) {
            System.out.println(result);
        }
    }
}
