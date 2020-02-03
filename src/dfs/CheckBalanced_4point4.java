packagee dfs;

/*
Implement a function to check if a binary tree is balanced. For the purposes of
this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
node never differ by more than one.
*/

class CheckBalanced_4point4 {
    int getHeight(TreeNode root) {
        if (root == null) return -1; // Base case
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    boolean isBalanced(TreeNode root) {
        if (root == null) return true; // Base case

        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else { // Recurse
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    int checkHeight(TreeNode root) {
        if (root == null) return -1;

        int leftHeight = checkHeight(root.left);
        if (leftHight == Integer.MIN_VALUE) return Integer.MIN_VALUE: // Pass error up

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Pass error up

        in heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Found error -> pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    boolean isBalanced(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    public static void main(String[] args) {

        final TreeNode eleven = new TreeNode(11);
        eleven.left = new TreeNode(7);
        eleven.right = new TreeNode(2);

        final TreeNode four = new TreeNode(4);
        four.left = eleven;

        final TreeNode anotherFour = new TreeNode(4);
        anotherFour.right = new TreeNode(1);

        final TreeNode eight = new TreeNode(8);
        eight.left = new TreeNode(13);
        eight.right = anotherFour;

        final TreeNode root = new TreeNode(5);
        root.left = four;
        root.right = eight;

        System.out.println(new CheckBalanced_4point4().isBalanced(root));

    }

}