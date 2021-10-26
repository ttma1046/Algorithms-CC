package tree;
import java.util.Queue;
import java.util.LinkedList;
/*
Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:


Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.


Constraints:

The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
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
/*
*/
class Check_Completeness_of_a_Binary_Tree_958 {
    public boolean isCompleteTreeDFS(TreeNode root) {
        return dfs(root) >= 0;
    }

    /*
        0001
        0010

        0001
    */

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left), r = dfs(root.right);
        if ((l & (l + 1)) == 0 && l / 2 <= r && r <= l)
            return l + r + 1;
        if ((r & (r + 1)) == 0 && r <= l && l <= r * 2 + 1)
            return l + r + 1;
        return -1 ;
    }
    
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<TreeNode>();
        bfs.offer(root);

        while(bfs.peek() != null) {
            TreeNode node = bfs.poll();
            bfs.offer(node.left);
            bfs.offer(node.right);
        }

        while(bfs.size() > 0 && bfs.peek() == null) bfs.poll();

        return bfs.size() == 0;
    }

    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(true) {
            TreeNode node = queue.poll();

            if (node.left == null) {
                if (node.right != null) return false;
                break;
            }

            queue.offer(node.left);
            if (node.right == null) break;
            queue.offer(node.right);
        }

        while(queue.size() > 0) {
            TreeNode node = queue.poll();

            if (node.left != null || node.right != null) return false;
        }

        return true;
    }
    /*
    Time Complexity: O(N), where N is the number of nodes.
    Space Complexity: O(N)
    */

/*
     1
   2  3
  4 5
 6 7 
*/
    public boolean isCompleteTree(TreeNode root) {
        boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur == null) end = true;
            else {
                if(end) return false;
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return true;
    }
    /*
    Time Complexity: O(N), where N is the number of nodes.
    Space Complexity: O(N)
    */
    public static void main(String[] args) {
        Check_Completeness_of_a_Binary_Tree_958 obj = new Check_Completeness_of_a_Binary_Tree_958();
    }

    /*
                 1
           2          3
         4    5     6    7
        8 9 10 11 12 13
      15
    */
}