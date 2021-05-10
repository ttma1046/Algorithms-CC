package tree;

/*
Given the roots of two binary search trees, root1 and root2,

return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

Example 1:

Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false

Constraints:

The number of nodes in each tree is in the range [1, 5000].
-109 <= Node.val, target <= 109
*/

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
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = inHashset(root1, target, new HashSet<Integer>());

        return check(root2, set);
    }

    public boolean check(TreeNode node, Set<Integer> set) {
        if (node == null) return false;

        return check(node.left, set) || set.contains(node.val) || check(node.right, set);
    }

    private Set<Integer> inHashset(TreeNode node, int target, Set<Integer> set) {
        if (node == null) return false;
        inHashset(node.left, target, set);
        set.add(target - r.val);
        inHashset(node.right, target, set);
        return s;
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        ArrayDeque<TreeNode> stack = new ArrayDeque();
        Set<Integer> s = new HashSet();
        // traverse the first tree
        // and store node complements (target - val) in hashset
        while (!stack.isEmpty() || root1 != null) {
            while (root1 != null) {
                stack.push(root1);
                root1 = root1.left;
            }
            root1 = stack.pop();
            s.add(target - root1.val);
            root1 = root1.right;
        }

        // traverse the second tree
        // and check if one of the values exists in hashset
        while (!stack.isEmpty() || root2 != null) {
            while (root2 != null) {
                stack.push(root2);
                root2 = root2.left;
            }
            root2 = stack.pop();
            if (s.contains(root2.val)) {
                return true;
            }
            root2 = root2.right;
        }

        return false;
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Stack<TreeNode> stack = new LinkedList<TreeNode>();

        Set<TreeNode> set = new HashSet<TreeNode>();

        while (!stack.isEmpty() || root1 != null) {
            while(root1 != null) {
                stack.push(root1);
                root1 = root1.left;
            }

            root1 = stack.pop();
            set.add(target - root1.val);

            root1 = root1.right;
        }

        while (!stack.isEmpty() || root2 != null) {
            while (root2 != null) {
                stack.push(root2);
                root2 = root2.left;
            }

            root2 = root2.pop();

            if (set.contains(root2.val)) {
                return true;
            }

            root2 = root2.right;
        }

        return false;
    }
}

/*
Time complexity: O(N1 + N2), where N1 and N2 are the numbers of nodes in the first and the second tree respectively.

Space complexity: O(2 × N1 + N2), N1 to keep the hashset and up to N1 + N2 for the recursive stacks.
*/