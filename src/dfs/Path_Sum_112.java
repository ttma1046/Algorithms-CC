package dfs;
/*
Given the root of a binary tree and an integer targetSum,

return true if the tree has a root-to-leaf path

such that adding up all the values along the path equals targetSum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Example 2:

    1
  / \
 2  3

Input: root = [1,2,3], targetSum = 5
Output: false


Example 3:

Input: root = [1,2], targetSum = 0
Output: false

*/

import java.util.LinkedList;
import java.util.Stack;


class Path_Sum_112 {
    public boolean hasPathSumFastest(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSumFastest(root.left, targetSum - root.val) || hasPathSumFastest(root.right, targetSum - root.val);
    }

    public boolean hasPathSum(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }

        return myDFS(root, sum, 0);
    }

    private boolean myDFS(final TreeNode current, final int sum, int currentSum) {
        if (current == null)
            return false;

        currentSum += current.val;

        if (current.left == null && current.right == null) {
            return currentSum == sum;
        }

        return myDFS(current.left, sum, currentSum) || myDFS(current.right, sum, currentSum);
    }

    public boolean hasPathSumII(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;

        if (root.left == null && root.right == null) {
            return sum == 0;
        }

        return hasPathSumII(root.left, sum) || hasPathSumII(root.right, sum);
    }

    public boolean hasPathSumIII(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> nodesStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        nodesStack.push(root);
        sumStack.push(sum);

        TreeNode currentNode;
        int currentSum;
        while (!nodesStack.isEmpty()) {
            currentNode = nodesStack.pop();
            currentSum = sumStack.pop();
            if (currentNode.left == null && currentNode.right == null && currentSum - currentNode.val == 0) {
                return true;
            }

            if (currentNode.left != null) {
                nodesStack.push(currentNode.left);
                sumStack.push(currentSum - currentNode.val);
            }

            if (currentNode.right != null) {
                nodesStack.push(currentNode.right);
                sumStack.push(currentSum - currentNode.val);
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        final TreeNode eleven = new TreeNode(11);
        eleven.left = new TreeNode(7);
        eleven.right = new TreeNode(2);

        final TreeNode four = new TreeNode(4);
        four.left = eleven;

        final TreeNode anotherFour = new TreeNode(4);
        anotherFour.right = new TreeNode(1);

        final TreeNode eight = new TreeNode(8);
        eight.left = new TreeNode(13);
        eight.right = anotherFour;

        final TreeNode root = new TreeNode(5);
        root.left = four;
        root.right = eight;

        System.out.println(new Path_Sum_112().hasPathSumIII(root, 22));
    }
}