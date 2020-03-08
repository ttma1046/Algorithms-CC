
/*
Given the root node of a binary search tree,
return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.
*/

package recursion;


/*

     10
  5      15
3  7        18


*/

class RangeSumofBST_938 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        return cal(root, L, R);
    }

    private int cal(TreeNode node, int L, int R) {
        int result = 0;

        if (node == null) return result;

        if (node.val > L) {
            result += cal(node.left, L, R);
        }

        if(node.val >= L && node.val <= R) {
            result += node.val;
        };

        if (node.val < R) {
            result += cal(node.right, L, R);
        }
        return result;
    }

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


