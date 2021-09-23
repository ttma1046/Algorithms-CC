package tree;



public class BinarySearchTree {
    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data < min || node.data > max) {
            return false;
        }

        return checkBST(node.left, min, node.data - 1) && checkBST(node.right, node.data + 1, max);
    }

    public TreeNode search(TreeNode root, int target) {
        TreeNode curr = root;
        while(true) {
            if (curr == null) return curr;
            if (curr.val == target) return curr;
            if (curr.val > target) curr = curr.left;
            else curr = curr.right;
        }
    }

    public TreeNode search(TreeNode root, int target) {
        if (curr == null || curr.val == target) return curr;

        if (root.val < target) return search(root.right, target);

        return search(root.left, target);
    }

    // recursive
    TreeNode insert(TreeNode root, int target) {
        if (root == null) return new TreeNode(target);

        if (root.val > target) root.left = insert(root.left, target);
        else if (root.val < target) root.right = insert(root.right, target);
        return root;
    }

    // iterative
    public TreeNode insert(TreeNode root, int target) {
        TreeNode curr = root;

        TreeNode node = new TreeNode(target);

        if (curr == null) return node;

        TreeNode prev = null;

        while(curr != null) {
            prev = curr;
            if (curr.val < target) curr = curr.right;
            else curr = curr.left;
        }

        if (prev.val < target) prev.right = node;
        else prev.left = node;
        
        return root;
    }
}

Class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

/*
                    Search Element       Insert Element      Delete Element
       array            O(n)                  O(n)                O(n)
    sorted array        O(logn)               O(n)                O(n)
        BST             O(logn)               O(logn)             O(logn)
*/
