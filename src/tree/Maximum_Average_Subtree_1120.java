package tree;

/*
Given the root of a binary tree, return the maximum average value of a subtree of that tree.

Answers within 10-5 of the actual answer will be accepted.

A subtree of a tree is any node of that tree plus all its descendants.

The average value of a tree is the sum of its values, divided by the number of nodes.

Example 1:

Input: root = [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.

Example 2:

Input: root = [0,null,1]
Output: 1.00000

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 105
*/
/**
 * Definition for a binary tree node.
 */
class TreeNode {
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

class Pair {
    int sum = 0;
    int nodeCount = 0;

    Pair(int s, int n) {
        this.sum = s;
        this.nodeCount = n;
    }
}

class Maximum_Average_Subtree_1120 {
    double res = 0;
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);

        return res;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[2];

        int[] rightSide = dfs(node.right);
        int[] leftSide = dfs(node.left);

        int sum = node.val + leftSide[0] + rightSide[0];
        int nodeCount = rightSide[1] + leftSide[1] + 1;

        res = Math.max(res, (double)sum / nodeCount);
        return new int[] {sum, nodeCount};
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);

        TreeNode two = new TreeNode(2);
        two.right = one;

        Maximum_Average_Subtree_1120 obj = new Maximum_Average_Subtree_1120();
        System.out.println(obj.maximumAverageSubtree(two));
    }
}