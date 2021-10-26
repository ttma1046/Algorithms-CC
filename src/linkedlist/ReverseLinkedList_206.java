package linkedlist;

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:

Input: head = [1,2]
Output: [2,1]

Example 3:

Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

public class ReverseLinkedList_206 {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        if (head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next; 
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public ListNode reverseListII(ListNode current) {
        if (current == null || current.next == null) return current;
        ListNode p = reverseListII(current.next);
        current.next.next = current;
        current.next = null;

        return p;
    }

    public ListNode reverseListIII(ListNode head) {
        /* recursive solution */
        return reverseListInt(null, head);
    }

    private ListNode reverseListInt(ListNode prev, ListNode current) {
        if (current == null) return prev;
        ListNode next = current.next;
        current.next = prev;
        return reverseListInt(current, next);
    }

    /*
    public ListNode reverseList(ListNode head) {
        return reverseListIterative(null, head);
    }

    private ListNode reverseListIterative(ListNode prev, ListNode current) {
        if (current == null) return prev;
        ListNode next = current.next;
        current.next = prev;
        reverseListIterative(current, next);
    }
    */

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        five.next = new ListNode(6);

        ListNode four  = new ListNode(4);
        four.next = five;

        ListNode three = new ListNode(3);
        three.next = four;

        ListNode two = new ListNode(2);
        two.next = three;

        ListNode node = new ListNode(1);
        node.next = two;

        new ReverseLinkedList_206().print(node);

        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }

        /*
        ListNode reverseNode = new ReverseLinkedList_206().reverseListIII(node);
        while(reverseNode != null) {
            System.out.println(reverseNode.val);
            reverseNode = reverseNode.next;
        }
        */
    }

    private void print(ListNode node) {
        /*
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        */
        node = node.next;

        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        // node.next = new ListNode(7);
    }
}
