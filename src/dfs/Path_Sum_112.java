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

class Path_Sum_112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return myDFS(root, sum, 0);
    }

    private boolean myDFS(TreeNode current, int sum, int currentSum) {
        if (current == null)
            return false;

        currentSum += current.val;

        if (current.left == null && current.right == null) {
            return currentSum == sum;
        }

        return myDFS(current.left, sum, currentSum) || myDFS(current.right, sum, currentSum);
    }

    public static void main(String[] args) {
        TreeNode eleven = new TreeNode(11);
        eleven.left = new TreeNode(7);
        eleven.right = new TreeNode(2);

        TreeNode four = new TreeNode(4);
        four.left = eleven;

        TreeNode anotherFour = new TreeNode(4);
        anotherFour.right = new TreeNode(1);

        TreeNode eight = new TreeNode(8);
        eight.left = new TreeNode(13);
        eight.right = anotherFour;

        TreeNode root = new TreeNode(5);
        root.left = four;
        root.right = eight;

        System.out.println(new Path_Sum_112().hasPathSum(root, 22));
    }
}