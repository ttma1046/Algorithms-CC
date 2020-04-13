package tree;

public class BinaryTreeMaximumPathSum_124 {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        postorderTraversal(root);
        return result;
    }
    
    private int postorderTraversal(TreeNode node) {
        if (node == null) 
            return 0;
        
        int left = Math.max(postorderTraversal(node.left), 0);
        
        int right = Math.max(postorderTraversal(node.right), 0);
                
        result = Math.max(result, left + right + node.val);
        
        return node.val + Math.max(left, right);
    }

    public int maxPathSumII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return postorderTraversalII(root)[0];
    }
    
    private int[] postorderTraversalII(TreeNode node) {
        if (node == null) 
            return new int [] {Integer.MIN_VALUE, 0};

        int[] left = postorderTraversalII(node.left);

        int[] right = postorderTraversalII(node.right);
        
        int leftbig = 0;
        
        if (left[1] > 0) {
            leftbig = left[1];
        }
        
        int rightbig = 0;
        if (right[1] > 0) {
            rightbig = right[1];
        }
        
        int result = Math.max(left[0], right[0]);

        result = Math.max(result, rightbig + leftbig + node.val);
        
        return new int[] { result, node.val + Math.max(leftbig, rightbig) };
    }

    public int maxPathSumIII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        postorderTraversalIII(root);

        return result;
    }
    
    private int postorderTraversalIII(TreeNode node) {
        if (node == null) return 0;
        int leftSum = postorderTraversalIII(node.left);
        int rightSum = postorderTraversalIII(node.right);
        result = Math.max(result, node.val + leftSum + rightSum);
        return Math.max(0, node.val + Math.max(leftSum, rightSum));
    }

    public static void main(String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = twenty;

        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSumIII(root));
        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSumII(root));
        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSum(root));
    }
}