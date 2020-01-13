package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageofLevelsinBinaryTree_637 {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }

        ArrayList<Double> resultArray = new ArrayList<Double>();

        Queue<TreeNode> myQueue = new LinkedList<TreeNode>();
        myQueue.add(root);

        double sum = 0;
        int i = 0;
        int length = 0;
        while (!myQueue.isEmpty()) {
            sum = 0;
            length = myQueue.size();

            for (i = 0; i < length; i++) {
                TreeNode node = myQueue.poll();
                sum += node.val;
                if (node.left != null) { myQueue.offer(node.left); }
                if (node.right != null) { myQueue.offer(node.right); }
            }

            resultArray.add(sum / length);
        }

        return resultArray;
    }

    public static void main (String[] args) {
        TreeNode twenty = new TreeNode(20);
        twenty.left = new TreeNode(15);
        twenty.right = new TreeNode(7);

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = twenty;

        List<Double> results = new AverageofLevelsinBinaryTree_637().averageOfLevels(root);

        for (double result: results) {
            System.out.println(result);
        }
    }
}
