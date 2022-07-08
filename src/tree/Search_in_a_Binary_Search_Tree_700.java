package tree;
import java.util.Stack;
/*
700. Search in a Binary Search Tree

Share
Given the root node of a binary search tree (BST) and a value.

You need to find the node in the BST that the node's value equals the given value.

Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the value to search: 2
You should return this subtree:

      2
     / \
    1   3
In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.

Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Search_in_a_Binary_Search_Tree_700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        
        TreeNode curr = root;

        while (curr != null) {
            if (val == curr.val)
                return curr;
            else if (val > curr.val)
                curr = curr.right;
            else
                curr = curr.left;
        }

        return null;
    }

    public static void main (String[] args) {
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        two.left = one;
        two.right = three;

        TreeNode seven = new TreeNode(7);
        TreeNode four = new TreeNode(4);
        four.left = two;
        four.right =  seven;
        TreeNode result = new Search_in_a_Binary_Search_Tree_700().searchBST(four, 2);

        new Search_in_a_Binary_Search_Tree_700().preorderTraversal(result);
        new Search_in_a_Binary_Search_Tree_700().preorderTraversal(four);
        new Search_in_a_Binary_Search_Tree_700().preorder(four);
        new Search_in_a_Binary_Search_Tree_700().preorderTraversalEasy(four);
    }

    private void preorderTraversal(TreeNode current) {
        if (current == null) return;

        System.out.println(current.val);

        if (current.left != null) {
            preorderTraversal(current.left);
        }

        if (current.right != null) {
            preorderTraversal(current.right);
        }
    }

    private void preorder(TreeNode current) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        // preorder traversal : left -> node -> right
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                System.out.println(current.val);

                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            current = current.right;
        }
    }

    private void preorderTraversalEasy(TreeNode node) {
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while (node != null) {
            if (node.right != null)
                rights.push(node.right);

            node = node.left;
            if (node == null && !rights.isEmpty())
                node = rights.pop();
        }
    }
}