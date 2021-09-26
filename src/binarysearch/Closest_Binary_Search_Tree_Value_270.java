package binarysearch;
/**
 * Definition for a binary tree node.
 *
**/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/*
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

Example 1:

Input: root = [4,2,5,1,3], target = 3.714286
Output: 4

Example 2:

Input: root = [1], target = 4.428571
Output: 1


Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
*/

class Closest_Binary_Search_Tree_Value_270 {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;

        while(root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) closest = root.val;
            root = root.val > target ? root.left : root.right;
        }

        return closest;
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int closestValue(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }

    public int closestValue(TreeNode root, double target) {
        Stack<TreeNode> stack = new LinkedList();
        long pred = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();

            if (pred <= target && target < root.val)
                return Math.abs(pred - target) < Math.abs(root.val - target) ? (int)pred : root.val;

            pred = root.val;
            root = root.right;
        }
        return (int)pred;
    }

    int res = 0;
    double min = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        dfs(root, target);

        return res;
    }

    private void dfs(TreeNode root, double target) {
        if (root == null) return;
        if (Math.abs(root.val - target) < min) {
            min = Math.abs(root.val - target);
            res = root.val;
        }

        if (root.val > target) dfs(root.left, target);
        else dfs(root.right, target);
    }

    public int closestValue(TreeNode root, double target) {
        int closest = root.val;

        while(root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) closest = root.val; 
            root = root.val > target ? root.left : root.right;
        }

        return closest;
    }

    public static void main(String[] args) {
        Closest_Binary_Search_Tree_Value_270 obj = new Closest_Binary_Search_Tree_Value_270();

        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);

        TreeNode two = new TreeNode(2);
        two.left = one;
        two.right = three;

        TreeNode five = new TreeNode(5);

        TreeNode root = new TreeNode(4);
        root.left = two;
        root.right = five;

        System.out.println(obj.closestValue(root, 3.714286));
    }
}
