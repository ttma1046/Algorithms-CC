package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/*
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:

Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:

Input: root = [1,2,3]
Output: [1,3]

Constraints:

The number of nodes in the tree will be in the range [0, 104].
-231 <= Node.val <= 231 - 1
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
class Find_Largest_Value_in_Each_Tree_Row_515 {
    /*
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        queue.offer(root);

        while(queue.size() > 0) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();

                if (node.val > max) max = node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(max);
        }

        return res;
    }
    */

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res, int depth) {
        if (node == null) return;

        if (depth == res.size()) 
            res.add(node.val);
        else if (node.val > res.get(depth))
            res.set(depth, node.val);

        dfs(node.left, res, depth + 1);
        dfs(node.right, res, depth + 1);
    }

    public static void main(String[] args) {
        Find_Largest_Value_in_Each_Tree_Row_515 obj = new Find_Largest_Value_in_Each_Tree_Row_515();

        TreeNode five =  new TreeNode(5);
        TreeNode three =  new TreeNode(3);
        TreeNode threetwo =  new TreeNode(3);
        threetwo.left = five;
        threetwo.right = three;
        TreeNode one =  new TreeNode(1);
        TreeNode two =  new TreeNode(2);
        TreeNode nine =  new TreeNode(9);
        two.right = nine;
        one.left = three;
        one.right = two;

        List<Integer> res = obj.largestValues(one);

        for (int a : res) System.out.println(a);
    }
}