class PathswithSum_BinaryTree_4point12 {
	boolean pathCountPathswithSum(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}

		int pathsFromNode = pathcountPathswithSumFromNode(root, targetSum, 0);

		int pathsOnLeft = pathCountPathswithSum(root.left, targetSum);
		int pathsOnRight = pathCountPathswithSum(root.right, targetSum);

		return pathsFromNode + pathsOnRight + pathsOnLeft;
	}

	int pathcountPathswithSumFromNode(TreeNode current, int targetSum, int currentSum) {
		if (current == null) {
			return 0;
		}

		int paths = 0;

		currentSum += current.val;

		if (targetSum == currentSum) {
			paths++;
		}

		paths += pathcountPathswithSumFromNode(current.left, targetSum, currentSum);
		paths += pathcountPathswithSumFromNode(current.right, targetSum, currentSum);

		return paths;
	}
}