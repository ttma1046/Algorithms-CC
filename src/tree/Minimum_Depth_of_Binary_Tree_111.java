package tree;
import java.util.Deque;
import java.util.ArrayDeque;
/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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

class Minimum_Depth_of_Binary_Tree_111 {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(queue.size() > 0) {
            depth++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left == null && node.right == null)
                    return depth;

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        return depth;
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        int minDepth = Integer.MAX_VALUE;

        if (root.left != null)
            minDepth = Math.min(minDepth(root.left), minDepth);

        if (root.right != null)
            minDepth = Math.min(minDepth(root.right), minDepth);

        return minDepth + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) 
            return 0;

        if (root.left == null) 
            return minDepth(root.right) + 1;
        if (root.right == null) 
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
    }
    
    public static void main(String[] args) {
        Minimum_Depth_of_Binary_Tree_111 obj = new Minimum_Depth_of_Binary_Tree_111();
    }
}