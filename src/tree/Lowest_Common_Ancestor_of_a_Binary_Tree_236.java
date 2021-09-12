package tree;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia:

“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
*/
class Lowest_Common_Ancestor_of_a_Binary_Tree_236 {
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestorI(root.left, p, q);
        TreeNode right = lowestCommonAncestorI(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p && root == q) { // p and q are same.
            return root;
        }

        TreeNode x = lowestCommonAncestor(root.left, p, q);
        if (x != null && x != p && x != q) {
            return x;
        }

        TreeNode y = lowestCommonAncestor(root.right, p, q);
        if (y != null && y != p && y != q) {
            return y;
        }

        if (x != null && y != null) {
            return root;
        } else if (root == p || root == q) {
            return root;
        } else {
            return x == null ? y : x; /* return the non-null value */
        }
    }

    public TreeNode lowestCommonAncestorTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == q || root == p) return root;

        TreeNode x = lowestCommonAncestorTree(root.left, p, q);
        TreeNode y = lowestCommonAncestorTree(root.right, p, q);

        if (x != null && y != null) return root;

        if (x != null) return x;
        if (y != null) return y;

        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        
        while (!ancestors.contains(q))
            q = parent.get(q);
        
        return q;
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        Frame answer = new Frame();
        Stack<Frame> stack = new Stack<Frame>();

        stack.push(new Frame(root, answer));

        while (!stack.isEmpty()) {
            Frame top = stack.peek();

            Frame parent = top.parent;
            TreeNode node = top.node;
            
            if (node == null || node == p || node == q) {
                parent.subs.add(node);
                stack.pop();
            } else if (top == null || top.subs == null || top.subs.isEmpty()) {
                stack.push(new Frame(node.right, top));
                stack.push(new Frame(node.left, top));
            } else {
                TreeNode left = top.subs.get(0);
                TreeNode right = top.subs.get(1);
                parent.subs.add(left == null ? right : right == null ? left : node);
                stack.pop();
            }
        }

        return answer.subs.get(0);
    }

    Map<TreeNode, TreeNode> parent = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(null, root);

        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while(!ancestors.contains(q)) q = parent.get(q);
        return q;
    }

    private void dfs(TreeNode parentNode, TreeNode cur) {
        if (cur == null) return;
        parent.put(cur, parentNode);
        dfs(cur, cur.left);
        dfs(cur, cur.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode p = new TreeNode(4);
        TreeNode q = new TreeNode(3);
        new Lowest_Common_Ancestor_of_a_Binary_Tree_236().lowestCommonAncestorIterative(root, p, q);
    }
}

class Frame {
    TreeNode node;
    Frame parent;
    ArrayList<TreeNode> subs;

    Frame() {
        this.subs = new ArrayList<TreeNode>();
    }

    Frame(TreeNode node, Frame parent) {
        this.node = node;
        this.parent = parent;
        this.subs = new ArrayList<TreeNode>();
    }
}

















