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

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList();
        Deque<TreeNode> stack = new ArrayDeque();
        
        if (root == null) return output;

        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            output.addFirst(root.val);
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }

        return output;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList();
        Deque<TreeNode> stack = new ArrayDeque();
        
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
                output.add(root.val);
                root = null;     
            }   
        }

        return output;
    }
}
