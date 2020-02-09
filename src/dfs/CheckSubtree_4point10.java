class CheckSubtree_4point10 {
	public boolean stringSolution(TreeNode nodeOne, TreeNode nodeTwo) {
		StringBuilder stringOne = new StringBuilder();
		StringBuilder stringTwo = new StringBuilder();

		getOrderString(nodeOne, stringOne);
		getOrderString(nodeTwo, stringTwo);


		return stringOne.toString().indexOf(stringTwo.toString()) > -1;
	}

	private void getOrderString(TreeNode current, StringBuilder stringBuilder) {
		if (current == null) {
			stringBuilder.append("X");
			return;
		}

		stringBuilder.append(current.val.toString());
		getOrderString(current.left, stringBuilder);
		getOrderString(current.right, stringBuilder);
	}

	boolean solution(TreeNode nodeOne, TreeNode nodeTwo) {
		if (nodeTwo == null) {
			return true;
		}

		return checkSubtree(nodeOne, nodeTwo);
	}

	private boolean checkSubtree(TreeNode nodeOne, TreeNode nodeTwo) {
		if (nodeOne == null) {
			return false;
		}

		if (nodeOne.val == nodeTwo.val && matchSubTree(nodeOne, nodeTwo)) {
			return true;
		}

		return checkSubtree(nodeOne.left, nodeTwo) || checkSubtree(nodeOne.right, nodeTwo);
	}

	private boolean matchSubTree(TreeNode nodeOne, TreeNode nodeTwo) {
		if (nodeOne == null && nodeTwo == null) {
			return true;
		}

		if (nodeOne == null || nodeTwo == null) {
			return false;
		}

		if (nodeOne.val != nodeTwo.val) {
			return false;
		}

		return matchSubTree(nodeOne.left, nodeTwo.left) && matchSubTree(nodeOne.right, nodeTwo.right);
	}

}