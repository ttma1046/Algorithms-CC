package linkedlist;

class Remove_Duplicates_from_Sorted_List_I {
	
	public ListNode deleteDuplicatesI(ListNode head) {

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
}