package tree;
import java.util.List;
import java.util.ArrayList;
/*
Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.

Example 1:

Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
*/

class Balance_a_Binary_Search_Tree_1382 {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorderSort(root, list);
        int n = list.size();
        return balance(list, 0, n - 1);
    }

    private void inorderSort(TreeNode curr, List<TreeNode> list) {
        if (curr == null) return;
        inorderSort(curr.left, list);
        list.add(curr);
        inorderSort(curr.right, list);
    }

    private TreeNode balance(List<TreeNode> list, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = list.get(mid);
        node.left = balance(list, start, mid - 1);
        node.right = balance(list, mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        TreeNode fifteen = new TreeNode(15);
        fifteen.left = new TreeNode(9);
        fifteen.right = new TreeNode(20);

        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = fifteen;

        Balance_a_Binary_Search_Tree_1382 obj = new Balance_a_Binary_Search_Tree_1382();

        TreeNode node = obj.balanceBST(root);
    }
}




