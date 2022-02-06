package tree;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
/*
A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.

Implement the CBTInserter class:

CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete, and returns the value of the parent of the inserted TreeNode.
TreeNode get_root() Returns the root node of the tree.

Example 1:

Input
["CBTInserter", "insert", "insert", "get_root"]
[[[1, 2]], [3], [4], []]
Output
[null, 1, 2, [1, 2, 3, 4]]

Explanation
CBTInserter cBTInserter = new CBTInserter([1, 2]);
cBTInserter.insert(3);  // return 1
cBTInserter.insert(4);  // return 2
cBTInserter.get_root(); // return [1, 2, 3, 4]


Constraints:

The number of nodes in the tree will be in the range [1, 1000].
0 <= Node.val <= 5000
root is a complete binary tree.
0 <= val <= 5000
At most 104 calls will be made to insert and get_root.
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
class Complete_Binary_Tree_Inserter_919 {
    public static void main(String[] args) {
        TreeNode two = new TreeNode(2);
        two.left = new TreeNode(4);
        TreeNode one = new TreeNode(1);
        one.left = two;
        one.right = new TreeNode(3);


        CBTInserter cBTInserter = new CBTInserter(one);
        // cBTInserter.insert(3);  // return 1
        // cBTInserter.insert(4);  // return 2
        // cBTInserter.get_root(); // return [1, 2, 3, 4]
    }
}

class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;
    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null)
                deque.offerLast(node);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }

        /*
        while (deque.size() > 0)
            System.out.println(deque.pollFirst().val);
        // 2 3 4
        */

        while (deque.size() > 0)
            System.out.println(deque.pollLast().val);
        // 4 3 2

    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null)
            node.left = deque.peekLast();
        else {
            node.right = deque.peekLast();
            deque.pollFirst(); // this node has both left and right now, it's "completed", so remove it from the deque.
        }

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

class CBTInserter {
    private Queue<TreeNode> queue;
    private TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offer(root);

        while(queue.peek().left != null && queue.peek().right != null) {
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = queue.peek();
        TreeNode newNode = new TreeNode(v);

        if(node.left == null) {
            node.left = newNode;
        } else {
            node.right = newNode;
            queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return node.val;
    }

    public TreeNode get_root() {
        return this.root;
    }
}

class CBTInserter {
    private final TreeNode root;
    private final Queue<TreeNode> q;

    public CBTInserter(TreeNode root) {
        this.root = root;
        q = new LinkedList<>();
        q.offer(root);
    }

    public int insert(int v) {
        while (q.peek().right != null) {
            TreeNode n = q.poll();
            q.offer(n.left);
            q.offer(n.right);
        }
        TreeNode parent = q.peek(), n = new TreeNode(v);
        if (parent.left == null) {
            parent.left = n;
        } else {
            parent.right = n;
        }
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
