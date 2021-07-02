package binarysearch;
/**
 * Definition for a binary tree node.
 * 
**/
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }


/*
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

Example 1:

Input: root = [4,2,5,1,3], target = 3.714286
Output: 4

Example 2:

Input: root = [1], target = 4.428571
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
*/

class Closest_Binary_Search_Tree_Value_270 {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;

        while (root != null) {
            closest = Math.abs(root.val - target) < Math.abs(closest - target)  ? root.val : closest;

            root = root.val > target ? root.left : root.right;
        }

        return closest;
    }

    public static void main(String[] args) {
        Closest_Binary_Search_Tree_Value_270 obj = new Closest_Binary_Search_Tree_Value_270();

        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);

        TreeNode two = new TreeNode(2);
        two.left = one;
        two.right = three;

        TreeNode five = new TreeNode(5);

        TreeNode root = new TreeNode(4);
        root.left = two;
        root.right = five;

        System.out.println(obj.closestValue(root, 3.714286));
    }
}