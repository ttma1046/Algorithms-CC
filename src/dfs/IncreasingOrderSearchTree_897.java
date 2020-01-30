package dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.
*/

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class IncreastingOrderSearchTree_897 {
    public TreeNode increasingBST(TreeNode root) {
        if (root != null) {
            TreeNode newRoot = null;
            return inorderTravsel(root, newRoot);
        }

        return null;
    }

    private TreeNode inorderTravsel(TreeNode current, TreeNode newRoot) {

        if (current == null)
            return null;

        if (current.left != null) {
            newRoot = inorderTravsel(current.left, newRoot);
        }

        if (newRoot == null) {
            newRoot = new TreeNode(current.val);
        } else {
            TreeNode temp = newRoot;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = new TreeNode(current.val);
        }

        if (current.right != null) {
            newRoot = inorderTravsel(current.right, newRoot);
        }

        return newRoot;
    }

    public TreeNode increasingBSTII(TreeNode root) {
        List<Integer> vals = new ArrayList<Integer>();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v : vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null)
            return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    TreeNode cur;

    public TreeNode increasingBSTIII(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }
}