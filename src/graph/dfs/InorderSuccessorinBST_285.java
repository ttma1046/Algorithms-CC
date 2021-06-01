package dfs;
/*

Given a binary search tree and a node `p` in it, return the `in-order successor of that node in the BST`.

if the given node has no in-order successor in the tree, return `null`.

The successor of a node p is the node with the smallest key greater than p.val.

Example 1:


Input: root = [2,1,3], p = 1

Output: 2

Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.

Note:

If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.
*/

import java.util.ArrayDeque;

public class InorderSuccessorinBST_285 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (p.right != null) {
            return lookingMostLeft(p.right);
        }

        // the successor is somewhere upper in the tree
        ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();
        int inorder = Integer.MIN_VALUE;

        // inorder traversal : left -> node -> right
        while (!stack.isEmpty() || root != null) {
            // 1. go left till you can
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 2. all logic around the node
            root = stack.pop();
            // if the previous node was equal to p
            // then the current node is its successor
            if (inorder == p.val)
                return root;
            inorder = root.val;

            // 3. go one step right
            root = root.right;
        }

        // there is no successor
        return null;
    }

    private TreeNode lookingMostLeft(TreeNode current) {
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private TreeNode InorderTraversal(TreeNode current, TreeNode p, TreeNode pervious) {
        if (current == null) {
            return null;
        }

        if (current.left != null) {
            pervious = current;
            current = current.left;
            return InorderTraversal(current, p, pervious);
        }

        if (pervious.val == p.val) {
            return current;
        }

        if (current.right != null) {
            pervious = current;
            current = current.right;
            return InorderTraversal(current, p, pervious);
        }
        return null;
    }

    /* recursive */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode closestLeft = inorderSuccessor(root.left, p);
            return closestLeft != null ? closestLeft : root;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;

        TreeNode successor = null;

        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }

    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;

        if (root.val >= p.val) {
            return inorderPredecessor(root.left, p);
        } else {
            TreeNode closestRight = inorderPredecessor(root.right, p);
            return closestRight != null ? closestRight : root;
        }
    }

    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        TreeNode predecessor = null;

        while (root != null) {
            if (root.val >= p.val) {
                root = root.left;
            } else {
                predecessor = root;
                root = root.right;
            }
        }

        return predecessor;
    }
}