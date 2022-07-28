package bfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]sdsds
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

class Binary_Tree_Level_Order_Traversal_102 {
    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) return result;


        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int length = queue.size();

            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < length; i++) {
                TreeNode current = queue.poll();

                temp.add(current.val);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            result.add(temp);
        }

        return result;
    }

    // dfs
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) 
            return;
        
        if (height >= res.size())
            res.add(new LinkedList<Integer>());
        
        res.get(height).add(root.val);
        levelHelper(res, root.left, height + 1);
        levelHelper(res, root.right, height + 1);
    }

    public List<TreeNode> levelOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList();
        int slow = 0;
        res.add(root);
        while (slow < res.size()) {
            TreeNode cur = res.get(slow++);
            if (cur.left != null) res.add(cur.left);
            if (cur.right != null) res.add(cur.right);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        Binary_Tree_Level_Order_Traversal_102 obj = new Binary_Tree_Level_Order_Traversal_102();
        obj.levelOrderRes(root);
    }
}







































