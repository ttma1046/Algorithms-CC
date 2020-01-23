package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturn_1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root != null && to_delete.length < 0) {
            return null;
        }

        if (match(root.val, to_delete)) { }

        List<TreeNode> result = new ArrayList<TreeNode>();



        result = traversal(root, result, to_delete);

        return result;
    }

    public List<TreeNode> traversal(TreeNode current, List<TreeNode> result, int [] to_delete) {
            if (current.left != null) {
                result.add(current.left);
                traversal(current.left, result, to_delete);
            }

            if (current.right != null) {
                result.add(current.left);
                traversal(current.left, result, to_delete);
            }
    }

    private boolean match(int number, int[] to_delete) {
        for(int i: to_delete) {
            if (i == number) {
                return true;
            }
        }

        return false;
    }

    Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodesII(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted) res.add(node);
        node.left = helper(node.left, deleted);
        node.right =  helper(node.right, deleted);
        return deleted ? null : node;
    }
}
