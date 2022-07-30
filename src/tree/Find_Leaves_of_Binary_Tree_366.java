package tree;
/*
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.

Example 1:

Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.

Example 2:

Input: root = [1]
Output: [[1]]

Constraints:

The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
*/

/**
 * Definition for a binary tree node.
 */ 
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**/
class Find_Leaves_of_Binary_Tree_366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        recursive(root, res);
        
        return res;
    }
    
    private int recursive(TreeNode node, List<List<Integer>> res) {
        if (node == null)
            return -1;
        
        int left = recursive(node.left, res);
        int right = recursive(node.right, res);
        
        int level = Math.max(left, right) + 1;
        
        if (res.size() == level)
            res.add(new ArrayList<>());
        
        res.get(level).add(node.val);
        
        node.left = null;
        node.right = null;
        
        return level;
    }

    public static void main(String[] args) {
        Find_Leaves_of_Binary_Tree_366 obj = new Find_Leaves_of_Binary_Tree_366();
    }
}