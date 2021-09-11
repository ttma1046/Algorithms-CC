package tree;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Collections;
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
class Binary_Tree_Level_Order_Traversal_II_107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        
        while(q.size() > 0) {
            int size = q.size();
            
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            // res.add(0, level);
            res.add(level);
        }

        Collections.reverse(res);
        
        return res;
    }
}