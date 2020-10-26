package dfs;

/*
Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
*/
class SortedArrayToBinarySearchTree_4point2 {
    public TreeNode Solution(int[] array) {
        if (array == null) {
            return null;
        }

        return GenerateTree(0, array.length - 1, array);

    }

    private TreeNode GenerateTree(int start, int end, int array[]) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode current = new TreeNode(array[mid]);

        current.left = GenerateTree(start, mid - 1, array);
        current.right = GenerateTree(mid + 1, end, array);

        return current;
    }
}