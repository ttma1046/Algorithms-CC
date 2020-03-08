package recursion;

public class MinimumDistanceBetweenBSTNodes_783 {
    private Integer res = Integer.MAX_VALUE, prev = null;

    int minDiffInBST(TreeNode node) {
        if (node == null) return 0;

        if (node.left != null) minDiffInBST(node.left);

        if (prev != null) res = Math.min(res, node.val - prev);

        prev = node.val;

        if (node.right != null) minDiffInBST(node.right);

        return res;
    }
        
    public int minDiffInBSTII(TreeNode root) {
        res = Integer.MAX_VALUE;
        prev = null;
        inorder(root);
        return res;
    }
    
    public void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        if(prev !=  null){
            res = Math.min(res, root.val - prev);
        }
        prev = root.val;
        inorder(root.right);
    }

    public static void main(String[] args) {
        final TreeNode two = new TreeNode(2);
        two.left = new TreeNode(1);
        two.right = new TreeNode(3);

        final TreeNode four = new TreeNode(4);
        four.left = two;

        four.right = new TreeNode(6);

        System.out.println(new MinimumDistanceBetweenBSTNodes_783().minDiffInBST(four));
    }
}