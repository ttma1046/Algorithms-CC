package tree;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.LinkedList;
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
class Two_Sum_BSTs_1214 {
    public boolean twoSumBSTsI(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = inHashset(root1, target, new HashSet<Integer>());

        return inCheck(root2, set);
    }

    public boolean inCheck(TreeNode node, Set<Integer> set) {
        if (node == null) return false;

        return inCheck(node.left, set) || set.contains(node.val) || inCheck(node.right, set);
    }

    private Set<Integer> inHashset(TreeNode node, int target, Set<Integer> set) {
        if (node == null) return set;
        inHashset(node.left, target, set);
        set.add(target - node.val);
        inHashset(node.right, target, set);
        return set;
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) {
            return false;
        }

        if (findTarget(root1, root2, target)) {
            return true;
        }

        return helper(root1.left, root2, target) || helper(root1.right, root2, target);
    }

    private boolean helper(TreeNode root, TreeNode root2, int target) {
        if (root == null) {
            return false;
        }

        if (findTarget(root, root2, target)) {
            return true;
        }

        return helper(root.left, root2, target) || helper(root.right, root2, target);
    }

    private boolean findTarget(TreeNode p, TreeNode root, int target) {
        while (root != null) {
            if (p.val + root.val == target) {
                return true;
            }

            if (p.val + root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Two_Sum_BSTs_1214 obj = new Two_Sum_BSTs_1214();

        TreeNode treeOneone = new TreeNode(1);
        TreeNode treeOnefour = new TreeNode(4);

        TreeNode treeOne = new TreeNode(2);
        treeOne.left = treeOneone;
        treeOne.right = treeOnefour;

        TreeNode treeTwoZero = new TreeNode(0);
        TreeNode treeTwoThree = new TreeNode(3);

        TreeNode treeTwo = new TreeNode(1);
        treeTwo.left = treeTwoZero;
        treeTwo.right = treeTwoThree;

        System.out.println(obj.twoSumBSTsI(treeOne, treeTwo, 5));
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root1 != null || !stack.isEmpty()) {
            while (root1 != null) {
                stack.push(root1);
                root1 = root1.left;
            }

            root1 = stack.pop();
            set.add(target - root1.val);
            root1 = root1.right;
        }

        while (root2 != null || !stack.isEmpty()) {
            while (root2 != null) {
                stack.push(root2);
                root2 = root2.left;
            }

            root2 = stack.pop();
            if (set.contains(root2.val)) return true;
            root2 = root2.right;
        }

        return false;
    }
}

/*
Time complexity: O(N1 + N2), where N1 and N2 are the numbers of nodes in the first and the second tree respectively.

Space complexity: O(2 × N1 + N2), N1 to keep the hashset and up to N1 + N2 for the recursive stacks.
*/