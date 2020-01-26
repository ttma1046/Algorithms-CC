package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest_1110 {
    Set<Integer> mySet;
    List<TreeNode> result;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null ) return null;
        result = new ArrayList<TreeNode>();
        if (to_delete != null) {
            mySet = new HashSet<Integer>();

            for (int i : to_delete) {
                mySet.add(i);
            }

            helper(root, true);
        } else {
            result.add(root);
        }
        return result;
    }

    private TreeNode helper(TreeNode current, boolean is_root) {
        if (current == null) return null;
        boolean deleted = mySet.contains(current.val);
        if (is_root && !deleted) result.add(current);

        current.left = helper(current.left, deleted);
        current.right = helper(current.right, deleted);

        return deleted ? null : current;
    }

    public List<TreeNode> delNodesII(TreeNode root, int[] to_delete) {
        if (root == null ) return null;
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (to_delete != null) {
            Set<Integer> mySet = new HashSet<Integer>();

            for (int i : to_delete) {
                mySet.add(i);
            }

            if (!mySet.contains(root.val)) result.add(root);
            helperII(root, result, mySet);
        } else {
            result.add(root);
        }
        return result;
    }

    private TreeNode helperII(TreeNode current, List<TreeNode> result, Set<Integer> set) {
        if (current == null) return null;
        current.left = helperII(current.left, result, set);
        current.right = helperII(current.right, result, set);

        if (set.contains(current.val)) {
            if (current.left != null) result.add(current.left);
            if (current.right != null) result.add(current.right);
            return null;
        }

        return current;

    }


}

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

Example 1:

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Constraints:

The number of nodes in the given tree is at most 1000.

Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */