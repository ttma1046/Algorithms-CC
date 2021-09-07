package tree;

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

                if (res.size() % 2 == 0) level.add(cur.val);
                else level.add(0, cur.val);

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
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
        if (root == null) return;
        if (res.size() <= height) res.add(new ArrayList<Integer>());

        if (height % 2 == 0) res.get(height).add(root.val);

        else res.get(height).add(0, root.val);

        dfs(root.left, res, height + 1);
        dfs(root.right, res, height + 1);
    }
}