package bfs;

class BfsTraverseTree {
	public List<List<Integer>> bfsTree(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null) return result;

		Queue<TreeNode> queue = new LinkedList<>();

		queue.offer(root);

		while (!queue.isEmpty()) {
			int length = queue.size();

			List<Integer> temp = new ArrayList<>();

			for (int i = 0; i < length; ++i) {
				TreeNode curr = queue.poll();

				temp.add(curr.val);

				if (curr.left != null) queue.offer(curr.left);
				if (curr.right != null) queue.offer(curr.right);
			}

			result.add(temp);
		}

		return result;
	}
}