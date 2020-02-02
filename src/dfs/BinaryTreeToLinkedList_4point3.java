package dfs;
/*
 * Given a binary tree, design an algorithm which creates a linked list of all
 * the nodes at each depth (e.g., if you have a tree with depth D, you 'll have
 * D linked lists).
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BinaryTreeToLinkedList_4point3 {
    public ArrayList<LinkedList<TreeNode>> solution(TreeNode root) {
        if (root == null) {
            return null;
        }

        ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
        LinkedList<TreeNode> myQueue = new LinkedList<TreeNode>();

        myQueue.add(root);

        while (!myQueue.isEmpty()) {

            result.add(myQueue);

            LinkedList<TreeNode> parents = myQueue;

            myQueue = new LinkedList<TreeNode>();

            for (TreeNode parent : parents) {
                if (parent.left != null) {
                    myQueue.add(parent.left);
                }

                if (parent.right != null) {
                    myQueue.add(parent.right);
                }
            }
        }

        return result;
    }

    public ArrayList<LinkedList<TreeNode>> solutionII(TreeNode root) {

        if (root == null) {
            return null;
        }

        ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

        LinkedList<TreeNode> temp = new LinkedList<TreeNode>();
        temp.add(root);
        ProcessTheNode(temp, result);

        return result;
    }

    private void ProcessTheNode(LinkedList<TreeNode> currents, List<LinkedList<TreeNode>> result) {
        if (currents.size() > 0) {

            result.add(currents);

            LinkedList<TreeNode> temp = new LinkedList<TreeNode>();

            for (TreeNode current : currents) {
                if (current.left != null) {
                    temp.add(current.left);
                }

                if (current.right != null) {
                    temp.add(current.right);
                }
            }

            ProcessTheNode(temp, result);
        }
    }

    void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> result, int level) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> list = null;

        if (result.size() == level) {
            list = new LinkedList<TreeNode>();

            result.add(list);
        } else {
            list = result.get(level);
        }

        list.add(root);

        createLevelLinkedList(root.left, result, level + 1);
        createLevelLinkedList(root.right, result, level + 1);
    }

    ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
        createLevelLinkedList(root, result, 0);

        return result;
    }

    public static void main(String[] args) {

        final TreeNode eleven = new TreeNode(11);
        eleven.left = new TreeNode(7);
        eleven.right = new TreeNode(2);

        final TreeNode four = new TreeNode(4);
        four.left = eleven;

        final TreeNode anotherFour = new TreeNode(4);
        anotherFour.right = new TreeNode(1);

        final TreeNode eight = new TreeNode(8);
        eight.left = new TreeNode(13);
        eight.right = anotherFour;

        final TreeNode root = new TreeNode(5);
        root.left = four;
        root.right = eight;

        ArrayList<LinkedList<TreeNode>> result = new BinaryTreeToLinkedList_4point3().solution(root);

        for (LinkedList<TreeNode> x : result) {
            for (TreeNode node : x) {
                System.out.println(node.val);
            }
        }

    }

}