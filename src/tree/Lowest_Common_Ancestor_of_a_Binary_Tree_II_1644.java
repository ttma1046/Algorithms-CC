package tree;

/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.

Constraints:

The number of nodes in the tree is in the range [1, 104].
-109 <= Node.val <= 109
All Node.val are unique.
p != q

Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
*/

class Lowest_Common_Ancestor_of_a_Binary_Tree_II {
	/*
	       3
	  5        1
	6   2     0 8
	   7 4
	*/

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

