package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<Integer>();

        if (root != null) {
            result.add(root.val);


            if (root.left != null) {
                preorderTraversal(root.left);
            }


            if (root.right != null) {
                preorderTraversal(root.right);
            }
        }

        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        // preorder traversal : left -> node -> right
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

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Integer> results = new BinaryTreePreorderTraversal_144().preorderTraversal(root);

        for (int result: results) {
            System.out.println(result);
        }
    }
}
