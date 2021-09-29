package linkedlist;

/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.

If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:

Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]

Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]

Example 4:

Input: head = [1], k = 1
Output: [1]

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz

Follow-up: Can you solve the problem in O(1) extra memory space?
*/

/**
 * Definition for a binary tree node.
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

/**/
class Reverse_Nodes_in_k_Group_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = countLength(head);
        int i = 1;
        while (i + k <= length + 1) {
            head = reverseGroup(head, i, i + k - 1);
            i += k;
        }

        return head;
    }

    public ListNode reverseGroup(ListNode head, int start, int end) {
        ListNode prevHead = new ListNode(-1, head);

        ListNode curr = prevHead;
        ListNode prev = null;

        for (int i = 0; i < start; ++i) {
            prev = curr;
            curr = curr.next;
        }

        ListNode curr1 = curr;
        ListNode prev1 = prev;


        for (int i = start; i <= end; ++i) {
            ListNode temp = curr1.next;
            curr1.next = prev1;

            prev1 = curr1;
            curr1 = temp;
        }


        prev.next = prev1;
        curr.next = curr1;

        return prevHead.next;
    }


    private int countLength(ListNode head) {
        int i = 0;
        while(head != null) {
            i++;
            head = head.next;
        }

        return i;
    }
}