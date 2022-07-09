package tree;

/*
Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:

Input: root = []
Output: 0

Example 3:

Input: root = [1]
Output: 1

Constraints:

The number of nodes in the tree is in the range [0, 5 * 104].
0 <= Node.val <= 5 * 104
The tree is guaranteed to be complete.
*/
/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
/**/
class Count_Complete_Tree_Nodes_222 {
    public int countNodes(TreeNode root) {
        return root != null ?
               1 + countNodes(root.left) + countNodes(root.right)
               : 0;
    }

    public int countNodesII(TreeNode root) {
        int h = 0;
        // 计算树的高度
        while (root != null) {
            root = root.left;
            h++;
        }
        // 节点总数就是 2^h - 1
        return (int)Math.pow(2, h) - 1;
    }


    public static void main(String[] args) {
        Count_Complete_Tree_Nodes_222 obj = new Count_Complete_Tree_Nodes_222();

        TreeNode four = new TreeNode(4);

        TreeNode two = new TreeNode(2);
        two.left = four;
        two.right = new TreeNode(5);

        TreeNode six = new TreeNode(6);
        TreeNode three = new TreeNode(3);
        // three.left = six;

        TreeNode one = new TreeNode(1);
        one.right = three;
        one.left = two;

        System.out.println(obj.countNodesMy(one));
    }

    /*
     1
   2   3
  4 5 
  */

    private int height(TreeNode root) {
        return root == null ?
               -1 : 1 + height(root.left);
    }


    public int countNodesMy(TreeNode root) {
        int nodes = 0, h = height(root);

        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h - 1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }

    public int countNodesI(TreeNode root) {
        TreeNode l = root, r = root;
        // 沿最左侧和最右侧分别计算高度
        int hl = 0, hr = 0;

        while (l != null) {
            l = l.left;
            hl++;
        }

        while (r != null) {
            r = r.right;
            hr++;
        }
        // 如果左右侧计算的高度相同，则是一棵满二叉树
        if (hl == hr)
            return (int)Math.pow(2, hl) - 1;

        // 如果左右侧的高度不同，则按照普通二叉树的逻辑计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}