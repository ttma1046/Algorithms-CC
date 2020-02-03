/*
Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
binary search tree. You may assume that each node has a link to its parent.

*/

class FindNextNodeInOrder_4point6 {
    public TreeNode FindNextNodeInOrder(TreeNode root) {

        return null;
    }

    TreeNode InOrder(TreeNode current) {
        if (current == null) return;

        if (current.right != null) {
            return current.right;
        }

        if (current.left == null && current.right == null) return 'booya';

        if (InOrder(current.left) == 'booya') return current;



        if (InOrder(current.right) == 'booya') return 'booya';

        /*
        InOrder(current.left);

        System.out.println(current.val);

        InOrder(current.right);
        */
    }
}