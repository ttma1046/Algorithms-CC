package tree;
/*
Given the root of a binary tree and an integer targetSum,
return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf,
but it must go downwards (i.e., traveling only from parent nodes to child nodes).

Example 1:

Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.

Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
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
public class Path_Sum_III_437 {
    int count = 0;
    int k;
    HashMap<Integer, Integer> map = new HashMap();

    public void preorder(TreeNode node, int currSum, int sum) {
        if (node == null) return;

        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;

        // number of times the curr_sum − k has occured already,
        // determines the number of times a path with sum k
        // has occured upto the current node
        count += map.getOrDefault(currSum - k, 0);

        // add the current sum into hashmap
        // to use it during the child nodes processing
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum, sum);
        // process right subtree
        preorder(node.right, currSum, sum);

        // remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        map.put(currSum, map.get(currSum) - 1);
    }

    public int pathSum(TreeNode root, int sum) {
        preorder(root, 0, sum);
        return count;
    }
}