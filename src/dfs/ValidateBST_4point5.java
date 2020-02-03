package dfs;

class ValidateBST_4point5 {
    public boolean ValidateBST(TreeNode root) {

        if (root == null) {
            return true;
        }
        

        return Validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean Validate(TreeNode current, int min, int max) {

        if (current == null) return true;

        if (current.val >= max || current.val < min) {
            return false;
        }

        return Validate(current.left, min, current.val) && Validate(current.right, current.val, max);
    }

    int index = 0;

    void CopyBST(TreeNode root, int[] array) {
        if (root == null) return;
        copyBST(root.left, array);
        array[index] = root.data;
        index++;
        copyBST(root.right, array);
    }

    boolean checkBST(TreeNode root) {
        if (root == null) return true;

        int[] array = new int[root.size];

        copyBST(root, array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= array[i - 1]) return false;
        }

        return true;
    }

    Integer last_printed = null;
    boolean checkBST(TreeNode n) {
        if (n == null) return true;

        // Check / recurse left
        if (!checkBST(n.left)) return false;

        // Check current
        if (last_printed != null && n.data < last_printed) {
            return false;
        }

        last_printed = n.data;

        // Check / resurse right
        if (!checkBST(n.right)) return false;

        return true; // All good!
    }
}