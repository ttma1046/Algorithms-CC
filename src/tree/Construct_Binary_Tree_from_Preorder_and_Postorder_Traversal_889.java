package tree;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
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
    public TreeNode constructFromPrePostI(int[] preorder, int[] postorder) {
        if (preorder == null || preorder.length == 0) 
            return null;

        if (postorder == null || postorder.length == 0) 
            return null;

        int n = preorder.length;
        
        TreeNode root = new TreeNode(preorder[0]);
        if (n == 1) 
            return root;

        int index =  0;
        for (int i = 0; i < n; ++i) 
            if (post[i] == preorder[1]) 
                index = i;

        root.left = constructFromPrePostI(Arrays.copyOfRange(preorder, 1, index + 2), Arrays.copyOfRange(post, 0, index + 1));
        root.right = constructFromPrePostI(Arrays.copyOfRange(preorder, index + 2, n), Arrays.copyOfRange(post, index + 1, n - 1));

        return root;
    }

    public static void main(String[] args) {
        TreeNode res = new Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889().constructFromPrePostI(new int[] {1, 2, 4, 5, 3, 6, 7},  new int[] {4, 5, 2, 6, 7, 3, 1});
        res = new Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889().constructFromPrePostII(new int[] {1, 2, 4, 5, 3, 6, 7},  new int[] {4, 5, 2, 6, 7, 3, 1});
    }

    public TreeNode constructFromPrePostII(int[] pre, int[] post) {
        if (pre.length != post.length) return null;
        return constructFromPrePostII(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    public TreeNode constructFromPrePostII(int[] pre, int prelow, int prehigh, int[] post, int postlow, int posthigh) {
        if (prelow > prehigh || postlow > posthigh) return null;

        // Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]

        TreeNode curr = new TreeNode(pre[prelow]);

        int postOrderNextIndex = -1;

        for (int i = postlow; i < posthigh; ++i) {
            if (post[i] == pre[prelow + 1]) {
                postOrderNextIndex = i;
                break;
            }
        }

        if (postOrderNextIndex == -1) return curr;

        int length = postOrderNextIndex - postlow;

        curr.left = constructFromPrePostII(pre, prelow + 1, prelow + 1 + length, post, postlow, postOrderNextIndex);
        curr.right = constructFromPrePostII(pre, prelow + 2 + length, prehigh, post, postOrderNextIndex + 1, posthigh - 1);

        /*
        int length = postOrderNextIndex - postlow + 1;
        
        node.left = constructFromPrePost(pre, postlow + 1, postlow + length, post, postlow, postOrderNextIndex);
        node.right = constructFromPrePost(pre, postlow + length + 1, prehigh, post, postOrderNextIndex + 1, posthigh - 1);
        */
        return curr;
    }

    
    Map<Integer, Integer> postMap = new HashMap<>();
    int[] pre;
    int[] post;
    int preStart = 0;
    int length;
    public TreeNode constructFromPrePostIII(int[] pre, int[] post) {
        this.length = pre.length;
        this.pre = pre;
        this.post = post;

        for (int i = 0; i < post.length; i++) 
            postMap.put(post[i], i);

        return constructFromPrePostHelper(0, post.length - 1);
    }

    private TreeNode constructFromPrePostHelper(int postStart, int postEnd) {
        if (preStart >= this.length || postStart > postEnd) 
            return null;

        TreeNode node = new TreeNode(pre[preStart++]);

        if (preStart == this.length || postStart == postEnd) 
            return node;

        int postIndex = postMap.get(pre[preStart]);

        node.left = constructFromPrePostHelper(postStart, postIndex);
        node.right = constructFromPrePostHelper(postIndex + 1, postEnd - 1);

        return node;
    }
    
    // pre  [1, 2, 4, 5, 3, 6, 7]
    // post  [4, 5, 2, 6, 7, 3, 1]
    Map<Integer, Integer> postMap = new HashMap<>();
    public TreeNode constructFromPrePostIV(int[] preorder, int[] postorder) {
        if (preorder.length != postorder.length || preorder.length == 0 || postorder.length == 0)
            return null;

        int n = preorder.length; 

        for (int i = 0; i < n; i++)
            postMap.put(postorder[i], i);

        return build(preorder, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode build(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd)
            return null;

        TreeNode node = new TreeNode(pre[preStart]);

        if (preStart == preEnd)
            return node;

        int index = postMap.get(pre[preStart + 1]);

        int length = index - postStart;

        node.left = build(pre, preStart + 1, preStart + 1 + length, post, postStart, index);
        node.right = build(pre, preStart + 2 + length, preEnd, post, index + 1, postEnd - 1);

        return node;
    }
}
