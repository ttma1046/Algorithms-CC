package tree;


public class TreeNode { // Binary Search Tree
    TreeNode left;
    TreeNode right;

    int val;

    public TreeNode(int x) {
        this.val = x;
    }

    public void insert(int value) {
        if (value <= val) {
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            // go to right
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(int value) {
        if (value == val) {
            return true;
        } else if (value < val) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    boolean checkBST(TreeNode root) {
        return true;
    }

    public void printPreOrder() {
        // root => left => right
        System.out.println(val);

        if (left != null) {
            left.printPreOrder();
        }

        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printInOrder() {
        // left => root => right

        if (left != null) {
            left.printInOrder();
        }

        System.out.println(val);

        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPostOrder() {
        // left => right => root
        if (left != null) {
            left.printPostOrder();
        }

        if (right != null) {
            right.printPostOrder();
        }

        System.out.println(val);
    }
}