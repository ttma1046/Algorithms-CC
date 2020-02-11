package dfs;
/*
  Design an algorithm and write code to Find the first common ancestor oF two nodes in a binary tree.
  Avoid storing additonal nodes in a data structure. NOTE: This is not necessarily a binary search tree.
  */

class FirstCommonAncestor_4point8 {
	// node with links to parents
	TreeNode findCommonAncestor(TreeNode p, TreeNode q) {
		if (p == null || q == null) {
			return null;
		}

		int difference = findDepth(p) - findDepth(q);
		TreeNode shallower = difference > 0 ? q : p;
		TreeNode deeper = difference > 0 ? p : q;

		int plusDifference = Math.abs(difference);

		while (plusDifference > 0 && deeper != null) {
			deeper = deeper.parent;
			plusDifference--;
		}

		while (shallower != null && deeper != null && shallower != deeper) {
			shallower = shallower.parent;
			deeper = deeper.parent;
		}

		return shallower == null || deeper == null ? null : shallower;
	}

	int findDepth(TreeNode current) {
		int depth = 0;
		while (current != null) {
			current = current.parent;
			depth++;
		}

		return depth;
	}

	// With Links to Parents (Better Worst-Cast Runtime)
	// Trace p's path upwards and check i any o the nodes cover q.
	// The first node that covers q (we already know that every node on this path
	// will cover p) must be the irst common ancestor.

	TreeNode findCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null) {
			return null;
		}

		if (!coverSubTree(root, p) || !coverSubTree(root, q)) {
			return null;
		}

		if (coverSubTree(p, q)) {
			return p;
		}

		if (coverSubTree(q, p)) {
			return q;
		}

		/*
		 * while(p != null) { TreeNode sibling = getSibling(p); if (cover(sibling, q)) {
		 * return p.parent; } p = p.parent; }
		 * 
		 * return null;
		 */

		TreeNode parent = p.parent;
		TreeNode sibling = getSibling(p);

		while (!coverSubTree(sibling, q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}

		return parent;
	}

	TreeNode getSibling(TreeNode current) {
		if (current == null || current.parent == null) {
			return null;
		}

		return current == current.parent.left ? current.parent.right : current.parent.left;
	}

	boolean coverSubTree(TreeNode current, TreeNode dest) {
		if (current == null) {
			return false;
		}

		if (current.val == dest.val) {
			return true;
		}

		return coverSubTree(current.left, dest) || coverSubTree(current.right, dest);
	}

	// Without LInks to Parents
	TreeNode findCommonAncestorIII(TreeNode root, TreeNode p, TreeNode q) {
		/* error check - one node is not in the tree. */
		if (!coverSubTree(root, p) || !coverSubTree(root, q)) {
			return null;
		}

		return ancestorHelper(root, p, q);
	}

	TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		boolean pIsOnLeft = covers(root.left, p);
		boolean qIsOnLeft = covers(root.left, q);

		if (pIsOnLeft != qIsOnLeft) { // Nodes are on different side
			return root;
		}

		TreeNode childSide = pIsOnLeft ? root.left : root.right;

		return ancestorHelper(childSide, p, q);
	}

	private boolean covers(TreeNode root, TreeNode p) {
		if (root == null) {
			return false;
		}

		if (root.val == p.val) {
			return true;
		}

		return covers(root.left, p) || covers(root.right, p);
	}

	TreeNode findCommonAncestorBAD(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		if (root == p && root == q) {
			return root;
		}

		TreeNode x = findCommonAncestorBAD(root.left, p, q);
		if (x != null && x != p && x != q) {
			return x;
		}

		TreeNode y = findCommonAncestorBAD(root.right, p, q);
		if (y != null && y != p && y != q) {
			return y;
		}

		if (x != null && y != null) {
			return root;
		} else if (root == p || root == q) {
			return root;
		} else {
			return x == null ? y : x; /* return the non-null value */
		}
	}

	public static class Result {
		public TreeNode node;
		public boolean isAncestor;

		public Result(TreeNode n, boolean isAnc) {
			node = n;
			isAncestor = isAnc;
		}
	}

	public static Result commonAncestorHelperIV(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return new Result(null, false);
		}
		if (root == p && root == q) {
			return new Result(root, true);
		}

		Result rx = commonAncestorHelperIV(root.left, p, q);
		if (rx.isAncestor) { // Found common ancestor
			return rx;
		}

		Result ry = commonAncestorHelperIV(root.right, p, q);
		if (ry.isAncestor) { // Found common ancestor
			return ry;
		}

		if (rx.node != null && ry.node != null) {
			return new Result(root, true); // This is the common ancestor
		} else if (root == p || root == q) {
			/*
			 * If we�re currently at p or q, and we also found one of those nodes in a
			 * subtree, then this is truly an ancestor and the flag should be true.
			 */
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		} else {
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	}

	public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		Result r = commonAncestorHelperIV(root, p, q);
		if (r.isAncestor) {
			return r.node;
		}
		return null;
	}

	/*
	 * public static void main(String[] args) { int[] array = { 1, 2, 3, 4, 5, 6, 7,
	 * 8, 9, 10 }; TreeNode root = TreeNode.createMinimalBST(array); TreeNode n3 =
	 * root.find(10); TreeNode n7 = root.find(6); TreeNode ancestor =
	 * commonAncestor(root, n3, n7); if (ancestor != null) {
	 * System.out.println(ancestor.data); } else { System.out.println("null"); } }
	 */
}