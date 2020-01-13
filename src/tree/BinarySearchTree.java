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

    boolean checkBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.data < min || root.data > max) {
            return false;
        }

        return checkBST(root.left, min, root.data - 1) && checkBST(root.right, root.data + 1, max);
    }

}
