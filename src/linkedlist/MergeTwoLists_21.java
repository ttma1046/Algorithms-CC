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
	} else if (l2 == null) {
		return l1;
	} else if (l1.val < l2.val) {
		l1.next = mergeTwoListsII(l1.next, l2);
		return l1;
	} else {
		l2.next = mergeTwoListsII(l1, l2.next);
		return l2;
	}
}

class MergeTwoLists_21 {
	public ListNode MergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}

		if (l1 == null) {
			return l1;
		}

		if (l2 == null) {
			return l2;
		}

		ListNode tempOne = l1;
		ListNode tempTwo = l2;

		ListNode result = new ListNode(-1);
		ListNode temp = result;

		while (tempOne != null || tempTwo != null) {
			if ((tempOne != null && tempTwo != null && tempOne.val < tempTwo.val) || tempTwo == null) {
				temp.next = new ListNode(tempOne.val);
				tempOne = tempOne.next;
			} else if ((tempOne != null && tempTwo != null && tempOne.val >= tempTwo.val) || tempOne == null) {
				temp.next = new ListNode(tempTwo.val);
				tempTwo = tempTwo.next;
			}

			temp = temp.next;
		}

		return result.next;
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