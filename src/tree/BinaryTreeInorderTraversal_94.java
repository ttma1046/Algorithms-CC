package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:

Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [1]
Output: [1]

Example 4:

Input: root = [1,2]
Output: [2,1]

Example 5:

Input: root = [1,null,2]
Output: [1,2]


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreeInorderTraversal_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        dfs(res, root);

        return res;
    }

    public void dfs(List<Integer> res, TreeNode node) {
        if (node == null) return;

        dfs(res, node.left);
        if (node != null) res.add(node.val);
        dfs(res, node.right);
    }


    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (curr != null || stack.size() > 0) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }

    /*
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        TreeNode curr = root;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while(stack.size() > 0 || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);

            curr = curr.right;
        }
    }

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
    */

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
