package bfs;

public class ValidateBinarySearchTree_98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return myBFS(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean myBFS(TreeNode current, long min, long max) {
        if (current == null) {
            return true;
        }

        if (current.val <= min || current.val >= max) {
            return false;
        }

        return myBFS(current.left, min, current.val) && myBFS(current.right, current.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(new ValidateBinarySearchTree_98().isValidBST(root));

        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(3);
        four.right = new TreeNode(6);

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = four;

        System.out.println(new ValidateBinarySearchTree_98().isValidBST(root));
    }
}
