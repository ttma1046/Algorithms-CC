package linkedlist;

/*
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Example 1:

Input: head = [1,1,2]
Output: [1,2]

Example 2:

Input: head = [1,1,2,3,3]
Output: [1,2,3]

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

class Remove_Duplicates_from_Sorted_List_I_83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;

                slow = slow.next;
            }

            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
		ListNode curr = head;
		while (curr != null && curr.next != null) {
			if (curr.val == curr.next.val) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}

		return head;
    }

    // iterative
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while(curr != null) {
            while(curr.next != null && curr.val == curr.next.val)
                curr.next = curr.next.next;

            curr = curr.next;
        }

        return head;
    }

    // recursive
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return null;

        head.next = deleteDuplicates(head.next);

        return head.val == head.next.val ? head.next : head;
    }

    public static void main(String[] args) {
        Remove_Duplicates_from_Sorted_List_I_83 obj = new Remove_Duplicates_from_Sorted_List_I_83();
        ListNode one = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        // one = new ListNode(1, new ListNode(1, new ListNode(1)));
        ListNode res = obj.deleteDuplicates(one);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}