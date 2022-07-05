package tree;
import java.util.ArrayDeque;
import java.util.Deque;

/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 212 - 1].
-1000 <= Node.val <= 1000

Follow-up:

You may only use constant extra space.
The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
*/
/*
// Definition for a Node.
*/
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
/*
*/

class Populating_Next_Right_Pointers_in_Each_Node_116 {
    public Node connectIII(Node root) {
        if (root == null)
            return null;

        Node curr = root,
             leftMost = null,
             prev = null;

        while(curr != null) {
            leftMost = null;
            prev = null;
            while(curr != null) {
                if (curr.left != null) {
                    if (prev != null)
                        prev.next = curr.left;
                    else
                        leftMost = curr.left;

                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (prev != null)
                        prev.next = curr.right;
                    else
                        leftMost = curr.right;

                    prev = curr.right;
                }

                curr = curr.next;
            }

            curr = leftMost;
        }

        return root;
    }

    // 主函数
    Node connectII(Node root) {
        if (root == null) return null;
        // 遍历「三叉树」，连接相邻节点
        traverse(root.left, root.right);
        return root;
    }

    // 三叉树遍历框架
    void traverse(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序位置 ****/
        // 将传入的两个节点穿起来
        node1.next = node2;

        // 连接相同父节点的两个子节点
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        traverse(node1.right, node2.left);
    }

    public Node connect(Node root) {
        if (root == null)
            return null;

        Deque<Node> queue = new ArrayDeque<>();

        queue.offer(root);

        while(queue.size() > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (i < size - 1)
                    node.next = queue.peek();

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Populating_Next_Right_Pointers_in_Each_Node_116 obj = new Populating_Next_Right_Pointers_in_Each_Node_116();
    }
}