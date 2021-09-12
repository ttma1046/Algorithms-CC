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
        TreeNode res = new Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889().constructFromPrePost(new int[] {1, 2, 4, 5, 3, 6, 7},  new int[] {4, 5, 2, 6, 7, 3, 1});
        res = new Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal_889().constructFromPrePostII(new int[] {1, 2, 4, 5, 3, 6, 7},  new int[] {4, 5, 2, 6, 7, 3, 1});
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length != post.length) return null;
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    public TreeNode constructFromPrePost(int[] pre, int prelow, int prehigh, int[] post, int postlow, int posthigh) {
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

        curr.left = constructFromPrePost(pre, prelow + 1, prelow + 1 + length, post, postlow, postOrderNextIndex);
        curr.right = constructFromPrePost(pre, prelow + 2 + length, prehigh, post, postOrderNextIndex + 1, posthigh - 1);

        return curr;
    }

    Map<Integer, Integer> postMap = new HashMap<>();
    int[] pre;
    int[] post;
    int preStart = 0;
    int length;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.length = pre.length;
        this.pre = pre;
        this.post = post;

        for (int i = 0; i < post.length; i++) postMap.put(post[i], i);

        return constructFromPrePostHelper(0, post.length - 1);
    }

    private TreeNode constructFromPrePostHelper(int postStart, int postEnd) {
        if (preStart >= this.length || postStart > postEnd) return null;

        TreeNode node = new TreeNode(pre[preStart++]);

        if (preStart == this.length || postStart == postEnd) return node;

        int postIndex = postMap.get(pre[preStart]);

        node.left = constructFromPrePostHelper(postStart, postIndex);

        node.right = constructFromPrePostHelper(postIndex + 1, postEnd - 1);

        return node;
    }



    // pre  [1, 2, 4, 5, 3, 6, 7]

    // post  [4, 5, 2, 6, 7, 3, 1]

    int[] preorder;
    int[] postorder;

    int length = 0;
    int preStart = 0;
    Map<Integer, Integer> map = new HashMap<>();



    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        this.length = preorder.length;
        for (int i = 0; i < this.length; ++i) map.put(postorder[i], i);

        return helper(0, preorder.length - 1);
    }

    private TreeNode helper(int postStart, int postEnd) {
        if (preStart >= N || postStart > postEnd) return null;

        TreeNode node = new TreeNode(this.preorder[preStart++]);

        if (preStart == N || postStart == postEnd) return node;

        int postIndex = map.get(this.preorder[preStart])

                        node.left = helper(postStart, postIndex);

        node.right = helper(postIndex + 1, postEnd - 1);

        return node;

    }

    Map<Integer, Integer> postMap = new HashMap<>();

    int[] pre;
    int[] post;

    int length;

    public TreeNleode constructFromPrePost(int[] preorder, int[] postorder) {
        this.length = preorder.length;

        this.pre = preorder;
        this.post = postorder;

        for (int i = 0; i < this.length; i++) postMap.put(post[i], i);


        return build(0, this.length - 1, 0, this.length - 1);
    }

    private TreeNode build(int preleft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight || postLeft > postRight) return null;

        TreeNode node = new TreeNode(pre[preLeft]);
        if (preLeft == preRight) return node;

        int index = postMap.get(pre[preLeft + 1]);

        int leftTreeSize = index - postLeft;

        node.left = build(preLeft + 1, preLeft + 1 + leftTreeSize, postLeft, postLeft + leftTreeSize);

        node.right = build(preLeft + leftTreeSize + 2, preRight, postLeft + leftTreeSize + 1, postRight - 1);

        return node;
    }
}
