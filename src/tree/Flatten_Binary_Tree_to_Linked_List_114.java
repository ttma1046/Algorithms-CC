package tree;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [0]
Output: [0]

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
*/
/*
    Definition for a binary tree node.
*/
public class TreeNode {
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
/**/
class Flatten_Binary_Tree_to_Linked_List_144 {
    public static void main(String[] args) {
        Flatten_Binary_Tree_to_Linked_List_144 obj = new Flatten_Binary_Tree_to_Linked_List_144();
    }

    public void flatten(TreeNode root) {
        flattenTree(root);
    }

    private TreeNode flattenTree(TreeNode node) {
        if (node == null)
            return null;

        if (node.left == null && node.right == null)
            return node;

        TreeNode leftTail = flattenTree(node.left);

        TreeNode rightTail = flattenTree(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail == null ? leftTail : rightTail;
    }

    /*
    public void flattenII(TreeNode root) {

        // Handle the null scenario
        if (root == null)
            return;

        TreeNode node = root;

        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }
    */

    public void flattenII(TreeNode root) {
        if (root == null)
            return;

        TreeNode curr = root;

        while(curr != null) {
            if (curr.left != null) {
                TreeNode rightMost = curr.left;

                while(rightMost.right != null)
                    rightMost = rightMost.right;

                rightMost.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            curr = curr.right;
        }
    }
}