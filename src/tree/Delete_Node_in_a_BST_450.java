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
class Delete_Node_in_a_BST_450 {
    private int Successor(TreeNode node) {
        node = node.right;
        while (node.left != null) node = node.left;
        return node.val;
    }

    private int Predecessor(TreeNode node) {
        node = node.left;
        while (node.right != null) 
            node = node.right;
        return node.val;
    }

    public TreeNode deleteNodeI(TreeNode root, int key) {
        if (root == null) 
            return null;

        // delete from the right subtree
        if (key > root.val) 
            root.right = deleteNode(root.right, key);
        // delete from the left subtree
        else if (key < root.val) 
            root.left = deleteNode(root.left, key);
        // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
            // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = Successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = Predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private int getSuccessorValue(TreeNode node) {
        TreeNode temp = node.right;

        while (temp.left != null) {
            temp = temp.left;
        }

        return temp.val;
    }

    private int getPredecessorValue(TreeNode node) {
        TreeNode temp = node.left;

        while (temp.right != null) {
            temp = temp.right;
        }

        return temp.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.right == null || root.left == null) {
                root = null;
            } else if (root.right != null) {
                root.val = getSuccessorValue(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = getPredecessorValue(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val < key) 
            root.right = deleteNode(root.right, key);
        else if (root.val > key) 
            root.left = deleteNode(root.left, key);
        else if (root.right == null) 
            return root.left;
        else if (root.left == null) 
            return root.right;
        else {
            root.val = findMin(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        
        return root;
    }

    private int findMin(TreeNode curr) {
        while(curr.left != null) curr = curr.left;
        return curr.val;
    }

    /*
    Time complexity: O(log N).
    During the algorithm execution we go down the tree all the time - on the left or on the right,
    first to search the node to delete(O(H1)) time complexity as already discussed and then to actually delete it.
    H1 is a tree height from the root to the node to delete.
    Delete process takes O(H2) time, hwere H2 is a tree height from the root to delete to the leafs.
    That in total results in O(H1 + H2) = O(H) time xomplexity, hwere H is a tree height, equal to Log N in the case of the balanced tree.

    Space complexity: O(H) to keep the recursion stack, where H is a tree height.
    H = log N for the balanced tree.
    */
    public static void main(String[] args) {
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);

        TreeNode three = new TreeNode(3);
        three.left = two;
        three.right = four;

        TreeNode seven = new TreeNode(7);
        TreeNode six = new TreeNode(6);
        six.right = seven;

        TreeNode five = new TreeNode(5);

        five.left = three;
        five.right = six;
        TreeNode result = new Delete_Node_in_a_BST_450().deleteNode(five, 3);

        new Delete_Node_in_a_BST_450().preorderTraversal(result);
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
}