package tree;
import java.util.Deque;
import java.util.ArrayDeque'
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Maximum_Binary_Tree_654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        return recursive(nums, 0, nums.length - 1);
    }

    private TreeNode recursive(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int maxValue = Integer.MIN_VALUE;
        int index = left;

        for (int i = left; i <= right; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }


        TreeNode node = new TreeNode(maxValue);

        node.left = recursive(nums, left, index - 1);
        node.right = recursive(nums, index + 1, right);

        return node;
    }

    public TreeNode constructMaximumBinaryTreeII(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i])
                curr.left = stack.pop();

            if(!stack.isEmpty())
                stack.peek().right = curr;

            stack.push(curr);
        }

        return stack.isEmpty() ? null : stack.removeLast();
    }

    public static void main(String[] args) {
        Maximum_Binary_Tree_654 obj = new Maximum_Binary_Tree_654();
    }

    /*
    Time complexity : O(n^2).
    The function construct is called n times.
    At each level of the recursive tree, we traverse over all the n elements to find the maximum element.
    In the average case, there will be a \log nlogn levels leading to a complexity of O(nlogn).
    In the worst case, the depth of the recursive tree can grow upto n, which happens in the case of a sorted numsnums array, giving a complexity of O(n^2).

    Space complexity : O(n). The size of the setset can grow upto n in the worst case.
    In the average case, the size will be logn for n elements in numsnums, giving an average case complexity of O(logn)
    */
}