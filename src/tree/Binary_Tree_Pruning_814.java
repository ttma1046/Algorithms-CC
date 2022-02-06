package tree;

/*
Given the root of a binary tree,

return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.

Example 1:
Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
Explanation:
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

Example 2:
Input: root = [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]

Example 3:
Input: root = [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]

Constraints:

The number of nodes in the tree is in the range [1, 200].
Node.val is either 0 or 1.
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
class Binary_Tree_Pruning_814 {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    private boolean containsOne(TreeNode node) {
    	if (node == null) return false;

    	boolean leftContainsOne = containsOne(node.left);
    	boolean rightContainsOne = containsOne(node.right);

    	if (!leftContainsOne) node.left = null;
    	if (!rightContainsOne) node.right = null;

    	return node.val == 1 || leftContainsOne || rightContainsOne;
    }

    public static void main(String[] args) {
    	Binary_Tree_Pruning_814 obj = new Binary_Tree_Pruning_814();
    }

    /*
    Time Complexity: O(N), where N is the number of nodes in the tree. We process each node once.

	Space Complexity: O(N), the recursion call stack can be as large as the height H of the tree. 
	In the worst case scenario, H=NH=N, when the tree is skewed.
	*/
}