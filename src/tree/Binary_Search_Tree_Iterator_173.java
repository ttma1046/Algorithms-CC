package tree;
import java.util.Stack;

/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
*/
/*
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
class Binary_Search_Tree_Iterator_173 {
    private Stack<Integer> stack = new Stack<Integer>();
    public Binary_Search_Tree_Iterator_173(TreeNode root) {
        if (root != null) {
            res(root);
        }
    }

    private void res(TreeNode root) {
        if (root.right != null) res(root.right);

        if (root != null) {
            stack.push(root.val);
        }

        if (root.left != null) res(root.left);
    }


    /** @return the next smallest number */
    public int next() {
        return stack.pop();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Binary_Search_Tree_Iterator_173(TreeNode root) {
        res(root);
    }

    private void res(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();

        if (node.right != null) {
            res(node.right);
        }

        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);
        TreeNode twenty = new TreeNode(20);

        TreeNode fifteen = new TreeNode(15);
        fifteen.right = twenty;
        fifteen.left = nine;

        TreeNode seven = new TreeNode(7);
        seven.left = three;
        seven.right = fifteen;

        Binary_Search_Tree_Iterator_173 iterator = new Binary_Search_Tree_Iterator_173(seven);

        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */