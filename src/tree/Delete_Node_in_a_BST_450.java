package tree;
/*
Given a root node reference of a BST and a key, 

delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

1. Search for a node to remove.
2. If the node is found, delete the node.

Follow up: Can you solve it with time complexity O(height of tree)?

Example 1:

Input: root = [5,3,6,2,4,null,7], key = 3

         5
       /   \
      3     6
     / \     \ 
    2  4      7

Output: [5,4,6,2,null,null,7]

         5
       /   \
      4    6
     /      \ 
    2       7

Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the above BST.

Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

         5
       /  \
      2    6
       \    \ 
        4    7


Example 2:

Input: root = [5,3,6,2,4,null,7], key = 0

         5
       /   \
      3     6
     / \     \ 
    2  4      7

Output: [5,3,6,2,4,null,7]

         5
       /   \
      3     6
     / \     \ 
    2  4      7

Explanation: The tree does not contain a node with value = 0.

Example 3:

Input: root = [], key = 0
Output: []
 
Constraints:

The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105
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
class Solution {
    private TreeNode Successor(TreeNode node) {
        node = node.right;
        while(node.left != null) node = node.left;
        return node;
    }

    private TreeNode predecessor(TreeNode node) {
        node = node.left;
        while(node.right != null) node = node.right;
        return node;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode curr = root;

        if (curr.val == key) {


            if (curr.right != null) {
                curr = curr.right;
            }

            if (curr.left != null) {
                curr.parent.left = curr.left;
            }

        } else if (key > curr.val) {
            curr = curr.right;
        } else {
            curr = curr.left;
        }

        return root;
    }
}