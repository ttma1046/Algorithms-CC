package linkedlist;

/*
Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]

Example 2:
Input: head = [], val = 1;
Output: []

Example 3:
Input: head = [7,7,7,7], val = 7
Output: []
*/
class Remove_Linked_List_Elements_203 {
	public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode curr = dummy;
		while(curr.next != null) {
			if (curr.next.val == val) curr.next = curr.next.next;
			else curr = curr.next;
		}

		return dummy.next;
	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);

		dummy.next = head;

		ListNode pre = dummy, curr = head;

		while(curr != null && curr.next != null) {
			if (curr.val == curr.next.val) {
				int temp = curr.val;
				while(cur != null && temp == curr.val) curr = curr.next;
				pre.next = curr;
			} else {
				pre = pre.next;
				curr = curr.next;
			}
		}

		return dummy.next;
	}

	public ListNode removeElementsII(ListNode head, int val) {
		if (head == null) return null;

		head.next = removeElementsII(head.next, val);

		return head.val == val ? head.next : head;
	}

	public ListNode removeElements(ListNode head, int val) {
		if (head == null) return null;
		head.next =	removeElements(head.next, val);
		return head.val == val ? head.next : head;
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode prev = new ListNode(-1);
		prev.next = head;
		ListNode curr = prev;
		while(curr.next != null) {
			if (curr.next.val == val) curr.next = curr.next.next;
			else curr = curr.next;
		}

		return prev;
	}
}