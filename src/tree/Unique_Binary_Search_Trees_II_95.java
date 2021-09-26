package tree;

/*
Given an integer n, return all the structurally unique BST's (binary search trees),

which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:

Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:

Input: n = 1
Output: [[1]]

Constraints:

1 <= n <= 8
*/
class Unique_Binary_Search_Trees_II_95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return helper(1, n);
    }

    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if (start > end) res.add(null);

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(start, i - 1);
            List<TreeNode> rightList = helper(i + 1, end);

            for (TreeNode left : leftList)
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
        }

        return res;
    }

}