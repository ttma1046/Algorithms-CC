package linkedlist;

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
	if (l1 == null) {
		return l2;
	}

	if (l2 == null) {
		return l1;
	} 

	if (l1.val < l2.val) {
		l1.next = mergeTwoListsII(l1.next, l2);
		return l1;
	} else {
		l2.next = mergeTwoListsII(l1, l2.next);
		return l2;
	}
}

class MergeTwoLists_21 {
	public ListNode MergeTwoLists(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // At least one of l1 and l2 can still have nodes at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
	}

	public static void main(String[] args) {

		ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))));
		ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));

		ListNode result = new MergeTwoLists_21().MergeTwoLists(l1, l2);

		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
}