package dfs;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
*/
public class ValidateBinarySearchTree_98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        return myDFS(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean myDFS(TreeNode current, long min, long max) {
        if (current == null) return true;

        if (current.val <= min || current.val >= max) return false;

        return myDFS(current.left, min, current.val) && myDFS(current.right, current.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (max != null && root.val >= max) return false;
        if (min != null && root.val <= min) return false;

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);

        if (!left) return false;

        if (root.val <= prev) return false;

        prev = root.val;

        return isValidBST(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while(root != null || stack.size() > 0) {
            while(root != null) {
                stack.push(root);
                root = root.left
            }

            root = stack.pop();

            if (prev != null && prev.val => root.val) return false;

            prev = root;

            root = root.right;
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(new ValidateBinarySearchTree_98().isValidBST(root));

        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(3);
        four.right = new TreeNode(6);

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = four;

        System.out.println(new ValidateBinarySearchTree_98().isValidBST(root));
    }
}
