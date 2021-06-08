package linkedlist;
/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

Example 1:

Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

Example 2:

Input: head = [1,1,1,2,3]
Output: [2,3]

Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
*/

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Remove_Duplicates_from_Sorted_List_II_82 {
    public ListNode deleteDuplicatesII(ListNode head) {
        if (head == null) return null;
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode pre = node;
        ListNode fast = head;

        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }

            if (pre.next == fast) {
                pre = pre.next;
            } else {
                pre.next = fast.next;
            }

            fast = fast.next;
        }

        return node.next;
    }

    public ListNode deleteDuplicatesIII(ListNode head) {
        if (head == null) return null;

        ListNode node = new ListNode(0);

        node.next = head;
        ListNode pre = node;
        ListNode fast = head;

        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }

            if (pre.next == fast) {
                pre = pre.next;
            } else {
                pre.next = fast.next;
            }

            fast = fast.next;
        }

        return node.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = new ListNode(0);

        ListNode slow = node;
        slow.next = head;
        ListNode fast = head;

        while (fast != null) {
            while(fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }

            if (slow.next == fast) {
                slow = slow.next;
            } else {
                slow.next = fast.next;
            }

            fast = fast.next;
        }

        return node.next;
    }


    public static void main(String[] args) {
        ListNode one =
            new ListNode(1,
                         new ListNode(2,
                                      new ListNode(3,
                                              new ListNode(3,
                                                      new ListNode(4,
                                                              new ListNode(4,
                                                                      new ListNode(5)))))));

        ListNode result = new Remove_Duplicates_from_Sorted_List_II_82().deleteDuplicates(one);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}