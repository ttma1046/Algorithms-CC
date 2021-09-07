package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;


public class BinaryTreePostorderTraversal_145 {
    // bottom -> top left -> right
    public List<Integer> postorderTraversalII(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        postorderTraversal(root, result);
        return result;
    }

    private void postorderTraversal(TreeNode current, List<Integer> result) {
        if (current.left != null) postorderTraversal(current.left, result);
        if (current.right != null) postorderTraversal(current.right, result);
        result.add(current.val);
    }

    public List<Integer> postorderTraversalIII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreePostorderTraversal_145().postorderTraversalII(root);

        for (int result : results) {
            System.out.println(result);
        }
    }

    public List<Integer> postorderTraversalIV(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        if (root == null) return result;

        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.addFirst(root.val);
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }

        return result;
    }

    public List<Integer> postorderTraversalI(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            // push nodes: right -> node -> left
            while (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            }  

            root = stack.pop();

            // if the right subtree is not yet processed
            if (!stack.isEmpty() && root.right == stack.peek()) {
                stack.pop();
                stack.push(root);
                root = root.right;
                // if we're on the leftmost leaf
            } else {
                result.add(root.val);
                root = null;
            }
        }

        return result;
    }

    public List<Integer> postorderTraversalGu(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();

        if (root != null) stack.push(root);
        TreeNode curr = root;
        while(curr != null || stack.size() > 0) {
            if (curr != null) {
                resStack.push(curr);
                stack.push(curr);
                curr = curr.right;
            } else {
                curr = stack.pop();
                curr = curr.left;
            }
        }

        List<Integer> res = new ArrayList<>();
        while(resStack.size() > 0) res.add(resStack.pop().val);
        return res;
    }
}
