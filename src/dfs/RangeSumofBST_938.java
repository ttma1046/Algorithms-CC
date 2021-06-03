
/*
Given the root node of a binary search tree,
return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.
*/

package dfs;


/*

     10
  5      15
3  7        18


*/

class RangeSumofBST_938 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        return dfs(root, L, R);
    }

    private int dfs(TreeNode node, int L, int R) {
        int result = 0;

        if (node == null) return result;

        if (node.val >= L && node.val <= R) {
            result += node.val;
        };

        if (node.val > L) {
            result += dfs(node.left, L, R);
        }

        if (node.val < R) {
            result += dfs(node.right, L, R);
        }
        return result;
    }

    public static int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node == null) continue;

            if (node.val >= L && node.val <= R) {
                result += node.val;
            }
            if (node.val > L) {
                stack.push(node.right);
            }

            if (node.val < R) {
                stack.push(node.left);
            }
        }

        return result;
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0; // base case.
        if (root.val < L) return rangeSumBST(root.right, L, R); // left branch excluded.
        if (root.val > R) return rangeSumBST(root.left, L, R); // right branch excluded.
        return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R); // count in both children.
    }

    /*
    Complexity Analysis

    Time Complexity: O(N)O(N), where NN is the number of nodes in the tree.

    Space Complexity: O(H)O(H), where HH is the height of the tree.
    */

    public static void main(String[] args) {
        final TreeNode five = new TreeNode(5);
        five.left = new TreeNode(3);
        five.right = new TreeNode(7);

        final TreeNode fifteen = new TreeNode(15);
        fifteen.right = new TreeNode(18);

        final TreeNode root = new TreeNode(10);
        root.left = five;
        root.right = fifteen;

        System.out.println(new RangeSumofBST_938().rangeSumBST(root, 7, 15));
    }
}


