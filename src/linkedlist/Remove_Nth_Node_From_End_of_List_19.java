package linkedlist;

/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []

Example 3:

Input: head = [1,2], n = 1
Output: [1]

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

Follow up: Could you do this in one pass?
*/
class Remove_Nth_Node_From_End_of_List_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null) return null;


        ListNode dummy = new ListNode(-1);

        dummy.next = head;

        ListNode slow = dummy, fast = dummy;

        for(int i = 0; i < n; ++i) fast = fast.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (n == 0) return head;

        ListNode start = new ListNode();
        start.next = head;

        ListNode slow = start;
        ListNode fast = start;
        int count = n + 1;
        while (count-- > 0) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp = null;
        return start.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null) return null;

        ListNode dummy = new ListNode(-1);

        dummy.next = head;

        ListNode slow = dummy, fast = dummy;

        for(int i = 0; i < n + 1; ++i) fast = fast.next;

        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;

        while(first != null) {
            length++;
            first = first.next;
        }

        length -= n;
        first = dummy;

        while(length > 0) {
            length--;
            first = first.next;
        }

        first.next = first.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode one =
            new ListNode(1,
                         new ListNode(2,
                                      new ListNode(3,
                                              new ListNode(4,
                                                      new ListNode(5)))));

        Remove_Nth_Node_From_End_of_List_19 obj = new Remove_Nth_Node_From_End_of_List_19();

        obj.removeNthFromEnd(one, 2);
    }
}