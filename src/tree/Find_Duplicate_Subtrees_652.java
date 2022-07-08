package tree;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/*
Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:

Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:

Input: root = [2,1,1]
Output: [[1]]

Example 3:

Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

Constraints:

The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200
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
/* */
class Find_Duplicate_Subtrees_652 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    	if (root == null)
    		return null;

    	Map<String, Integer> map = new HashMap<>();
    	List<TreeNode> result = new ArrayList<>();
    	postorder(root, map, result);

    	return result;
    }

    private String postorder(TreeNode node, Map<String, Integer> map, List<TreeNode> res) {
    	if (node == null)
    		return "#";
    	String left = postorder(node.left, map, res);
    	String right = postorder(node.right, map, res);

    	String currKey = node.val + "," + left + "," + right;
    	map.put(currKey, map.getOrDefault(currKey, 0) + 1);
    	if (map.get(currKey) == 2)
    		res.add(node);

    	return currKey;
    }

    public static void main(String[] args) {
    	Find_Duplicate_Subtrees_652 obj = new Find_Duplicate_Subtrees_652();
    }
}