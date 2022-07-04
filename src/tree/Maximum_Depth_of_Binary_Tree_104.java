package tree;
import java.util.Deque;
import java.util.ArrayDeque;
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
class Maximum_Depth_of_Binary_Tree_104 {
    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;
    // 主函数
    int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    void traverse(TreeNode root) {
        if (root == null)
            return;
        
        // 前序位置
        depth++;
        if (root.left == null && root.right == null)
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        
        traverse(root.left);
        traverse(root.right);
        // 后序位置
        depth--;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Maximum_Depth_of_Binary_Tree_104 obj = new Maximum_Depth_of_Binary_Tree_104();
    }

    public int maxDepthII(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        TreeNode curr;
        Deque<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);

        while (queue.size() > 0) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                curr = queue.poll();

                if (curr.left != null)
                    queue.offer(curr.left);

                if (curr.right != null)
                    queue.offer(curr.right);
            }
        }

        return depth;
    }
}