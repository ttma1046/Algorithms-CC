package tree;


class Lowest_Common_Ancestor_of_a_Binary_Tree_II {
	boolean pFound = false;
	boolean qFound = false;

	public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode LCA = LCA(root, p, q);
		return pFound && qFound ? LCA : null;
	}

	public TreeNode LCA(TreeNode current, TreeNode p, TreeNode q) {
		if (current == null) return current;

		TreeNode left = LCA(current.left, p, q);
		TreeNode right = LCA(current.right, p, q);

		if (current == p) {
			pFound = true;
			return current;
		}
		if (current == q) {
			qFound = true;
			return current;
		}

		return left == null ? right : right == null ? left : current;
	}

	public static void main(String[] args) {
		TreeNode four = new TreeNode(4);
		TreeNode seven = new TreeNode(7);

		TreeNode two = new TreeNode(2);
		two.left = seven;
		two.right = four;

		TreeNode six = new TreeNode(6);
		TreeNode five = new TreeNode(5);
		five.left = six;
		five.right = two;

		TreeNode zero = new TreeNode(0);
		TreeNode eight = new TreeNode(8);

		TreeNode one = new TreeNode(1);
		one.left = zero;
		one.right = eight;

		TreeNode three = new TreeNode(3);
		three.left = five;
		three.right = one;


		TreeNode newFive = new TreeNode(5);
		TreeNode newFour = new TreeNode(4);

		new Lowest_Common_Ancestor_of_a_Binary_Tree_II().lowestCommonAncestor(three, newFive, newFour);
	}


	private int count = 0;

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode node = dfs(root, p, q);

		System.out.println(node.val);

		return count == 2 ? node : null;
	}

	private TreeNode dfs(TreeNode current, TreeNode p, TreeNode q) {
		if (current == null) {
			return current;
		}

		TreeNode left = dfs(current.left, p, q);
		TreeNode right = dfs(current.right, p, q);

		if (current.val == p.val || current.val == q.val) {
			count++;

			return current;
		}

		return left == null ? right : right == null ? left : current;
	}
}

/*

       3
  5        1
6   2     0 8
   7 4
*/