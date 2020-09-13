package tree;

/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:

What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class KthSmallestElementinaBST_230 {
    public int kthSmallestI(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        ArrayList<Integer> arrayList = InOrderlist(root, new ArrayList<Integer>());

        return arrayList.get(k - 1);
    }

    private ArrayList<Integer> InOrderlist(TreeNode root, ArrayList<Integer> arrayList) {
        if (root == null) return arrayList;

        InOrderlist(root.left, arrayList);

        arrayList.add(root.val);

        InOrderlist(root.right, arrayList);

        return arrayList;
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> myStack = new Stack<TreeNode>();

        TreeNode curr = root;

        while (true) {
            while (curr != null) {
                myStack.push(curr);
                curr = curr.left;
            }

            curr = myStack.pop();
            if (--k == 0) {
                return curr.val;
            }

            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        /*
        four.left = new TreeNode(7);

        two.left = four;
        two.right = new TreeNode(5);

        TreeNode six = new TreeNode(6);
        six.right = new TreeNode(8);
        three.right = six;

        one.right = three;
        */
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);

        two.left = one;
        three.left = two;
        three.right = four;

        five.left = three;
        five.right = six;

        System.out.println(new KthSmallestElementinaBST_230().kthSmallestII(five, 3));
        System.out.println(new KthSmallestElementinaBST_230().kthSmallestI(five, 3));

    }
}