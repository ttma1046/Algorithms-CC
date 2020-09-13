package tree;
/*
Given the root node of a binary search tree (BST)

and a value to be inserted into the tree, insert the value into the BST.

Return the root node of the BST after the insertion.

It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion,

as long as the tree remains a BST after insertion.

You can return any of them.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \
    1   3
         \
          4


Constraints:

The number of nodes in the given tree will be between 0 and 10^4.

Each node will have a unique integer value from 0 to -10^8, inclusive.
-10^8 <= val <= 10^8

It's guaranteed that val does not exist in the original BST.

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
class Insert_into_a_Binary_Search_Tree_701 {
  public TreeNode insertIntoBST(TreeNode root, int val) {
    TreeNode curr = root;


    if (root == null && val >= 0) {
      return new TreeNode(val);
    }


    while (curr != null) {
      if (val > curr.val) {
        if (curr.right != null) {
          curr = curr.right;
        } else {
          curr.right = new TreeNode(val);
          break;
        }
      } else {
        if (curr.left != null) {
          curr = curr.left;
        } else {
          curr.left = new TreeNode(val);
          break;
        }
      }
    }

    return root;
  }

  public static void main(String[] args) {
    TreeNode one = new TreeNode(1);
    TreeNode three = new TreeNode(3);
    TreeNode two = new TreeNode(2);

    two.left = one;
    two.right = three;

    TreeNode four = new TreeNode(4);
    four.left = two;

    TreeNode seven = new TreeNode(7);
    four.right = seven;


    // TreeNode five = new TreeNode(5);
    // TreeNode six = new TreeNode(6);

    // two.left = one;
    // three.left = two;
    // three.right = four;

    // five.left = three;
    // five.right = six;

    TreeNode result = new Insert_into_a_Binary_Search_Tree_701().insertIntoBST(four, 5);

    TreeNode curr = result;
    new Insert_into_a_Binary_Search_Tree_701().inorderTraversal(curr);
  }

  private void inorderTraversal(TreeNode current) {
    if (current == null) return;
    
    if (current.left != null) {
      inorderTraversal(current.left);
    }

    System.out.println(current.val);

    if (current.right != null) {
      inorderTraversal(current.right);
    }
  }
}