package tree;
/*
        8
       / \
     3    10
    / \    \
   1   6    14
      / \   /
     4  7  13
*/

public class Maximum_Difference_Between_Node_and_Ancestor_1026 {
	public int maxAncestorDiff(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return helper(root, root.val, root.val);
	}

	public int helper(TreeNode node, int curMax, int curMin) {
		// if encounter leaves, return the max-min along the path
		if (node == null) {
			return curMax - curMin;
		}
		// else, update max and min
		// and return the max of left and right subtrees
		curMax = Math.max(curMax, node.val);
		curMin = Math.min(curMin, node.val);
		int left = helper(node.left, curMax, curMin);
		int right = helper(node.right, curMax, curMin);
		return Math.max(left, right);
	}

	public static void main(String[] args) {
		TreeNode thirteen = new TreeNode(13);
		TreeNode fourteen = new TreeNode(14);
		fourteen.left = thirteen;

		TreeNode four = new TreeNode(4);
		TreeNode seven = new TreeNode(7);

		TreeNode six = new TreeNode(6);
		six.left = four;
		six.right = seven;

		TreeNode one = new TreeNode(1);
		TreeNode three = new TreeNode(3);
		three.left = one;
		three.right = six;

		TreeNode ten = new TreeNode(10);
		ten.right = fourteen;
		TreeNode root = new TreeNode(8);

		root.left = three;
		root.right = seven;

		System.out.println(new Maximum_Difference_Between_Node_and_Ancestor_1026().maxAncestorDiff(root));
	}
}
