package tree;
import java.util.Arrays;

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,

construct and return the binary tree.



Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
*/
class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null) return null;

        int n = inorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(postorder[n - 1]);
        if (n == 1) return root;

        int L =  0;
        for (int i = 0; i < n; ++i) if (inorder[i] == postorder[n - 1]) L = i;

        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]

        root.left = buildTree(Arrays.copyOfRange(inorder, 0, L), Arrays.copyOfRange(postorder, 0, L));
        root.right = buildTree(Arrays.copyOfRange(inorder, L + 1, n), Arrays.copyOfRange(postorder, L, n - 1));

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }

        return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private static TreeNode buildTree(int [] inorder, int [] postorder, int start, int end, int rootIndex) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[rootIndex]);
        int i = 0;
        while (postorder[rootIndex] != inorder[end - i]) {
            i++;
        }

        root.left = buildTree(inorder, postorder, start, end - i - 1, rootIndex - i - 1);
        root.right = buildTree(inorder, postorder, end - i + 1, end, rootIndex - 1);

        return root;
    }

    public static void main(String[] args) {
        TreeNode res = new Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_106().buildTree(new int[] {9, 3, 15, 20, 7},  new int[] {9, 15, 7, 20, 3});
    }
}
