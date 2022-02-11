package tree;
import java.util.ArrayList;
import java.util.List;
/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

Example 1:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:

Input: root = [1,2], targetSum = 0
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
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

public class Path_Sum_II_113 {
	List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    	dfs(root, targetSum, 0, new ArrayList<Integer>());
    	return res;
    }

    private void dfs(TreeNode node, int targetSum, int currentSum, List<Integer> list) {
    	if (node == null) return;
    	currentSum += node.val;
        List<Integer> temp = new ArrayList<Integer>(list);
    	temp.add(node.val);
    	if (node.left == null && node.right == null && targetSum == currentSum)
    		res.add(temp);

    	dfs(node.left, targetSum, currentSum, temp);
    	dfs(node.right, targetSum, currentSum, temp);
    }

    public static void main(String[] args) {
    	Path_Sum_II_113 obj = new Path_Sum_II_113();
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    	List<List<Integer>> res = new ArrayList<>();
    	dfs(root, targetSum, new ArrayList<Integer>(), res);
    	return res;
    }

    private void dfs(TreeNode node, int currentSum, List<Integer> list, List<List<Integer>> res) {
    	if (node == null) return;
        list.add(node.val);

    	if (node.left == null && node.right == null && node.val == currentSum) {
    		res.add(new ArrayList<Integer>(list));
        } else {
    	    dfs(node.left, currentSum - node.val, list, res);
    	    dfs(node.right, currentSum - node.val, list, res);
        }
        
        list.remove(list.size() - 1);
    }
}