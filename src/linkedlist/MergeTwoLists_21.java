package linkedlist;

class MergeTwoLists_21 {
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

 	/*
	public ListNode MergeTwoListsOrder(ListNode l1, ListNode l2) {
		ListNode first = l1, prev = l2;

		while(prev.next != null) {
			ListNode tmp = first.next;
			first.next = prev;
			first = tmp;

			tmp = prev.next;
			prev.next = first;
			prev = tmp;
		}

		return l1;
	}
	*/

	public ListNode MergeTwoListsOrder(ListNode l1, ListNode l2) {
		ListNode first = l1, prev = l2;

		/*
		1 -> 2 -> 3 -> 4
		^
		|


		6 -> 5 -> 4
		^
		|

		*/


		/*

		tmp = 2 -> 3 -> 4

		first 1 -> 6 -> 5 -> 4

		first = 2 -> 3 -> 4

		tmp = 5 -> 4;
		prev 6 -> 2 -> 3 -> 4;
		// 1 -> 6 -> 2 -> 3 -> 4

		prev = 5 -> 4
        ===========================
        tmp = 3 -> 4
        first 2 -> 5 -> 4
      	// 1-> 6 -> 2 -> 5 -> 4  
		first = 3 -> 4
		
		tmp = 4
		perv = 5 -> 3 -> 4;
		// 1 -> 6 -> 2 -> 5 -> 3 -> 4
		prev = 4;
		===========================
		
		1 6 2 5 3 4

*/

/*
			ListNode kk = first;
			System.out.println("=======");
			System.out.println("first.next = prev;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/

/*
			kk = first;
			System.out.println("=======");
			System.out.println("first = tmp;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/

		while (prev.next != null) {
			ListNode tmp = first.next;
			first.next = prev;

/*
			ListNode kk = l1;
			System.out.println("=======");
			System.out.println("first.next = prev;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/
			first = tmp;

/*
			kk = l1;
			System.out.println("=======");
			System.out.println("first = tmp;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/
			tmp = prev.next;
			prev.next = first;

/*
			kk = l1;
			System.out.println("=======");
			System.out.println("prev.next = first;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/
			prev = tmp;

/*
			kk = l1;
			System.out.println("=======");
			System.out.println("prev = tmp;");
			while (kk != null) {
				System.out.println(kk.val);
				kk = kk.next;
			}
			System.out.println("=======");
*/		}

		return l1;
	}

	public ListNode MergeTwoListsIIII(ListNode l1, ListNode l2) {
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
		ListNode six = new ListNode(6);
		ListNode five = new ListNode(5);
		ListNode four = new ListNode(4);
		six.next = five;
		five.next = four;


		ListNode fourtwo = new ListNode(4);

		ListNode three = new ListNode(3);
		three.next = fourtwo;

		ListNode two = new ListNode(2);
		two.next = three;
		ListNode one = new ListNode(1);
		one.next = two;

		ListNode result = new MergeTwoLists_21().MergeTwoListsOrder(one, six);


		System.out.println("==============");
		System.out.println("result:");

		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}
}