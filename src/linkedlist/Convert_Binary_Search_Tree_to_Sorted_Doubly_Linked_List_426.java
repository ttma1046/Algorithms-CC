package linkedlist;

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

  public Node treeToDoublyList(Node root) {
    if (root == null) return null;

    helper(root);

    first.left = last;
    last.right = first;

    return first;
  }
}