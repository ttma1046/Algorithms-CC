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
    public TreeNode buildTreeI(int[] inorder, int[] postorder) {
        if (inorder == null) return null;

        int n = inorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(postorder[n - 1]);
        if (n == 1) return root;

        int L =  0;
        for (int i = 0; i < n; ++i) if (inorder[i] == postorder[n - 1]) L = i;

        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]

        root.left = buildTreeI(Arrays.copyOfRange(inorder, 0, L), Arrays.copyOfRange(postorder, 0, L));
        root.right = buildTreeI(Arrays.copyOfRange(inorder, L + 1, n), Arrays.copyOfRange(postorder, L, n - 1));

        return root;
    }

    public TreeNode buildTreeII(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }

        return buildTreeII(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode buildTreeII(int [] inorder, int [] postorder, int start, int end, int rootIndex) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[rootIndex]);
        int i = 0;
        while (postorder[rootIndex] != inorder[end - i]) {
            i++;
        }

        root.left = buildTreeII(inorder, postorder, start, end - i - 1, rootIndex - i - 1);
        root.right = buildTreeII(inorder, postorder, end - i + 1, end, rootIndex - 1);

        return root;
    }

    public static void main(String[] args) {
        TreeNode res = new Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_106().buildTreeII(new int[] {9, 3, 15, 20, 7},  new int[] {9, 15, 7, 20, 3});
        res = new Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_106().buildTreeNice(new int[] {9, 3, 15, 20, 7},  new int[] {9, 15, 7, 20, 3});
    }

    public TreeNode buildTreeNice(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) return null;
        return buildTreeNode(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeNode(int[] inorder, int inlow, int inhigh, int[] postorder, int postlow, int posthigh) {
        if (inlow > inhigh || postlow > posthigh) return null;

        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        TreeNode current = new TreeNode(postorder[posthigh]);

        int inorderRootIndex = inlow;
        for (int i = inlow; i <= inhigh; i++) {
            if (inorder[i] == current.val) {
                inorderRootIndex = i;
                break;
            }
        }

        int length = inorderRootIndex - inlow;

        current.left = buildTreeNode(inorder, inlow, inorderRootIndex - 1, postorder, postlow, postlow + length - 1);
        current.right = buildTreeNode(inorder, inorderRootIndex + 1, inhigh, postorder, postlow + length, posthigh - 1);

        return current;
    }
}
