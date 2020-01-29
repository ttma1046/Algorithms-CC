package dfs;

import java.util.LinkedList;
import java.util.Queue;
 
public class Symmetric_Tree_101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        
        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode nodeLeft, TreeNode nodeRight) {
        if(nodeLeft == null && nodeRight == null) return true;
        if(nodeLeft == null || nodeRight == null) return false;

        return nodeLeft.val == nodeRight.val 
            && isMirror(nodeLeft.left, nodeRight.right) 
            && isMirror(nodeLeft.right, nodeRight.left);
    }

    public boolean isSymmetricII(TreeNode root) {
        if (root == null) return false;

        Queue<TreeNode> myQueue = new LinkedList<TreeNode>();

        myQueue.add(root.left);
        myQueue.add(root.right);

        while(!myQueue.isEmpty()) {
            TreeNode treeNodeOne = myQueue.poll();
            TreeNode treeNodeTwo = myQueue.poll();

            if (treeNodeTwo == null && treeNodeOne == null) continue;
            if (treeNodeTwo == null || treeNodeOne == null) return false;
            if (treeNodeOne.val != treeNodeTwo.val) return false;

            myQueue.add(treeNodeOne.left);
            myQueue.add(treeNodeTwo.right);
            myQueue.add(treeNodeOne.right);
            myQueue.add(treeNodeTwo.left);
        }

        return true;
    }
}