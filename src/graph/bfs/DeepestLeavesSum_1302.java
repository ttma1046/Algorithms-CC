package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum_1302 {
    private int maxLevel = -1;
    private int sum = 0;
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) { return 0; }
        maxLevel = 0;
        sum = 0;
        calSum(root, 0);
        return sum;
    }

    private void calSum(TreeNode node, int level) {
        if (node == null) return;

        if (level > maxLevel) {
            sum = node.val;
            maxLevel = level;
        } else if (level == maxLevel) {
            sum += node.val;
        }

        calSum(node.left, level+1);
        calSum(node.right, level+1);
    }


    public int deepestLeavesSumII(TreeNode root) {
        int[] maxDepth = new int[1];
        int[] sumOfDeepestLeaves = new int[1];
        findDepth(root, maxDepth, sumOfDeepestLeaves, 1);
        return sumOfDeepestLeaves[0];
    }

    public void findDepth(TreeNode root, int[] maxDepth, int[] sumOfDeepestLeaves,
                          int currDepth) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            /* If depth of the current leaf is equal to existing maximum depth,
             add the value of this leaf to the existing sum of deepest leaves. */
            if (maxDepth[0] == currDepth) {
                sumOfDeepestLeaves[0] += root.val;
                return;
                /** If depth of the current leaf is less than the existing maximum depth,
                 dont change the existing sum of deepest leaves and return. */
            } else if (currDepth < maxDepth[0]) {
                return;
                /** If depth of the current leaf is greater than the existing maximum depth,
                 set max depth to current depth and also initialize the sum of deepest leaves
                 as the current node val */
            } else {
                sumOfDeepestLeaves[0] = root.val;
                maxDepth[0] = currDepth;
                return;
            }
        }

        findDepth(root.left, maxDepth, sumOfDeepestLeaves, currDepth + 1);
        findDepth(root.right, maxDepth, sumOfDeepestLeaves, currDepth + 1);
        return;
    }


    public int deepestLeavesSumBFS(TreeNode root) {
        if (root == null) return 0;

        int sum = 0, i;
        LinkedList<TreeNode> bfsQueue = new LinkedList<TreeNode>();

        bfsQueue.add(root);

        while(!bfsQueue.isEmpty()) {
            int currentSize = bfsQueue.size();
            sum = 0;
            for (i = currentSize - 1; i >= 0; --i) {
                TreeNode node = bfsQueue.poll();
                sum += node.val;

                if (node.left != null) bfsQueue.add(node.left);
                if (node.right != null) bfsQueue.add(node.right);
            }
        }

        return sum;
    }

    public int deepestLeavesSumIII(TreeNode root) {
        int res = 0, i;
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            for (i = q.size() - 1, res = 0; i >= 0; --i) {
                TreeNode node = q.poll();
                res += node.val;
                if (node.right != null) q.add(node.right);
                if (node.left  != null) q.add(node.left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(7);

        TreeNode two = new TreeNode(2);
        two.left = four;
        two.right = new TreeNode(5);

        TreeNode six = new TreeNode(6);
        six.right = new TreeNode(8);
        TreeNode three = new TreeNode(3);
        three.right = six;

        TreeNode one = new TreeNode(1);
        one.right = three;
        one.left = two;

        DeepestLeavesSum_1302 abc = new DeepestLeavesSum_1302();

        System.out.println(abc.deepestLeavesSum(one));
        System.out.println(abc.deepestLeavesSumBFS(one));

        // abc.traversePreOrder(one);
        // abc.traverseInOrder(one);
        // abc.traversePostOrder(one);

        /*
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(3);
        four.right = new TreeNode(5);

        TreeNode eight = new TreeNode(8);
        eight.left = new TreeNode(7);
        eight.right = new TreeNode(9);

        TreeNode six = new TreeNode(6);
        six.left = four;
        six.right = eight;

        abc.traversePostOrder(six);
        */

        // System.out.println(abc.deepestLeavesSumIII(one));
    }

    public void traversePreOrder(TreeNode node) {
        if (node != null) {
            System.out.println(" " + node.val);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(" " + node.val);
            traverseInOrder(node.right);
        }
    }

    public void traversePostOrder(TreeNode node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.println(" " + node.val);
        }
    }
}
