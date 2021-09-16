package tree;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
/*
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

Example 1:

Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.

Example 2:

Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.

Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
*/

/**
 * Definition for a binary tree node.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Count_Good_Nodes_in_Binary_Tree_1448 {
    /*
    Complexity Analysis

    Given N as the number of nodes in the tree,

    Time complexity: O(N)

    With DFS we visit every node exactly once and do a constant amount of work each time.

    Space complexity: O(N)

    Because DFS prioritizes depth, our call stack can be as large as the height HH of the tree. In the worst case scenario, H = N, if the tree only has one path.
    */
    int res = 0;
    public int goodNodes(TreeNode root) {

        dfs(root, Integer.MIN_VALUE);

        return res;
    }

    void dfs(TreeNode node, int max) {
        if (node == null) return;

        if (node.val >= max) {
            res++;
            max = node.val;
        }

        dfs(node.left, max);
        dfs(node.right, max);
    }

    /*
    Complexity Analysis

    Given N as the number of nodes in the tree,

    Time complexity: O(N)

    With DFS we visit every node exactly once and do a constant amount of work each time.

    Space complexity: O(N)

    In the worst case scenario, where every right child has 2 children and every left child has no children (or vice-versa), our stack will contain N/2 nodes at max depth.
    */
    public int goodNodesII(TreeNode root) {
        int numGoodNodes = 0;

        Stack<Pair> stack = new Stack<>();

        stack.push(new Pair(root, Integer.MIN_VALUE));

        while(stack.size() > 0) {
            Pair pair = stack.pop();

            TreeNode node = pair.node;
            int maxSoFar = pair.maxSoFar;


            if (node.val >= maxSoFar) {
                numGoodNodes++;
                maxSoFar = node.val;
            }

            if (node.left != null) stack.push(new Pair(node.left, maxSoFar));
            if (node.right != null) stack.push(new Pair(node.right, maxSoFar));
        }

        return numGoodNodes;
    }

    public int goodNodesIII(TreeNode root) {
        int numGoodNodes = 0;

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, Integer.MIN_VALUE));

        while(queue.size() > 0) {
            Pair curr = queue.poll();

            TreeNode node = curr.node;
            int maxSoFar = curr.maxSoFar;

            if (node.val >= maxSoFar) {
                numGoodNodes++;
                maxSoFar = node.val;
            }

            if (node.left != null) {
                queue.offer(new Pair(node.left, maxSoFar));
            }

            if (node.right != null) {
                queue.offer(new Pair(node.right, maxSoFar));
            }
        }

        return numGoodNodes;
    }
}

class Pair {
    TreeNode node;
    int maxSoFar;

    public Pair(TreeNode n, int m) {
        this.node = n;
        this.maxSoFar = m;
    }
}
