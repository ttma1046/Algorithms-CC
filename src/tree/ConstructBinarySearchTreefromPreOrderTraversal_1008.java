package tree;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/*
Given an array of integers preorder, which represents the preorder traversal of a BST

(i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree

with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node,

any descendant of Node.left has a value strictly less than Node.val,

and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first,

then traverses Node.left, then traverses Node.right.

Example 1:

Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Example 2:

Input: preorder = [1,3]
Output: [1,null,3]

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 108
All the values of preorder are unique.
*/

public class ConstructBinarySearchTreefromPreOrderTraversal_1008 {
    /*
    private int index;

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 0) {
            return null;
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
    }

    private TreeNode helper(int minLimit, int maxLimit, int[] array) {
        if (array.length == index) {
            return null;
        }

        int value = array[index];
        if (value <= minLimit || value >= maxLimit) {
            return null;
        }

        index++;

        TreeNode node = new TreeNode(value);
        node.left = helper(minLimit, value, preorder);
        node.right = helper(value, maxLimit, preorder);

        return node;
    }
    */

    /*
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        int n = preorder.length;
        TreeNode node = new TreeNode(preorder[0]);
        Stack<TreeNode> myStack = new Stack<TreeNode>();
        myStack.push(node);

        for(int i = 1; i < n; i++) {
            TreeNode temp = myStack.peek();
            TreeNode child = new TreeNode(preorder[i]);

            while(!myStack.isEmpty() && myStack.peek().val < child.val) {
                temp = myStack.pop();
            }

            if (child.val > temp.val) {
                temp.right = child;
            } else {
                temp.left = child;
            }

            myStack.push(child);
        }

        return node;
    }
    */
    /*
    int idx = 0;
    int[] preorder;
    int n;

    public TreeNode helper(int lower, int upper) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (idx == n) return null;

        int val = preorder[idx];
        // if the current element
        // couldn't be placed here to meet BST requirements
        if (val < lower || val > upper) return null;

        // place the current element
        // and recursively construct subtrees
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = helper(lower, val);
        root.right = helper(val, upper);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    */
    /*
    int index = 0;
    private TreeNode helper(int lower, int upper, int[] array) {
        if (index == array.length) {
            return null;
        }

        int val = array[index];
        if (val < lower || val > upper) return null;
        index++;
        TreeNode node = new TreeNode(val);
        node.left = helper(lower, val, array);
        node.right = helper(val, upper, array);
        return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 0) {
            return null;
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
    }
    */
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        return bstFromPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] preorder, int lower, int upper) {
        if (preorder == null || preorder.length == 0 || index == preorder.length) return null;

        int value = preorder[index];

        if (value <= lower || value >= upper) return null;

        TreeNode node = new TreeNode(value);

        index++;
        node.left = bstFromPreorder(preorder, lower, value);
        node.right = bstFromPreorder(preorder, value, upper);

        return node;
    }

    public TreeNode bstFromPreorderI(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;

        Queue<Integer> q = new LinkedList<>();

        for (int i : preorder) q.offer(i);

        return bstFromPreorderI(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorderI(Queue<Integer> q, int lower, int upper) {
        if (q.size() == 0) return null;

        int value = q.peek();

        if (value <= lower || value >= upper) return null;

        q.poll();

        TreeNode node = new TreeNode(value);

        node.left = bstFromPreorderI(q, lower, value);
        node.right = bstFromPreorderI(q, value, upper);

        return node;
    }

    int i = 0;
    public TreeNode bstFromPreorderII(int[] preorder) {
        return bstFromPreorderII(preorder, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorderII(int[] preorder, int upper) {
        if (i == preorder.length || preorder[i] > upper) return null;

        TreeNode node = new TreeNode(preorder[i++]);
        node.left = bstFromPreorderII(preorder, node.val);
        node.right = bstFromPreorderII(preorder, upper);
        return node;
    }

    public static void main(String[] args) {
        TreeNode result = new ConstructBinarySearchTreefromPreOrderTraversal_1008().bstFromPreorderI(new int[] { 8, 5, 1, 7, 10, 12 });

        printPreOrder(result);
    }

    private static void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printPreOrder(node.left);
        System.out.println(node.val);
        printPreOrder(node.right);
    }
}