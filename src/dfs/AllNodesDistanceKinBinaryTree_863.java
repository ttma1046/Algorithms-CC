package dfs;

import java.util.*;

/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 */

public class AllNodesDistanceKinBinaryTree_863 {
    Map<TreeNode, Integer> map = new HashMap<>();
    public List<Integer> distanceKIII(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        find(root, target);

        for (TreeNode node: map.keySet()) {
            System.out.println(node.val + ":" + map.get(node));
        }

        search(root, 0, K, res);

        return res;
    }

    private void find(TreeNode current, TreeNode target) {
        if (current == null) {
            return;
        }

        if (current.val == target.val) {
            map.put(current, 0);
            return;
        }

        find(current.left, target);
        if (map.containsKey(current.left)) {
            map.put(current, map.get(current.left) + 1);
            return;
        }

        find(current.right, target);
        if (map.containsKey(current.right)) {
            map.put(current, map.get(current.right) + 1);
        }
    }

    public void search(TreeNode current, int dis, int K, List<Integer> res) {
        if (current == null) {
            return;
        }

        if (map.containsKey(current)) {
            dis = map.get(current);
        }

        if (dis == K) {
            res.add(current.val);
        }

        search(current.left, dis + 1, K, res);
        search(current.right, dis + 1, K, res);
    }
    /*
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<Integer>();
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        find(map, root, target);

        dfs(map, root, 0, K, res);
        return res;
    }

    // find target node first and store the distance in that path that we could use it later directly
    private void find(Map<TreeNode, Integer> map, TreeNode current, TreeNode target) {
        if (current == null) {
            return;
        }

        if (current.val == target.val) {
            map.put(current, 0);
            return;
        }

        find(map, current.left, target);
        if (map.containsKey(current.left)) {
            map.put(current, map.get(current.left) + 1);
        }

        find(map, current.right, target);
        if (map.containsKey(current.right)) {
            map.put(current, map.get(current.right) + 1);
        }
    }

    private void dfs(Map<TreeNode, Integer> map, TreeNode current, int distance, int K, List<Integer> res) {
        if (current == null) {
            return;
        }

        if (map.containsKey(current)) {
            distance = map.get(current);
        }

        if (distance == K) {
            res.add(current.val);
        }

        dfs(map, current.left, distance + 1, K, res);
        dfs(map, current.right, distance + 1, K, res);
    }
    */

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

        List<Integer> result = new AllNodesDistanceKinBinaryTree_863().distanceKIII(root, new TreeNode(5), 2);
        // List<Integer> result = new AllNodesDistanceKinBinaryTree_863().distanceK(root, new TreeNode(5), 2);
        for (int number: result) {
            System.out.println(number);
        }

        // System.out.println(new AllNodesDistanceKinBinaryTree_863().search(root, new TreeNode(5), 0));
        // new AllNodesDistanceKinBinaryTree_863().preorderTravsel(root);
    }

    /*
    public int search(TreeNode current, TreeNode target, int distance) {
        if (current == null) {
            return 0;
        }

        if (current.val == target.val) {
            return distance;
        }

        return search(current.left, target, distance + 1) + search(current.right, target, distance + 1);
    }

     private void preorderTravsel(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            preorderTravsel(root.left);
            preorderTravsel(root.right);
        }
    }
    */
}
