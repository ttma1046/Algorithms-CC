package dfs;
/*

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

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
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

*/

import java.util.LinkedList;

class Path_Sum_112 {
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

    public boolean hasPathSumII(final TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;

        if (root.left == null && root.right == null) {
            return sum == 0;
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public boolean hasPathSumIII(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> nodesStack = new LinkedList<TreeNode>();
        LinkedList<Integer> sumStack = new LinkedList<Integer>();

        nodesStack.add(root);
        sumStack.add(sum);

        TreeNode currentNode;
        int currentSum;
        while (!nodesStack.isEmpty()) {
            currentNode = nodesStack.pollLast();
            currentSum = sumStack.pollLast();
            if (currentNode.left == null && currentNode.right == null && currentSum - currentNode.val == 0) {
                return true;
            }

            if (currentNode.left != null) {
                nodesStack.add(currentNode.left);
                sumStack.add(currentSum - currentNode.val);
            }

            if (currentNode.right != null) {
                nodesStack.add(currentNode.right);
                sumStack.add(currentSum - currentNode.val);
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