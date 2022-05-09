package tree;
/*
Given the root of a binary tree, return the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from

some starting node to any node in the tree along the parent-child connections.

The longest consecutive path needs to be from parent to child (cannot be the reverse).

Example 1:

Input: root = [1,null,3,2,4,null,null,null,5]
Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:

Input: root = [2,null,3,2,null,1]
Output: 2
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-3 * 104 <= Node.val <= 3 * 104
*/
class Binary_Tree_Longest_Consecutive_Sequence_298 {
    private int maxLength = 0;

    public int longestConsecutiveSequence(TreeNode root) {
        dfs(root, null, 0);
        return maxLength;
    }

    void dfs(TreeNode node, TreeNode parent, int length) {
        if (node == null)
            return;

        length = (parent != null && node.val == parent.val + 1) ? length + 1 : 1;

        if (length > maxLength)
            maxLength = length;

        dfs(node.left, node, length);
        dfs(node.right, node, length);
    }

    public int longestConsecutiveSequenceI(TreeNode root) {
        return dfsI(root, null, 0);
    }

    int dfsI(TreeNode node, TreeNode parent, int length) {
        if (node == null)        
            return length;

        if (parent != null && node.val == parent.val + 1)
            length += 1;
        else
            length = 1;

        int left = dfsI(node.left, node, length);
        int right = dfsI(node.right, node, length);

        return Math.max(length, Math.max(left, right));
    }

    private int maxLength = 0;
    public int longestConsecutiveSequence(TreeNode root) {
        return dfsII(root);
    }

    public int dfsII(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsII(node.left) + 1;
        int right = dfsII(node.right) + 1;

        if (node.left != null && node.val + 1 != node.left.val)
            left = 1;

        if (node.right != null && node.val + 1 != node.right.val)
            right = 1;

        maxLength = Math.max(maxLength, Math.max(left, right));
        return maxLength;
    }

    public static void main(String[] args) {
        Binary_Tree_Longest_Consecutive_Sequence_298 obj = new Binary_Tree_Longest_Consecutive_Sequence_298();
    }
}