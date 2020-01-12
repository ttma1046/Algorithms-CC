package bfs;

import java.util.List;

public class AllNodesDistanceKinBinaryTree_863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return null;
        }


        return dfs(root, target, K);




        return null;
    }





    public static void main(String[] args) {
        TreeNode two = new TreeNode(2);
        two.left = new TreeNode(7);
        two.right = new TreeNode(4);

        TreeNode five = new TreeNode(5);
        five.left = new TreeNode(6);
        five.right = two;

        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(0);
        one.right = new TreeNode(8);

        TreeNode root = new TreeNode(3);
        root.left = five;
        root.right = one;

        new AllNodesDistanceKinBinaryTree_863().distanceK(root, new TreeNode(5), 2);
    }
}
