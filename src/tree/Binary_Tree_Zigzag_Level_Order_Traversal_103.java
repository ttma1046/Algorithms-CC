package tree;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:

Input: root = [1]
Output: [[1]]

Example 3:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Binary_Tree_Zigzag_Level_Order_Traversal_103 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);

        while(q.size() > 0) {
            int size = q.size();

            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();

                if (res.size() % 2 == 0)
                    level.add(cur.val);
                else
                    level.add(0, cur.val);

                if (cur.left != null) 
                    q.offer(cur.left);
                if (cur.right != null) 
                    q.offer(cur.right);
            }

            res.add(level);
        }

        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<List<Integer>> res, int height) {
        if (root == null) 
            return;
        
        if (res.size() <= height) 
            res.add(new ArrayList<Integer>());

        if (height % 2 == 0) 
            res.get(height).add(root.val);
        else 
            res.get(height).add(0, root.val);

        dfs(root.left, res, height + 1);
        dfs(root.right, res, height + 1);
    }

    public List<List<Integer>> zigzagLevelOrderII(TreeNode root) {
        List <List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();

        q.offer(root);

        while(q.size() > 0) {
            int size = q.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();

                if (res.size() % 2 == 0)
                    currentLevel.add(node.val);
                else
                    currentLevel.add(0, node.val);

                if (node.left != null) 
                    q.offer(node.left);
                if (node.right != null) 
                    q.offer(node.right);
            }

            res.add(currentLevel);
        }

        return res;
    }

    public static void main(String[] args) {
        Binary_Tree_Zigzag_Level_Order_Traversal_103 obj = new Binary_Tree_Zigzag_Level_Order_Traversal_103();

        TreeNode fifteen = new TreeNode(15);
        TreeNode seven = new TreeNode(7);

        TreeNode twenty = new TreeNode(20);

        twenty.left = fifteen;
        twenty.right = seven;

        TreeNode three = new TreeNode(3);
        three.right = twenty;
        three.left = new TreeNode(9);

        obj.zigzagLevelOrder(three);
    }
}