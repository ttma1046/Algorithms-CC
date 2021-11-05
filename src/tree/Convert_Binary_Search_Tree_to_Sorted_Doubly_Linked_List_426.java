package tree;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

class LinkedListNode {
    public int val;
    public LinkedListNode next;
    public LinkedListNode prev;

    public LinkedListNode() {}

    public LinkedListNode(int _val) {
        val = _val;
    }
};

/*
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.


Example 1:

Input: root = [4,2,5,1,3]

Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 2:

Input: root = [2,1,3]
Output: [1,2,3]

Example 3:

Input: root = []
Output: []
Explanation: Input is an empty tree. Output is also an empty Linked List.

Example 4:

Input: root = [1]
Output: [1]

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
All the values of the tree are unique.
*/

class Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List_426 {
    public static void main(String[] args) {
        Node one = new Node(1);
        Node three = new Node(3);
        Node five = new Node(5);
        Node two = new Node(2, one, three);
        Node four = new Node(4, two, five);

        Node result = new Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List_426().treeToDoublyList(four);
    }


    // the smallest (first) and the largest (last) nodes
    /*  Node first = null;
      Node last = null;

      public void helper(Node node) {
        if (node != null) {
          // left
          helper(node.left);
          // node
          if (last != null) {
            // link the previous node (last)
            // with the current one (node)
            last.right = node;
            node.left = last;
          } else {
            // keep the smallest node
            // to close DLL later on
            first = node;
          }
          last = node;
          // right
          helper(node.right);
        }
      }

      public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
      }*/

    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            helper(node.left);

            if (last != null) {
                last.right = node;
                node.left = last;
            } else {
                first = node;
            }

            last = node;

            helper(node.right);
        }
    }

    /*
     [4,2,5,1,3]

       4
     2   5
    1 3



      1 -> 2 -> 3 -> 4 -> 5
        <-   <-   <-   <-

      F    L    L    L    L
    */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);

        first.left = last;
        last.right = first;

        return first;
    }

    // inorder traversal iterative
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        Node first = null, last = null;

        Stack<Node> stack = new Stack<>();

        while (root != null || stack.size() > 0) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (first == null) first = root;

            if (perv != null) {
                last.right = root;
                root.left = last;
            }

            last = root;
            root = root.right;
        }

        first.left = last;
        last.right = first;
        return first;
    }

    // recursive
    Node last = null, first = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        inorderTraversal(root);
        first.left = last;
        last.right = first;
        return first;
    }

    private void inorderTraversal(Node curr) {
        if (curr == null) return;

        inorderTraversal(curr.left);

        if (first == null) first = curr;
        if (last != null) last.right = curr;

        curr.left = last;
        last = curr;

        inorderTraversal(curr.right);
    }

    Node first = null, last = null;

    public Node treeToDoublyList(Node root) {
      if (root == null) return null;
      inorderTraversal(root);
      first.left = last;
      last.right = first;

      return first;
    }

    public void inorderTraversal(Node node) {
      if (root == null) return;

      inorderTraversal(node.left);

      if (last != null) {
        last.right = node;
        node.left = last;
      } else {
        first = node;
      }

      last = node;

      inorderTraversal(node.right);
    }
}