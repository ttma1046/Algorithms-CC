package tree;

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
	public int kthSmallest(TreeNode root, int k) {
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

    public int kthSmallestII(TreeNode root, int k) {
	    Stack<TreeNode> stack = new Stack<TreeNode>();

    	while (true) {
      		while (root != null) {
        		stack.push(root);
        		root = root.left;
      		}
      		root = stack.pop();
      		if (--k == 0) return root.val;
      		root = root.right;
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
    }	
}