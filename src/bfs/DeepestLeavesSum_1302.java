package bfs;

import com.sun.source.tree.Tree;

public class DeepestLeavesSum_1302 {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int level = 0;
        int result = 0;

        return checkNode(root, level, result, 0);
    }

    public int checkNode(TreeNode node, int currentLevel, int deepestLevel, int result) {
        if (node == null) {
            return result;
        }
        currentLevel++;
        if (node.left != null || node.right != null) {
            if (currentLevel > deepestLevel) {
                deepestLevel = currentLevel;
            }

            checkNode(node.left, currentLevel, deepestLevel, result);
            checkNode(node.right, currentLevel, deepestLevel, result);
        } else {
            if (currentLevel == deepestLevel) {
                result += node.val;
            } else if (currentLevel > deepestLevel) {
                deepestLevel = currentLevel;
                result = node.val;
            }
        }

        return result;
    }

    public int deepestLeavesSumII(TreeNode root) {
        int[] maxDepth = new int[1];
        int[] sumOfDeepestLeaves = new int[1];
        findDepth(root, maxDepth, sumOfDeepestLeaves, 1);
        return sumOfDeepestLeaves[0];
    }

    public void findDepth(TreeNode root, int[] maxDepth, int[] sumOfDeepestLeaves,
                          int currDepth) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            /** If depth of the current leaf is equal to existing maximum depth,
             add the value of this leaf to the existing sum of deepest leaves. */
            if(maxDepth[0] == currDepth) {
                sumOfDeepestLeaves[0] += root.val;
                return;
                /** If depth of the current leaf is less than the existing maximum depth,
                 dont change the existing sum of deepest leaves and return. */
            } else if(currDepth < maxDepth[0]) {
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

        findDepth(root.left, maxDepth, sumOfDeepestLeaves, currDepth+1);
        findDepth(root.right, maxDepth, sumOfDeepestLeaves, currDepth+1);
        return;
    }

    public int deepestLeavesSumIII(TreeNode root) {
        int maxDepth = 0;
        int sumOfDeepestLeaves = 0;
        findDepth(root, maxDepth, sumOfDeepestLeaves, 1);
        return sumOfDeepestLeaves;
    }

    public void findDepth(TreeNode root, int maxDepth, int sumOfDeepestLeaves,
                          int currDepth) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            /** If depth of the current leaf is equal to existing maximum depth,
             add the value of this leaf to the existing sum of deepest leaves. */
            if(maxDepth == currDepth) {
                sumOfDeepestLeaves += root.val;
                return;
                /** If depth of the current leaf is less than the existing maximum depth,
                 dont change the existing sum of deepest leaves and return. */
            } else if(currDepth < maxDepth) {
                return;
                /** If depth of the current leaf is greater than the existing maximum depth,
                 set max depth to current depth and also initialize the sum of deepest leaves
                 as the current node val */
            } else {
                sumOfDeepestLeaves = root.val;
                maxDepth = currDepth;
                return;
            }
        }

        findDepth(root.left, maxDepth, sumOfDeepestLeaves, currDepth+1);
        findDepth(root.right, maxDepth, sumOfDeepestLeaves, currDepth+1);
        return;
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
        abc.traversePreOrder(one);
        abc.traverseInOrder(one);
        abc.traversePostOrder(one);

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
