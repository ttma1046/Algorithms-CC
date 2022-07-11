package tree;
/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia:

“The lowest common ancestor is defined between two nodes p and q as the lowest node in T

that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:

       6
  2      8
0   4   7 9
   3 5

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:

       6
  2      8
0   4   7 9
   3 5

Input: root = [2,1], p = 2, q = 1
Output: 2

  2
 1

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the BST.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Lowest_Common_Ancestor_of_a_Binary_Search_Tree_235 {
    // O(N), where N is the number of nodes in the BST. In the worst case we might be visiting all the nodes of the BST.

    // O(N), This is because the maximum amount of space utilized by the recursion stack would be N since the height of a skewedd BST could be N.
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) 
            return lowestCommonAncestorI(root.left, p, q);

        if (p.val > root.val && q.val > root.val) 
            return lowestCommonAncestorI(root.right, p, q);
        return root;
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        while(true) {
            if (root.val > p.val && root.val > q.val) root = root.left;
            else if (root.val < p.val && root.val < q.val) root = root.right;
            else return root;
        }
    }

    public TreeNode lowestCommonAncestorIII(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;

        if(root.val == p.val || root.val == q.val) 
            return root;

        if(
            (p.val < root.val && q.val > root.val) 
            || (p.val > root.val && q.val < root.val)) 
            return root;

        if(root.val < p.val && root.val < q.val) {
            TreeNode node = lowestCommonAncestorIII(root.right, p, q);
            return node;
        } else {
            TreeNode node = lowestCommonAncestorIII(root.left, p, q);
            return node;
        }
    } 

    public static void main(String[] args) {
        Lowest_Common_Ancestor_of_a_Binary_Search_Tree_235 obj = new Lowest_Common_Ancestor_of_a_Binary_Search_Tree_235();
    }
}