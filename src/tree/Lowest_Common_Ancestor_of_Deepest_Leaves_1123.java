package tree;
/*
Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest leaf-nodes of the tree.
Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.

Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree, and it's the lca of itself.

Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.

Constraints:

The number of nodes in the tree will be in the range [1, 1000].
0 <= Node.val <= 1000
The values of the nodes in the tree are unique.
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

class Lowest_Common_Ancestor_of_Deepest_Leaves_1123 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Map<TreeNode, Integer> depth = new HashMap<TreeNode, Integer>();

        depth.put(null, -1);

        dfs(root, null, depth);

        int max_depth = -1;

        for (Integer d : depth.values()) {
            max_depth = Math.max(max_depth, d);
        }

        return answer(root, max_depth, depth);
    }

    private void dfs(TreeNode current, TreeNode parent, Map<TreeNode, Integer> depth) {
        if (current != null) {
            depth.put(current, depth.get(parent) + 1);
            dfs(current.left, current, depth);
            dfs(current.right, current, depth);
        }
    }

    private TreeNode answer(TreeNode current, int max_depth, Map<TreeNode, Integer> depth) {
        if (current == null || depth.get(current) == max_depth) return current;
        

        TreeNode left = answer(current.left, max_depth, depth), 
        	right = answer(current.right, max_depth, depth);

        return left == null ? right : right == null ? left : current;
    }

    // O(n^2)
    public TreeNode lcaDeepestLeaves(TreeNode root) {
    	if (root == null) return null;
    	int left = height(root.left);
    	int right = height(root.right);

    	if (left == right) return root;
    	else if (left > right) return lcaDeepestLeaves(root.left);
    	else return lcaDeepestLeaves(root.right);
    }

    private int height(TreeNode root) {
    	if (root == null) return 0;
    	return 1 + Math.max(height(root.left), height(root.right));
    }

    // O(n)
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair res = dfs(root);

        return res.node;
    }

    public Pair dfs(TreeNode node) {
        if (node == null) return new Pair(null, 0);

        Pair left = dfs(node.left), right = dfs(node.right);

        /*
        if (left.depth == right.depth) {
            return new Pair(node, left.depth + 1);
        } else if (left.depth > right.depth) {
            left.depth++;
            return left;
        } else {
            right.depth++;
            return right;
        }
        */

        return left.depth == right.depth ? new Pair(node, left.length + 1) : (left.depth > right.depth ? new Pair(left.node, left.depth + 1) : new Pair(right.node, right.depth + 1));
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).getValue();
    }

    public Pair<Integer, TreeNode> deep(TreeNode root) {
        if (root == null) return new Pair(0, null);

        Pair<Integer, TreeNode> l = deep(root.left), r = deep(root.right);

        int d1 = l.getKey(), d2 = r.getKey();
        return new Pair(Math.max(d1, d2) + 1, d1 == d2 ? root: d1 > d2 ? l.getValue() : r.getValue()); 
    }
}

class Pair {
	TreeNode node;
	int depth;

	public Pair(TreeNode n, int d) {
		this.node = n;
		this.depth = d;
	}
}