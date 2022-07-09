package tree;
import java.util.List;
import java.util.ArrayList;
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
/**/

class Unique_Binary_Search_Trees_II_95 {
    public List<TreeNode> generateTreesII(int n) {
        if (n == 0)
            return new ArrayList<TreeNode>();

        return recursive(1, n);
    }

    private List<TreeNode> recursive(int low, int high) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (low > high) {
            res.add(null);
            return res;
        }

        for (int i = low; i <= high; i++) {
            List<TreeNode> left = recursive(low, i - 1);
            List<TreeNode> right = recursive(i + 1, high);

            for (TreeNode l: left) {
                for (TreeNode r: right) {
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;

                    res.add(node);
                }
            }
        }

        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) 
            return new ArrayList<TreeNode>();
        return helper(1, n);
    }

    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(start, i - 1);
            List<TreeNode> rightList = helper(i + 1, end);

            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Unique_Binary_Search_Trees_II_95 obj = new Unique_Binary_Search_Trees_II_95();
    }
}