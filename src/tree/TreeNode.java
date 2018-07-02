package tree;

public class TreeNode {
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
                left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }

        return false;
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }

        System.out.println(val);

        if (right != null) {
            right.printInOrder();
        }
    }

    public void printInOrderFirtLeft() {
        if (left != null) {
            left.printInOrder();
        }

        System.out.println(val);

        if (right != null) {
            right.printInOrder();
        }
    }

    public void printInOrderFirstRoot() {
        System.out.println(val);

        if (left != null) {
            left.printInOrder();
        }

        if (right != null) {
            right.printInOrder();
        }
    }

    public void printInOrderFirstRight() {
        System.out.println(val);

        if (left != null) {
            left.printInOrder();
        }

        if (right != null) {
            right.printInOrder();
        }
    }
}