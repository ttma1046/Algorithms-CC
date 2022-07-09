package tree;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
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
    // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

    /*
    3   9   20 15 7

    9   3   15 20 7
    */

    /*
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) return null;
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int [] preorder, int preLow, int preHigh, int[] inorder, int inLow, int inHigh) {
        if (preLow > preHigh || inLow > inHigh) return null;
        TreeNode root = new TreeNode(preorder[preLow]);

        int inorderRoot = inLow;
        for (int i = inLow; i <= inHigh; i++) {
            if (inorder[i] == root.val) {
                inorderRoot = i;
                break;
            }
        }

        int leftTreeLen = inorderRoot - inLow;
        root.left = build(preorder, preLow + 1, preLow + leftTreeLen, inorder, inLow, inorderRoot - 1);
        root.right = build(preorder, preLow + leftTreeLen + 1, preHigh, inorder, inorderRoot + 1, inHigh);
        return root;
    }
    */


    private int pre = 0;
    private int in = 0;

    public TreeNode buildTreeFast(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length)
            return null;

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
        if (preorder == null)
          return null;()

        ListNode root = new TreeNode(preorder[0]);

        int i = inorder.indexOf(root.val);

        root.left = buildTree(preorder(1, i), inorder(0, i - 1));
        root.right = buildTree(preorder(i, n - 1), inorder(i, n - 1));

        return root;
        */

        if (preorder == null)
            return null;

        int n = preorder.length;
        if (n == 0)
            return null;

        TreeNode root = new TreeNode(preorder[0]);

        if (n == 1)
            return root;

        int L =  0;
        for (int i = 0; i < n; ++i)
            if (inorder[i] == preorder[0])
                L = i;

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, L + 1), Arrays.copyOfRange(inorder, 0, L));
        root.right = buildTree(Arrays.copyOfRange(preorder, L + 1, n), Arrays.copyOfRange(inorder, L + 1, n));

        return root;
    }

    // Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            return null;

        int n = preorder.length;
        return buildTreeRes(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    TreeNode buildTreeRes(
        int[] pre, int preStart, int preEnd,
        int[] in, int inStart, int inEnd
    ) {
        if (pre == null || pre.length == 0)
            return null;

        if (in == null || in.length == 0)
            return null;

        if (preStart > preEnd || inStart > inEnd)
            return null;

        TreeNode node = new TreeNode(pre[preStart]);

        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == node.val) {
                index = i;
                break;
            }
        }

        int length = index - inStart;

        node.left = buildTreeRes(pre, preStart + 1, preStart + length, in, inStart, index - 1);
        node.right = buildTreeRes(pre, preStart + length + 1, preEnd, in, index + 1, inEnd);

        return node;
    }

    public TreeNode buildBinarySearchTree(int[] preorder) {
        int n = preorder.length;
        int[] temp = new int[preorder.length];

        for (int i = 0; i < n; i++) {
            temp[i] = preorder[i];
        }

        Arrays.sort(preorder);

        for (int i = 0; i < preorder.length; i++) {
            if (temp[i] != preorder[i])
                System.out.println("not same");
        }

        return buildTree(preorder, temp);
    }

    public TreeNode buildTreeNice(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            return null;
        return buildTreeNode(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeNode(int[] preorder, int prelow, int prehigh, int[] inorder, int inlow, int inhigh) {
        if (prelow > prehigh || inlow > inhigh)
            return null;

        TreeNode current = new TreeNode(preorder[prelow]);

        int inorderRootIndex = inlow;
        for (int i = inlow; i <= inhigh; i++) {
            if (inorder[i] == current.val) {
                inorderRootIndex = i;
                break;
            }
        }

        int length = inorderRootIndex - inlow;

        current.left = buildTreeNode(preorder, prelow + 1, prelow + length, inorder, inlow, inorderRootIndex - 1);

        current.right = buildTreeNode(preorder, prelow + length + 1, prehigh, inorder, inorderRootIndex + 1, inhigh);

        return current;
    }

    int[] preOrder; // [3, 9, 1, 2, 20, 15, 7]
    int[] inOrder;  // [1, 9, 2, 3, 15, 20, 7]
    int preOrderIndex;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTreeIV(int[] preOrder, int[] inOrder) {
        this.preOrder = preOrder;
        this.inOrder = inOrder;

        this.preOrderIndex = 0;
        int length = preOrder.length;

        for (int i = 0; i < length; i++)
            map.put(inOrder[i], i);
        return helperbuilder(0, length - 1);
    }

    /*
    [
       { 1, 0 },
       { 9, 1 },
       { 2, 2 },
       { 3, 3 },
       { 15, 4 },
       { 20, 5 },
       { 7, 6 }
    ]
    */
    public TreeNode helperbuilder(int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        TreeNode root = new TreeNode(this.preOrder[this.preOrderIndex++]);

        int index = map.get(root.val);

        root.left = helperbuilder(inStart, index - 1);
        root.right = helperbuilder(index + 1, inEnd);
        return root;
    }

    // O(n) Time Complexcity
    // O(n) Space Complexity
    public static void main(String[] args) {
        Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_105 obj = new Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_105();

        TreeNode res = obj.buildTree(new int[] {3, 9, 20, 15, 7},  new int[] {9, 3, 15, 20, 7});
        TreeNode binaryTree = obj.buildBinarySearchTree(new int[] {3, 9, 20, 15, 7});
        binaryTree = obj.buildTreeNice(new int[] {3, 9, 20, 15, 7},  new int[] {9, 3, 15, 20, 7});
    }

    // My Map

    private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder.length == 0 || inorder.length == 0)
            return null;
        
        int n = preorder.length;
        
        for (int i = 0; i < n; i++) 
            map.put(inorder[i], i);
        
        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }
    
    private TreeNode buildTree(
        int[] pre,
        int preStart,
        int preEnd,
        int[] in,
        int inStart,
        int inEnd    
    ) {
        if (preStart > preEnd || inStart > inEnd)
            return null;
        
        TreeNode node = new TreeNode(pre[preStart]);
        
        int index = map.get(pre[preStart]);
        
        int length = index - inStart;
        
        node.left = buildTree(pre, preStart + 1, preStart + length, in, inStart, index - 1);
        node.right = buildTree(pre, preStart + length + 1, preEnd, in, index + 1, inEnd);
        return node;      
    }
}