package tree;
import java.util.Arrays;
/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
*/
class Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889 {
    /*
    public TreeNode buildTree(int[] preorder, int[] postorder) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        if (N == 1) return root;

        int L = 0;
        for (int i = 0; i < N; ++i) if (postorder[i] == preorder[1]) L = i + 1;

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, L + 1),
                                         Arrays.copyOfRange(postorder, 0, L));
        root.right = buildTree(Arrays.copyOfRange(preorder, L + 1, N),
                                          Arrays.copyOfRange(postorder, L, N - 1));
        return root; 
    }
    */


    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null) return null;

        int n = pre.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(pre[0]);
        if (n == 1) return root;

        int L =  0;
        for (int i = 0; i < n; ++i) if (post[i] == pre[1]) L = i;

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L + 2), Arrays.copyOfRange(post, 0, L + 1));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L + 2, n), Arrays.copyOfRange(post, L + 1, n - 1));

        return root;
    }

    public static void main(String[] args) {
        TreeNode res = new Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889().constructFromPrePost(new int[] {1,2,4,5,3,6,7},  new int[] {4,5,2,6,7,3,1});
    }
}


