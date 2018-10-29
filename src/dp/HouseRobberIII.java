package dp;

public class HouseRobberIII {
    public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode(int x) { val = x; }
    }

    public int Rob(TreeNode root) {
        int [] memo = new int[1999];
        if (root == null) {
            return 0;
        }

        TreeNode current = root;

        if (current.left == null && current.right == null) {
            return current.val;
        }

        memo[0] = current.val;

        current = current.left != null ? root.left : root.right

        memo[1] = Math.max(memo[0], current.val);

        memo[2] = 2222;

        int x = 3;

        while (current != null) {
            memo[x] = Math.max(memo[(x - 1) / 2], current.val + memo[((x-1)/2 - 1) / 2];

            x++;
        }

        return memo[x];
    }
}
