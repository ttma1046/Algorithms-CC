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
class Find_Bottom_Left_Tree_Value_513 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> deque = new LinkedList<>();

        deque.offer(root);

        int res = 0;
        while(deque.size() > 0) {
            int size = deque.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = deque.poll();

                if (i == 0) res = node.val;

                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
        }

        return res;
    }

    int res = 0;
    int mostHeight = 0;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return this.res;
    }

    private void dfs(TreeNode node, int depth) {
        if (depth > mostHeight) {
            res = node.val;
            mostHeight = depth;
        }

        if (node.left != null) dfs(node.left, depth + 1);
        if (node.right != null) dfs(node.right, depth + 1);
    }

    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[] {0, 0});
    }

    private int findBottomLeftValue(TreeNode node, int depth, int[] res) {
        if (depth > res[1]) {
            res[0] = node.val;
            res[1] = depth;
        }

        if (node.left != null) findBottomLeftValue(node.left, depth + 1, res);
        if (node.right != null) findBottomLeftValue(node.right, depth + 1, res);

        return res[0];
    }

    public static void main(String[] args) {
        Find_Bottom_Left_Tree_Value_513 obj = new Find_Bottom_Left_Tree_Value_513();
    }
}