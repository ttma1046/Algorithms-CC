package tree;
import java.util.Set;
import java.util.HashSet;

/*
Given a binary tree with the following rules:

root.val == 0
If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

You need to first recover the binary tree and then implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
bool find(int target) Return if the target value exists in the recovered binary tree.


Example 1:



Input
["FindElements","find","find"]
[[[-1,null,-1]],[1],[2]]
Output
[null,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1]);
findElements.find(1); // return False
findElements.find(2); // return True
Example 2:



Input
["FindElements","find","find","find"]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
Output
[null,true,true,false]
Explanation
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False
Example 3:



Input
["FindElements","find","find","find","find"]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
Output
[null,true,false,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True


Constraints:

TreeNode.val == -1
The height of the binary tree is less than or equal to 20
The total number of nodes is between [1, 10^4]
Total calls of find() is between [1, 10^4]
0 <= target <= 10^6
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
class Find_Elements_in_a_Contaminated_Binary_Tree_1261 {
	/*
	// sln 1
	TreeNode mainRoot;
	public FindElements(TreeNode root) {
		root.val = 0;
		this.mainRoot = root;
	}
	public boolean find(int target) {
		// target = (target+1)<<1;
		String br = Integer.toBinaryString(target + 1);
		int i = 1;
		TreeNode node = this.mainRoot;
		while (i < br.length()) {
			if (node == null) return false;
			if (br.charAt(i) == '0') node = node.left;
			else node = node.right;
			i++;
		}
		return node != null;
	}

	// sln 2
	TreeNode mRoot;
	HashMap<Integer, Boolean> mMap = new HashMap();
	public FindElements(TreeNode root) {
		mRoot = root;
		recover(root, 0);
	}

	private void recover(TreeNode node, int realValue) {
		if (node != null) {
			node.val = realValue;
			mMap.put(realValue, true); //maps the search value to all true
			recover(node.left, 2 * realValue + 1);
			recover(node.right, 2 * realValue + 2);
		}
	}

	public boolean find(int target) {
		return mMap.get(target) == null ? false : true;
		// if target exists in mMap, return true. otherwise return null
	}

	// sln 3 dfs
	HashSet<Integer> set = new HashSet<>();

	public FindElements(TreeNode root) {
		if (root != null) {
			root.val = 0;
			dfs(root);
		}
	}

	private void dfs(TreeNode node) {
		set.add(node.val);
		if (node.right != null) {
			node.right.val = node.val * 2 + 2;
			dfs(node.right);
		}

		if (node.left != null) {
			node.left.val = node.val * 2 + 1;
			dfs(node.left);
		}
	}

	public boolean find(int target) {
		return set.contains(target);
	}

	// sln 4 bfs
	private Set<Integer> seen = new HashSet<>();

	public FindElements(TreeNode root) {
		if (root != null) {
			root.val = 0;
			seen.add(root.val);
			bfs(root);
		}
	}

	public boolean find(int target) {
		return seen.contains(target);
	}

	private void bfs(TreeNode node) {
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(node);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur.left != null) {
				cur.left.val = 2 * cur.val + 1;
				q.offer(cur.left);
				seen.add(cur.left.val);
			}
			if (cur.right != null) {
				cur.right.val = 2 * cur.val + 2;
				q.offer(cur.right);
				seen.add(cur.right.val);
			}
		}
	}
	*/

	// my sln
	Set<Integer> set = new HashSet<Integer>();

	public Find_Elements_in_a_Contaminated_Binary_Tree_1261(TreeNode root) {
		if (root != null) {
			root.val = 0;
			bfs(root, 0);
		}
	}

	private void bfs(TreeNode node, int val) {
		set.add(val);
		if (node.left != null) bfs(node.left, val * 2 + 1);
		if (node.right != null) bfs(node.right, val * 2 + 2);
	}

	public boolean find(int target) {
		return set.contains(target);
	}

	public static void main(String[] args) {
		TreeNode one = new TreeNode(-1);

		TreeNode root = new TreeNode(-1);
		root.right = one;

		Find_Elements_in_a_Contaminated_Binary_Tree_1261 fe = new Find_Elements_in_a_Contaminated_Binary_Tree_1261(root);

		System.out.println(fe.find(1));

		System.out.println(fe.find(2));
	}
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */