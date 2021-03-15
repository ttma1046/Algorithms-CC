package tree;
import java.util.Arrays;

/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,

construct and return the binary tree.

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]\


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
*/
class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_105 {

    /*
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        # 实际上inorder 和 postorder一定是同时为空的，因此你无论判断哪个都行
        if not preorder:
            return None
        root = TreeNode(preorder[0])

        i = inorder.index(root.val)
        root.left = self.buildTree(preorder[1:i + 1], inorder[:i])
        root.right = self.buildTree(preorder[i + 1:], inorder[i + 1:])

        return root
    */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        if (preorder == null) return null;

        ListNode root = new TreeNode(preorder[0]);

        int i = inorder.indexOf(root.val);

        root.left = buildTree(preorder(1, i), inorder(0, i - 1));
        root.right = buildTree(preorder(i, n - 1), inorder(i, n - 1));

        return root;
        */


        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

        /*

        3   9   20 15 7

        9   3   15 20 7
        */

        if (preorder == null) return null;

        int n = preorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        if (n == 1) return root;

        int L =  0;
        for (int i = 0; i < n; ++i) if (inorder[i] == preorder[0]) L = i;

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, L + 1), Arrays.copyOfRange(inorder, 0, L));
        root.right = buildTree(Arrays.copyOfRange(preorder, L + 1, n), Arrays.copyOfRange(inorder, L + 1, n));

        return root;
    }

    // O(n) Time Complexcity
    // O(n) Space Complexity
    public static void main(String[] args) {
        TreeNode res = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_105().buildTree(new int[] {3, 9, 20, 15, 7},  new int[] {9, 3, 15, 20, 7});
        TreeNode binaryTree = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_105().buildBinarySearchTree(new int[] {3, 9, 20, 15, 7});
    }

    public TreeNode buildBinarySearchTree(int[] preorder) {
        int n = preorder.length;
        int[] temp = new int[preorder.length];

        for (int i = 0; i < n; i++) {
            temp[i] = preorder[i];
        }

        Arrays.sort(preorder);

        for (int i = 0; i < preorder.length; i++) {
            if (temp[i] != preorder[i]) System.out.println("not same");
        }

        return buildTree(preorder, temp);
    }
}